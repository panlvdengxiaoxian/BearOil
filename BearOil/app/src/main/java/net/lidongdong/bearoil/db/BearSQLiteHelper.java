package net.lidongdong.bearoil.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @date 17/4/13
 * @explain
 * @function
 */


class BearSQLiteHelper extends SQLiteOpenHelper {

    BearSQLiteHelper(Context context) {
        super(context,BearSQLiteValues.NAME_DB,null,BearSQLiteValues.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BearSQLiteValues.SQL_CAR);
        db.execSQL(BearSQLiteValues.SQL_RECORD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

