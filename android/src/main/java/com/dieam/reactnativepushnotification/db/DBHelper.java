package com.dieam.reactnativepushnotification.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "tc.db";
    private static final int DATABASE_VERSION = 3;

    public DBHelper(Context context) {
        // Cursor factory is set to null, use default one
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // This method will be called when database is created in its firs time
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS notificationmessage " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, message TEXT, source_id TEXT)");
        System.out.println("DBTest--create notificationmessage table in onCreate()");

        db.execSQL("CREATE TABLE IF NOT EXISTS notificationmessagecounter " +
                "(source_id TEXT PRIMARY KEY, number INTEGER)");
        System.out.println("DBTest--create notificationmessagecounter table in onCreate()");

        db.execSQL("CREATE TABLE IF NOT EXISTS itemnotificationid " +
                "(notification_id INTEGER PRIMARY KEY AUTOINCREMENT, item_id TEXT)");
        System.out.println("DBTest--create itemnotificationid table in onCreate()");

    }

    // If DATABASE_VERSION's value is updated, this method will be called
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE IF NOT EXISTS notificationmessage " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, message TEXT, source_id TEXT)");
        System.out.println("DBTest--create notificationmessage table in onUpgrade()");

        db.execSQL("CREATE TABLE IF NOT EXISTS notificationmessagecounter " +
                "(source_id TEXT PRIMARY KEY, number INTEGER)");
        System.out.println("DBTest--create notificationmessagecounter table in onUpgrade()");

        db.execSQL("CREATE TABLE IF NOT EXISTS itemnotificationid " +
                "(notification_id INTEGER PRIMARY KEY AUTOINCREMENT, item_id TEXT)");
        System.out.println("DBTest--create itemnotificationid table in onUpgrade()");
    }
}
