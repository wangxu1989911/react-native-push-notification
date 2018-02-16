package com.dieam.reactnativepushnotification.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.dieam.reactnativepushnotification.db.item_notification_id.ItemNotificationIdManager;
import com.dieam.reactnativepushnotification.db.notificationmessage.NotificationMessageManager;
import com.dieam.reactnativepushnotification.db.notificationmessagecounter.NotificationMessageCounterManager;

public class Manager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public Manager(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public NotificationMessageManager getNotificationMessageManager() {
        return new NotificationMessageManager(db);
    }

    public NotificationMessageCounterManager getNotificationMessageCounterManager() {
        return new NotificationMessageCounterManager(db);
    }

    public ItemNotificationIdManager getItemNotificationIdManager() {
        return new ItemNotificationIdManager(db);
    }
    /**
     * tested
     */
    public void closeDB() {
        db.close();
    }

    /**
     * tested
     *
     * @return
     */
    public boolean isDataBaseOpened() {
        return db.isOpen();
    }

}
