package com.thread.towPhase;


public class AlarmMgr {
    // 保存AlarmMgr类的唯一实例
    private static final AlarmMgr INSTANCE = new AlarmMgr();

    private volatile boolean shutdownRequested = false;

    // 告警发送线程
    private final AlarmSendingThread alarmSendingThread;

    // 私有构造器
    private AlarmMgr() {
        alarmSendingThread = new AlarmSendingThread();
    }

    public AlarmSendingThread getAlarmSendingThread() {
        return alarmSendingThread;
    }

    // 发送告警
    public int sendAlarm(AlarmType type, String id, String extroInfo) {
        // log for teyp id extroinfo
        int duplicateSubmissionCount = 0;
        AlarmInfo alarmInfo = new AlarmInfo(id, type);
        alarmInfo.setExtroInfo(extroInfo);
        // send
        duplicateSubmissionCount = alarmSendingThread.sendAlarm(alarmInfo);

        return duplicateSubmissionCount;
    }
}