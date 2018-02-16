package com.dieam.reactnativepushnotification.db.notificationmessagecounter;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class NotificationMessageCounterManager {
    private SQLiteDatabase db;
    public static final String LOG_TAG = "NotifMsgCt SQLException";

    public NotificationMessageCounterManager(SQLiteDatabase db) {
        this.db = db;
    }

    public void update(String sourceId) {
        String sqlUpdate = "UPDATE notificationmessagecounter SET number = number + 1 WHERE source_id = " +
                sourceId;
        String sqlInsert = "INSERT INTO notificationmessagecounter VALUES(?,?)";
        try {
            if (checkIfExist(sourceId)) {
                db.execSQL(sqlUpdate);
            } else {
                db.execSQL(sqlInsert, new Object[] {sourceId, 1});
            }
        } catch (SQLException e) {
            Log.e(LOG_TAG, "fail to update number in notificationmessagecounter table");
        }
    }

    public Integer getCount(String sourceId) {
        String sql = "SELECT number FROM " + NotificationMessageCounterDescription.TABLE_NAME +
                " WHERE source_id = ?";
        Cursor cursor = db.rawQuery(sql, new String[] {sourceId});
        cursor.moveToFirst();
        Integer count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    public void delete(String sourceId) {
        String sql = "DELETE FROM " + NotificationMessageCounterDescription.TABLE_NAME +
                " WHERE source_id = " + sourceId;
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "fail to delete number in notificationmessagecounter table");
        }
    }

    public Boolean checkIfExist(String sourceId) {
        String sql = "SELECT number FROM " + NotificationMessageCounterDescription.TABLE_NAME +
                " WHERE source_id = ?";
        Cursor cursor = db.rawQuery(sql, new String[] {sourceId});
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
        String sql = "DELETE FROM " + NotificationMessageCounterDescription.TABLE_NAME;
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "fail to clear notificationmessagecounter table");
        }
    }
}
