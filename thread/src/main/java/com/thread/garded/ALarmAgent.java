package com.thread.garded;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

public class ALarmAgent {
    //用于记录AlarmAgent
    private volatile boolean connectedToServer = false;
    //模式角色：GuardedSuspension.Predicate
    private final Predicate agentConnected = () -> connectedToServer;
    //模式角色GuaededSuspension.Blocker
    private final Blocker blocker = new ConditionVarBlocker();
    //心跳定时器
    private final Timer heartbeatTimer = new Timer(true);

    //发送告警信息
    public void sendAlarm(final AlarmInfo alarm) throws Exception {
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected) {
            public Void call() {
                doSendAlarm(alarm);
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }

    //通过网络链接讲告警信息发送给告警服务器
    private void doSendAlarm(AlarmInfo alarm) {
        //省略其他与设计模式无关的代码
        System.out.print("sending alarm" + alarm);
        //模拟发送告警值服务器的耗时
        try {
            Thread.sleep(50);
        } catch (Exception e) {

        }
    }

    public void init() {
        //省略其他与设计模式无关的代码
        //告警服务器连接线程
        Thread connectingThread = new Thread(new ConnectingTask());

        connectingThread.start();

        heartbeatTimer.schedule(new HeartbeatTask(), 60000, 2000);
    }

    public void disconnect() {
        //省略其他与设计模式无关的代码
        System.out.print("disconnected from alarm server.");
        connectedToServer = false;
    }

    protected void onConnected() {
        try {
            blocker.signalAfter(new Callable<Boolean>() {
                @Override
                public Boolean call() {
                    connectedToServer = true;
                    System.out.print("connnected to server");
                    return Boolean.TRUE;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void onDisconnected() {
        connectedToServer = false;
    }

    //负责与告警服务器建立网络链接
    private class ConnectingTask implements Runnable {
        @Override
        public void run() {
            //省略其他与设计模式无关的代码
            //模拟连接操作耗时
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {

            }
        }
    }

    //心跳定时任务：定时检车与该井服务器的链接是否正常，发现链接异常后自动重新链接
    private class HeartbeatTask extends TimerTask {
        @Override
        public void run() {
            //省略其他与设计模式无关的代码
            if (!testConnection()) {
                onDisconnected();
                reconnect();
            }
        }

        private boolean testConnection() {
            //省略其他与设计模式无关的代码
            return true;
        }

        private void reconnect() {
            ConnectingTask connectingThread = new ConnectingTask();
            //直接在心跳定时器线程中执行
            connectingThread.run();
        }
    }

    public static void main(String[] args) throws Exception {
        ALarmAgent aLarmAgent = new ALarmAgent();
        aLarmAgent.init();
        aLarmAgent.sendAlarm(new AlarmInfo("111111"));
    }
}