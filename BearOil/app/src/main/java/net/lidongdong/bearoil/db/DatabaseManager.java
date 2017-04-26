package net.lidongdong.bearoil.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @ date 17/4/20
 * @ explain
 * @ function
 */

public class DatabaseManager {

    private AtomicInteger mOpenCounter = new AtomicInteger();
    private static DatabaseManager instance;
    private static SQLiteOpenHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;

    private DatabaseManager() {
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }

    public static synchronized void initializeInstance(SQLiteOpenHelper helper) {
        if (instance == null) {
            mDatabaseHelper = helper;
            instance = new DatabaseManager();
        }
    }

    public static synchronized DatabaseManager getInstance(SQLiteOpenHelper helper) {
        if (instance == null) {
            initializeInstance(helper);
        }
        return instance;
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }

        return mDatabase;
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            //open new database
            mDatabase = mDatabaseHelper.getReadableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            // close database
         mDatabase.close();
        }
    }

}