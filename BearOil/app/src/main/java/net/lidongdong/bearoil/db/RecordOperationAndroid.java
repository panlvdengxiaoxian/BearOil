package net.lidongdong.bearoil.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @date 17/4/13
 * @explain
 * @function
 */

public class RecordOperationAndroid implements TableRecordOperation {
    private BearSQLiteHelper mHelper;

    public RecordOperationAndroid(BearSQLiteHelper helper) {
        mHelper = helper;
    }

    @Override
    public void addCar(RecordEntity record) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (record.get_id() != 0) {
            values.put(BearSQLiteValues._ID, record.get_id());
        }
        values.put(BearSQLiteValues.DATE, record.getDate());
        values.put(BearSQLiteValues.ODOMETER, record.getOdometer());
        values.put(BearSQLiteValues.PRICE, record.getPrice());
        values.put(BearSQLiteValues.YUAN, record.getYuan());
        values.put(BearSQLiteValues.TYPE, record.getType());
        values.put(BearSQLiteValues.GASSUP, record.getGasSup());
        values.put(BearSQLiteValues.REMARK, record.getRemark());
        values.put(BearSQLiteValues.CARID, record.getCarId());
        values.put(BearSQLiteValues.FORGET, record.getForget());
        values.put(BearSQLiteValues.LIGHTON, record.getLightOn());
        values.put(BearSQLiteValues.STATIONID, record.getStationId());
        db.insert(BearSQLiteValues.RECORDS_TBL, null, values);
        db.close();
    }

    @Override
    public void removeCar(int id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String whereClause = BearSQLiteValues._ID + " =?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.delete(BearSQLiteValues.RECORDS_TBL, whereClause, whereArgs);
        db.close();
    }

    @Override
    public void updateCar(RecordEntity record) {
           updateCar(openDatabase(),true,record);
    }

    private void updateCar(SQLiteDatabase db, boolean isClose, RecordEntity record) {
        ContentValues values = new ContentValues();
        values.put(BearSQLiteValues.DATE, record.getDate());
        values.put(BearSQLiteValues.ODOMETER, record.getOdometer());
        values.put(BearSQLiteValues.PRICE, record.getPrice());
        values.put(BearSQLiteValues.YUAN, record.getYuan());
        values.put(BearSQLiteValues.TYPE, record.getType());
        values.put(BearSQLiteValues.GASSUP, record.getGasSup());
        values.put(BearSQLiteValues.REMARK, record.getRemark());
        values.put(BearSQLiteValues.CARID, record.getCarId());
        values.put(BearSQLiteValues.FORGET, record.getForget());
        values.put(BearSQLiteValues.LIGHTON, record.getLightOn());
        values.put(BearSQLiteValues.STATIONID, record.getStationId());
        db.insert(BearSQLiteValues.RECORDS_TBL, null, values);
        String whereClause = BearSQLiteValues._ID + " =?";
        String[] whereArgs = new String[]{String.valueOf(record.get_id())};
        db.update(BearSQLiteValues.RECORDS_TBL, values, whereClause, whereArgs);
        if (isClose) {
            closeDatabase(db);
        }

    }

    @Override
    public List<RecordEntity> queryCars() {
        List<RecordEntity> records=new ArrayList<>();
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.query(BearSQLiteValues.RECORDS_TBL, null, null, null, null, null, null);
        if (cursor!=null&&cursor.moveToFirst()) {
            int indexId=cursor.getColumnIndex(BearSQLiteValues._ID);
            int indexDate=cursor.getColumnIndex(BearSQLiteValues.DATE);
            int indexOdometer=cursor.getColumnIndex(BearSQLiteValues.ODOMETER);
            int indexPrice=cursor.getColumnIndex(BearSQLiteValues.PRICE);
            int indexYuan=cursor.getColumnIndex(BearSQLiteValues.YUAN);
            int indexType=cursor.getColumnIndex(BearSQLiteValues.TYPE);
            int indexGassup=cursor.getColumnIndex(BearSQLiteValues.GASSUP);
            int indexRemark=cursor.getColumnIndex(BearSQLiteValues.REMARK);
            int indexCarId=cursor.getColumnIndex(BearSQLiteValues.CARID);
            int indexForget=cursor.getColumnIndex(BearSQLiteValues.FORGET);
            int indexLightOn=cursor.getColumnIndex(BearSQLiteValues.LIGHTON);
            int indexStationID=cursor.getColumnIndex(BearSQLiteValues.STATIONID);
            do {
                int id=cursor.getInt(indexId);
                int date=cursor.getInt(indexDate);
                int odometer=cursor.getInt(indexOdometer);
                int price=cursor.getInt(indexPrice);
                int yuan=cursor.getInt(indexYuan);
                int type=cursor.getInt(indexType);
                int gassup=cursor.getInt(indexGassup);
                String remark=cursor.getString(indexRemark);
                int carId=cursor.getInt(indexCarId);
                int forget=cursor.getInt(indexForget);
                int lightOn=cursor.getInt(indexLightOn);
                int stationId=cursor.getInt(indexStationID);
                RecordEntity record=new RecordEntity(id);
                record.setDate(date);
                record.setOdometer(odometer);
                record.setPrice(price);
                record.setYuan(yuan);
                record.setType(type);
                record.setGasSup(gassup);
                record.setRemark(remark);
                record.setCarId(carId);
                record.setForget(forget);
                record.setLightOn(lightOn);
                record.setStationId(stationId);
                records.add(record);

            }while (cursor.moveToNext());
            cursor.close();
        }
        db.close();

        return records;
    }

    @Override
    public RecordEntity querySelectedCar() {
        return querySelectedCar(openDatabase(),true);
    }
    private RecordEntity querySelectedCar(SQLiteDatabase db,boolean isClose){
        String selection=BearSQLiteValues.CARID + " =?";
        String[] selectionArgs=new String[]{String.valueOf(DatabaseTool.getInstance().querySelectedCar().get_id())};
        Cursor cursor = db.query(BearSQLiteValues.RECORDS_TBL, null, selection, selectionArgs, null, null, null);
        if (cursor!=null&&cursor.moveToFirst()){
            int indexId=cursor.getColumnIndex(BearSQLiteValues._ID);
            int indexDate=cursor.getColumnIndex(BearSQLiteValues.DATE);
            int indexOdometer=cursor.getColumnIndex(BearSQLiteValues.ODOMETER);
            int indexPrice=cursor.getColumnIndex(BearSQLiteValues.PRICE);
            int indexYuan=cursor.getColumnIndex(BearSQLiteValues.YUAN);
            int indexType=cursor.getColumnIndex(BearSQLiteValues.TYPE);
            int indexGassup=cursor.getColumnIndex(BearSQLiteValues.GASSUP);
            int indexRemark=cursor.getColumnIndex(BearSQLiteValues.REMARK);
            int indexCarId=cursor.getColumnIndex(BearSQLiteValues.CARID);
            int indexForget=cursor.getColumnIndex(BearSQLiteValues.FORGET);
            int indexLightOn=cursor.getColumnIndex(BearSQLiteValues.LIGHTON);
            int indexStationID=cursor.getColumnIndex(BearSQLiteValues.STATIONID);
            int id=cursor.getInt(indexId);
            int date=cursor.getInt(indexDate);
            int odometer=cursor.getInt(indexOdometer);
            int price=cursor.getInt(indexPrice);
            int yuan=cursor.getInt(indexYuan);
            int type=cursor.getInt(indexType);
            int gassup=cursor.getInt(indexGassup);
            String remark=cursor.getString(indexRemark);
            int carId=cursor.getInt(indexCarId);
            int forget=cursor.getInt(indexForget);
            int lightOn=cursor.getInt(indexLightOn);
            int stationId=cursor.getInt(indexStationID);
            RecordEntity record=new RecordEntity(id);
            record.setDate(date);
            record.setOdometer(odometer);
            record.setPrice(price);
            record.setYuan(yuan);
            record.setType(type);
            record.setGasSup(gassup);
            record.setRemark(remark);
            record.setCarId(carId);
            record.setForget(forget);
            record.setLightOn(lightOn);
            record.setStationId(stationId);
            return record;
        }
        if (isClose) {
            closeDatabase(db);
        }
        return null;

    }

    @Override
    public void changeSelectedCar(int carId) {

    }

    @Override
    public void changeSelectedRecord(RecordEntity newRecordEntity) {

    }

    /**
     * 初始化数据库
     *
     * @return 对象
     */
    private SQLiteDatabase openDatabase() {
        return mHelper.getWritableDatabase();
    }

    /**
     * 关数据库
     *
     * @param db 对象
     */

    private void closeDatabase(SQLiteDatabase db) {
        if (db != null) {
            db.close();
        }
    }
}
