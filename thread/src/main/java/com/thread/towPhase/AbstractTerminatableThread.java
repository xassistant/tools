package com.thread.towPhase;

public abstract class AbstractTerminatableThread extends Thread implements Terminatable {

    // 模式角色
    private final TerminationToken termination;

    public AbstractTerminatableThread() {
        this.termination = new TerminationToken();
    }

    public AbstractTerminatableThread(TerminationToken termination) {
        super();
        this.termination = termination;
        termination.register(this);
    }

    /**
     * 子类实现的逻辑
     *
     * @param args
     */
    protected abstract void doRun() throws Exception;

    protected void doCleanup(Exception cause){
        // nothing
    }
    protected void doTerminiate(){
        // nothiog
    }

    @Override
    public void run() {
        super.run();
    }

    public static void main(String[] args) {
        AbstractTerminatableThread t = new AbstractTerminatableThread() {

            @Override
            protected void doRun() throws Exception {

            }
        };

    }
}
