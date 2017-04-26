package net.lidongdong.bearoil.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.lidongdong.bearoil.entity.CarEntity;

import java.util.ArrayList;
import java.util.List;

/**
*
*
*  @authorlidongdong(A handsome man)
*  @ date 17/4/25
*  @ explain 
*  @ function 
*  @ version 1.0
*   
*/
class CarOperationAndroid implements TableCarOperation {
    private DatabaseManager mDatabaseManager;

    CarOperationAndroid(SQLiteOpenHelper helper) {
        mDatabaseManager = DatabaseManager.getInstance(helper);
    }

    @Override
    public void addCar(CarEntity car) {

        SQLiteDatabase db = mDatabaseManager.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (car.get_id() != 0) {
            values.put(BearSQLiteValues.CAR_ID, car.get_id());
        }
        values.put(BearSQLiteValues.NAME, car.getName());
        values.put(BearSQLiteValues.SELECTED, car.getSelect());
        values.put(BearSQLiteValues.MODEL, car.getModel());
        values.put(BearSQLiteValues.UUID, car.getUuid());
        db.insert(BearSQLiteValues.CARS_TBL, null, values);

        mDatabaseManager.closeDatabase();
    }

    @Override
    public void removeCar(int id) {
        SQLiteDatabase db = mDatabaseManager.getWritableDatabase();
        String whereClause = BearSQLiteValues.CAR_ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.delete(BearSQLiteValues.CARS_TBL, whereClause, whereArgs);
        mDatabaseManager.closeDatabase();
    }

    @Override
    public void updateCar(CarEntity car) {
        updateCar(mDatabaseManager.getWritableDatabase(), car);
        mDatabaseManager.closeDatabase();

    }

    private void updateCar(SQLiteDatabase db, CarEntity car) {
        ContentValues values = new ContentValues();
        values.put(BearSQLiteValues.NAME, car.getName());
        values.put(BearSQLiteValues.SELECTED, car.getSelect());
        values.put(BearSQLiteValues.MODEL, car.getModel());
        values.put(BearSQLiteValues.UUID, car.getUuid());
        String whereClause = BearSQLiteValues.CAR_ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(car.get_id())};
        db.update(BearSQLiteValues.CARS_TBL, values, whereClause, whereArgs);
    }

    @Override
    public List<CarEntity> queryCars() {
        List<CarEntity> cars = new ArrayList<>(5);
        SQLiteDatabase db = mDatabaseManager.getWritableDatabase();
        Cursor cursor = db.query(BearSQLiteValues.CARS_TBL, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int indexId = cursor.getColumnIndex(BearSQLiteValues.CAR_ID);
            int indexName = cursor.getColumnIndex(BearSQLiteValues.NAME);
            int indexSelected = cursor.getColumnIndex(BearSQLiteValues.SELECTED);
            int indexModel = cursor.getColumnIndex(BearSQLiteValues.MODEL);
            int indexUUID = cursor.getColumnIndex(BearSQLiteValues.UUID);
            do {
                int id = cursor.getInt(indexId);
                String name = cursor.getString(indexName);
                int selected = cursor.getInt(indexSelected);
                int model = cursor.getInt(indexModel);
                String uuid = cursor.getString(indexUUID);
                CarEntity car = new CarEntity(id);
                car.setName(name);
                car.setSelect(selected);
                car.setModel(model);
                car.setUuid(uuid);

                cars.add(car);
            } while (cursor.moveToNext());
            cursor.close();
        }
        mDatabaseManager.closeDatabase();
        return cars;
    }

    @Override
    public CarEntity querySelectedCar() {
        return querySelectedCar(mDatabaseManager.getWritableDatabase());
    }

    // 查询出当前选中的小车
    private CarEntity querySelectedCar(SQLiteDatabase db) {
        String selection = BearSQLiteValues.SELECTED + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(1)};
        Cursor cursor = db.query(
                BearSQLiteValues.CARS_TBL,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            int indexId = cursor.getColumnIndex(BearSQLiteValues.CAR_ID);
            int indexName = cursor.getColumnIndex(BearSQLiteValues.NAME);
            int indexSelected = cursor.getColumnIndex(BearSQLiteValues.SELECTED);
            int indexModel = cursor.getColumnIndex(BearSQLiteValues.MODEL);
            int indexUUID = cursor.getColumnIndex(BearSQLiteValues.UUID);
            int id = cursor.getInt(indexId);
            String name = cursor.getString(indexName);
            int selected = cursor.getInt(indexSelected);
            int model = cursor.getInt(indexModel);
            String uuid = cursor.getString(indexUUID);
            CarEntity car = new CarEntity(id);
            car.setName(name);
            car.setSelect(selected);
            car.setModel(model);
            car.setUuid(uuid);
            return car;
        }
        assert cursor != null;
        cursor.close();
        mDatabaseManager.closeDatabase();

        return null;
    }

    @Override
    public void changeSelectedCar(int id) {
        SQLiteDatabase db = mDatabaseManager.getWritableDatabase();
        // 先把原来选中的车辆设置为不选中
        CarEntity currentSelectedCar = querySelectedCar(db);
        if (currentSelectedCar != null) {
            currentSelectedCar.setSelect(0);
            updateCar(db,  currentSelectedCar);
        }
        // 将新选中的车设置为选中状态
        ContentValues values = new ContentValues();
        values.put(BearSQLiteValues.SELECTED, 1);
        String whereClause = BearSQLiteValues.CAR_ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.update(BearSQLiteValues.CARS_TBL,
                values,
                whereClause,
                whereArgs);
        mDatabaseManager.closeDatabase();
    }

    @Override
    public void changeSelectedCar(CarEntity newSelectedCar) {
        SQLiteDatabase db = mDatabaseManager.getWritableDatabase();
        // 先把原来选中的车辆设置为不选中
        CarEntity currentSelectedCar = querySelectedCar(db);
        if (currentSelectedCar != null) {
            currentSelectedCar.setSelect(0);
        }
        updateCar(db, currentSelectedCar);
        // 将新选中的车设置为选中状态
        newSelectedCar.setSelect(1);
        updateCar(db,newSelectedCar);
        mDatabaseManager.closeDatabase();
    }




}
