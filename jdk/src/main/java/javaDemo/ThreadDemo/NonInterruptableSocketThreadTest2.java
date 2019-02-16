package javaDemo.ThreadDemo;

import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.*;

public class NonInterruptableSocketThreadTest2<T> extends SocketUsingTask<T> {
    @Test
    public void test() throws InterruptedException, ExecutionException {
        NonInterruptableSocketThreadTest2 test = new NonInterruptableSocketThreadTest2();
        RunnableFuture runnableFuture = test.newTask();
        runnableFuture.run();
        System.out.println(runnableFuture.get());
        Thread.sleep(100000000);
    }

    @Override
    public T call() throws Exception {
        return (T) "ok";
    }
}
class CancelingExecutor extends ThreadPoolExecutor{

    public CancelingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable){
        if(callable instanceof CancelableTask){
            return ((CancelableTask<T>) callable).newTask();
        }else {
            return super.newTaskFor(callable);
        }
    }
}
abstract class SocketUsingTask<T> implements CancelableTask<T> {
    private Socket socket;

    public synchronized void setSocket(Socket s) {
        this.socket = s;
    }

    @Override
    public synchronized void cancel() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {

            }
        }
    }

    @Override
    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                SocketUsingTask.this.cancel();
                return super.cancel(mayInterruptIfRunning);
            }
        };
    }
}

interface CancelableTask<T> extends Callable<T> {
    void cancel();

    RunnableFuture<T> newTask();
}