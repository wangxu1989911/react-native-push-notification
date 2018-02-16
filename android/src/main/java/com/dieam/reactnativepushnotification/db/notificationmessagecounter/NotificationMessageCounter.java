package com.dieam.reactnativepushnotification.db.notificationmessagecounter;

public class NotificationMessageCounter {
    public String sourceId;

    public Integer number;

    public NotificationMessageCounter() {

    }

    public NotificationMessageCounter(String sourceId, Integer number) {
        this.sourceId = sourceId;
        this.number = number;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "NotificationMessageCounter{" +
                "sourceId='" + sourceId + '\'' +
                ", number=" + number +
                '}';
    }
}
