package com.dieam.reactnativepushnotification.db.item_notification_id;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ItemNotificationIdManager {
    private SQLiteDatabase db;
    public static final String LOG_TAG = "ItemNotif SQLException";

    public ItemNotificationIdManager(SQLiteDatabase db) {
        this.db = db;
    }

    public Long addOne(String itemId) {
        ContentValues value = new ContentValues();
        value.put(ItemNotificationIdDescription.ITEM_ID, itemId);
        return db.insert(ItemNotificationIdDescription.TABLE_NAME, null, value);
    }

    public void deleteOne(Integer notificationId) {
        String sql = "DELETE FROM " + ItemNotificationIdDescription.TABLE_NAME +
                " WHERE notification_id = " + notificationId;
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "fail to delete one row in itemnotificationid table");
        }
    }

    public ItemNotificationId checkIfExist(String itemId) {
        String sql = "SELECT * FROM " + ItemNotificationIdDescription.TABLE_NAME +
                " WHERE " + ItemNotificationIdDescription.ITEM_ID + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[] {itemId});
        if (cursor.getCount() <= 0) {
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        ItemNotificationId result = new ItemNotificationId();
        result.setNotificationId(
                cursor.getInt(cursor.getColumnIndex(ItemNotificationIdDescription.NOTIFICATION_ID)));
        result.setItemId(
                cursor.getString(cursor.getColumnIndex(ItemNotificationIdDescription.ITEM_ID)));
        cursor.close();
        return result;
    }

    public Boolean checkIfExist(Integer notificationId) {
        String sql = "SELECT * FROM " + ItemNotificationIdDescription.TABLE_NAME +
                " WHERE " + ItemNotificationIdDescription.NOTIFICATION_ID + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[] {notificationId.toString()});
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    /**
     * clear all data in the table
     */
    public void clear() {
        String sql = "DELETE FROM " + ItemNotificationIdDescription.TABLE_NAME;
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "fail to clear itemnotificationid table");
        }
    }

}
