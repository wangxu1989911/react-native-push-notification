package com.dieam.reactnativepushnotification.db.item_notification_id;

public class ItemNotificationId {

    public Integer notificationId;

    public String itemId;

    public ItemNotificationId() {

    }

    public ItemNotificationId(Integer notificationId, String itemId) {
        this.notificationId = notificationId;
        this.itemId = itemId;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "ItemNotificationId{" +
                "notificationId=" + notificationId +
                ", itemId='" + itemId + '\'' +
                '}';
    }
}

