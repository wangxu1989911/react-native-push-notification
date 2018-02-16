package com.dieam.reactnativepushnotification.db.notificationmessage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NotificationMessageManager {
    private SQLiteDatabase db;
    public static final String LOG_TAG = "NotifMsg SQLException";

    public NotificationMessageManager(SQLiteDatabase db) {
        this.db = db;
    }

    public void addMultiple(List<NotificationMessage> messages) {
        // begins a transaction in EXCLUSIVE mode
        db.beginTransaction();
        try {
            for (NotificationMessage message : messages) {
                db.execSQL("INSERT INTO notificationmessage VALUES(null, ?, ?)",
                        new Object[]{message.id, message.message,message.sourceId});
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e(LOG_TAG, "fail to insert notificationmessage into database in addMultiple");
        } finally {
            db.endTransaction();
        }
    }

    /**
     * tested
     *
     * @param message
     * @param sourceId
     */
    public void addOne(String message, String sourceId) {
        ContentValues value = new ContentValues();
        value.put(NotificationMessageDescription.MESSAGE, message);
        value.put(NotificationMessageDescription.SOURCE_ID, sourceId);
        db.insert(NotificationMessageDescription.TABLE_NAME, null ,value);
    }

    public void addOneInRaw(int id, String message, String sourceId) {
        ContentValues values = new ContentValues();
        values.put(NotificationMessageDescription.ID, id);
        values.put(NotificationMessageDescription.MESSAGE, message);
        values.put(NotificationMessageDescription.SOURCE_ID, sourceId);
        db.insert(NotificationMessageDescription.TABLE_NAME, null, values);
    }

    public void update(String type, String content) {
        ContentValues value = new ContentValues();
        if (type.equals(NotificationMessageDescription.MESSAGE)) {
            value.put(NotificationMessageDescription.MESSAGE, content);
            db.update(NotificationMessageDescription.TABLE_NAME, value, "message = ?", new String[]{content});
        }
    }

    /**
     * tested
     *
     * @param id
     */
    public void delete(Integer id) {
        // define 'where' pare of query
        String selection = NotificationMessageDescription.ID + " = ?";
        // specify arguments in placeholder order
        String[] selectionArgs = new String[] {id + ""};
        db.delete(NotificationMessageDescription.TABLE_NAME, selection, selectionArgs);
    }

    /**
     * tested
     *
     * @param target
     * @param sourceId
     * @return
     */
    public Integer count(String target, String sourceId) {
        String sql = "SELECT COUNT(" + target + ") FROM " + NotificationMessageDescription.TABLE_NAME
                + " WHERE source_id=?";
        Cursor cursor = db.rawQuery(sql, new String[] {sourceId});
        cursor.moveToFirst();
        Integer count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    /**
     * tested
     *
     * @param sourceId
     * @return
     */
    public List<NotificationMessage> getMuliple(String sourceId) {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query
        String[] projection = new String[] {
                NotificationMessageDescription.ID,
                NotificationMessageDescription.MESSAGE,
                NotificationMessageDescription.SOURCE_ID
        };
        String selection = NotificationMessageDescription.SOURCE_ID + " = ?";
        String[] selectionArgs = new String[] {sourceId};
        // How you want the results sorted in the resulting Cursor
        String sortOrder = NotificationMessageDescription.ID + " DESC ";
        Cursor cursor = db.query(
                NotificationMessageDescription.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        List<NotificationMessage> result = new ArrayList<>();
        NotificationMessage tempMessage = null;
        while (cursor.moveToNext()) {
            tempMessage = new NotificationMessage();
            tempMessage.id = cursor.getInt(cursor.getColumnIndex(NotificationMessageDescription.ID));
            tempMessage.message = cursor.getString(cursor.getColumnIndex(NotificationMessageDescription.MESSAGE));
            tempMessage.sourceId = cursor.getString(cursor.getColumnIndex(NotificationMessageDescription.SOURCE_ID));
            result.add(tempMessage);
        }
        // release memory
        cursor.close();
        return result;
    }

    /**
     * tested
     *
     * @param sourceId
     */
    public void delete(String sourceId) {
        String sql = "DELETE FROM " + NotificationMessageDescription.TABLE_NAME +
                " WHERE source_id = " + sourceId;
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "fail to delete number in notificationmessage table");
        }
    }

    public Boolean checkIfExist(String sourceId) {
        String sql = "SELECT * FROM " + NotificationMessageDescription.TABLE_NAME +
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
     *
     */
    public void clear() {
        String sql = "DELETE FROM " + NotificationMessageDescription.TABLE_NAME;
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "fail to clear notificationmessage table");
        }
    }
}
