package net.lidongdong.bearoil.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.lidongdong.bearoil.entity.MoneyEntity;
import net.lidongdong.bearoil.entity.RecordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @ date 17/4/14
 * @ explain
 * @ function
 */

class RecordOperationSQL implements TableRecordOperation {
    private BearSQLiteHelper mHelper;

    RecordOperationSQL(BearSQLiteHelper helper) {
        mHelper = helper;
    }


    /**
     * 对 record 的操作
     *
     * @param sql
     */

    private void record(String sql) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }


    @Override
    public void addRecord(RecordEntity record) {
        String sql;
        if (record.getCarId() == 0) {
            sql = "insert into cars_tbl (date, odometer, price, yuan, type, gasSup, remark, carId, forget, lightOn, stationId) values (" +
                    record.getDate() + ", " + record.getOdometer() + ", " + record.getPrice() + ", " + record.getYuan() + ", " + record.getType()
                    + ", " + record.getGasSup() + ", " + record.getRemark() + ", " + record.getCarId() + ", " + record.getForget() + ", " +
                    record.getLightOn() + ", " + record.getStationId() + ");";
        } else {
            sql = "insert into cars_tbl values (" + record.get_id() + ", " +
                    record.getDate() + ", " + record.getOdometer() + ", " + record.getPrice() + ", " + record.getYuan() + ", " + record.getType()
                    + ", " + record.getGasSup() + ", " + record.getRemark() + ", " + record.getCarId() + ", " + record.getForget() + ", " +
                    record.getLightOn() + ", " + record.getStationId() + ");";
        }
        record(sql);

    }

    @Override
    public void removeRecord(int id) {
        String sql = "delete from records_tbl ";
    }

    @Override
    public void updateRecords(RecordEntity record) {

    }

    /**
     * 查询相关数据的集合
     *
     * @param sql 查询语句
     * @return 数据集合
     */

    private List<RecordEntity> queryRecords(String sql) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        List<RecordEntity> recordEntities = new ArrayList<>();
        if (null != cursor && cursor.moveToFirst()) {

            int indexId = cursor.getColumnIndex(BearSQLiteValues.RECORD_ID);
            int indexDate = cursor.getColumnIndex(BearSQLiteValues.DATE);
            int indexOdometer = cursor.getColumnIndex(BearSQLiteValues.ODOMETER);
            int indexPrice = cursor.getColumnIndex(BearSQLiteValues.PRICE);
            int indexYuan = cursor.getColumnIndex(BearSQLiteValues.YUAN);
            int indexType = cursor.getColumnIndex(BearSQLiteValues.TYPE);
            int indexGassup = cursor.getColumnIndex(BearSQLiteValues.GASSUP);
            int indexRemark = cursor.getColumnIndex(BearSQLiteValues.REMARK);
            int indexCarId = cursor.getColumnIndex(BearSQLiteValues.CARID);
            int indexForget = cursor.getColumnIndex(BearSQLiteValues.FORGET);
            int indexLightOn = cursor.getColumnIndex(BearSQLiteValues.LIGHTON);
            int indexStationId = cursor.getColumnIndex(BearSQLiteValues.STATIONID);

            do {

                int id = cursor.getInt(indexId);
                int date = cursor.getInt(indexDate);
                int odometer = cursor.getInt(indexOdometer);
                int price = cursor.getInt(indexPrice);
                int yuan = cursor.getInt(indexYuan);
                int type = cursor.getInt(indexType);
                int gassup = cursor.getInt(indexGassup);
                String remark = cursor.getString(indexRemark);
                int carId = cursor.getInt(indexCarId);
                int forget = cursor.getInt(indexForget);
                int lightOn = cursor.getInt(indexLightOn);
                int stationId = cursor.getInt(indexStationId);

                RecordEntity recordEntity = new RecordEntity(id);
                recordEntity.setDate(date);
                recordEntity.setOdometer(odometer);
                recordEntity.setPrice(price);
                recordEntity.setYuan(yuan);
                recordEntity.setType(type);
                recordEntity.setGasSup(gassup);
                recordEntity.setRemark(remark);
                recordEntity.setCarId(carId);
                recordEntity.setForget(forget);
                recordEntity.setLightOn(lightOn);
                recordEntity.setStationId(stationId);
                recordEntities.add(recordEntity);

            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return recordEntities;
    }

    @Override
    public List<RecordEntity> queryRecords() {
        String sql = "SELECT A.*" +
                "FROM records_tbl as A, cars_tbl AS B" +
                "where" +
                "A.carId = B._id" +
                "AND" +
                "B.selected = 1;";
        return queryRecords(sql);
    }

    @Override
    public List<RecordEntity> queryRecordsEachYear() {
        String sql = "SELECT A.date, A.odometer, A.price, A.yuan, A.carId, datetime(A.date / 1000, 'unixepoch')" +
                "from records_tbl as A, cars_tbl as B" +
                "WHERE A.date > (" +
                "    strftime('%s', datetime('now', '-12 month')) * 1000" +
                ")" +
                "AND" +
                "A.carId = B._id" +
                "AND" +
                "B.selected = 1;";
        return queryRecords(sql);
    }

    @Override
    public List<RecordEntity> queryRecordsEachHalfOfYear() {
        String sql = "SELECT A.date, A.odometer, A.price, A.yuan, A.carId, datetime(A.date / 1000, 'unixepoch')" +
                "from records_tbl as A, cars_tbl as B" +
                "WHERE A.date > (" +
                "    strftime('%s', datetime('now', '-6 month')) * 1000" +
                ")" +
                "AND" +
                "A.carId = B._id" +
                "AND" +
                "B.selected = 1;";
        return queryRecords(sql);
    }

    @Override
    public List<RecordEntity> queryRecordsThreeMonth() {
        String sql = "SELECT A.date, A.odometer, A.price, A.yuan, A.carId, datetime(A.date / 1000, 'unixepoch')" +
                "from records_tbl as A, cars_tbl as B" +
                "WHERE A.date > (" +
                "    strftime('%s', datetime('now', '-3 month')) * 1000" +
                ")" +
                "AND" +
                "A.carId = B._id" +
                "AND" +
                "B.selected = 1;";
        return queryRecords(sql);
    }

    /**
     * 查询的油钱
     *
     * @param sql 查询语句
     * @return 相关数据集合
     */
    private List<MoneyEntity> queryRecordsMoney(String sql) {
        List<MoneyEntity> entities = new ArrayList<>();
        SQLiteDatabase db = mHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {

            int indexTime = cursor.getColumnIndex("time");
            int indexMoney = cursor.getColumnIndex("money");

            do {

                String time = cursor.getString(indexTime);
                float money = cursor.getFloat(indexMoney);
                MoneyEntity moneyEntity = new MoneyEntity();
                moneyEntity.setMoney(money);
                moneyEntity.setTime(time);
                entities.add(moneyEntity);

            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();

        return entities;
    }

    @Override
    public List<MoneyEntity> queryRecordsMoneyEachYear() {
        String sql = "select" +
                "strftime('%Y-%m',datetime(A.date / 1000, 'unixepoch'),'localtime') time," +
                "sum(A.yuan) money" +
                "from records_tbl as A, cars_tbl as B" +
                "    where A.carId = B._id and B.selected = 1" +
                "GROUP BY tome" +
                "ORDER BY time;";

        return queryRecordsMoney(sql);
    }

    @Override
    public List<MoneyEntity> queryRecordMoneyEachMonth() {
        String sql = "select" +
                "strftime('%Y-%m',datetime(A.date / 1000, 'unixepoch'),'localtime') time," +
                "sum(A.yuan) money" +
                "from records_tbl as A, cars_tbl as B" +
                "    where A.carId = B._id and B.selected = 1" +
                "GROUP BY tome" +
                "ORDER BY time;";

        return queryRecordsMoney(sql);
    }
}
