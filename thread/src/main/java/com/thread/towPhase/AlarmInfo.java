package com.thread.towPhase;

public class AlarmInfo {

    private String id;

    public AlarmType type;

    private String extroInfo;

    public AlarmInfo(String id, AlarmType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AlarmType getType() {
        return type;
    }

    public void setType(AlarmType type) {
        this.type = type;
    }

    public void setExtroInfo(String extroInfo) {
        this.extroInfo = extroInfo;
    }
}
