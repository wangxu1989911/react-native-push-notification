package com.dieam.reactnativepushnotification.db.notificationmessage;

public class NotificationMessage {
    public Integer id;

    public String message;

    public String sourceId;

    public NotificationMessage() {

    }

    public NotificationMessage(Integer id, String message, String sourceId) {
        this.id = id;
        this.message = message;
        this.sourceId = sourceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public String toString() {
        return "NotificationMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", sourceId='" + sourceId + '\'' +
                '}';
    }
}
