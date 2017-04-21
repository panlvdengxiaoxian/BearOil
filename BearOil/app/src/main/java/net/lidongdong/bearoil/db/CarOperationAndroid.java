package net.lidongdong.bearoil.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.lidongdong.bearoil.entity.CarEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @ date 17/4/13
 * @ explain
 * @ function
 */
class CarOperationAndroid implements TableCarOperation {
    private SQLiteOpenHelper mHelper;

    CarOperationAndroid(SQLiteOpenHelper helper) {
        mHelper = helper;
    }

    @Override
    public void addCar(CarEntity car) {

        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (car.get_id() != 0) {
            values.put(BearSQLiteValues._ID, car.get_id());
        }
        values.put(BearSQLiteValues.NAME, car.getName());
        values.put(BearSQLiteValues.SELECTED, car.getSelect());
        values.put(BearSQLiteValues.MODEL, car.getModel());
        values.put(BearSQLiteValues.UUID, car.getUuid());
        db.insert(BearSQLiteValues.CARS_TBL, null, values);
      //  closeDatabase(db);
    }

    @Override
    public void removeCar(int id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String whereClause = BearSQLiteValues._ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.delete(BearSQLiteValues.CARS_TBL, whereClause, whereArgs);
      //  closeDatabase(db);
    }

    @Override
    public void updateCar(CarEntity car) {
        updateCar(openDatabase(), true, car);

    }

    private void updateCar(SQLiteDatabase db, boolean isClose, CarEntity car) {
        ContentValues values = new ContentValues();
        values.put(BearSQLiteValues.NAME, car.getName());
        values.put(BearSQLiteValues.SELECTED, car.getSelect());
        values.put(BearSQLiteValues.MODEL, car.getModel());
        values.put(BearSQLiteValues.UUID, car.getUuid());
        String whereClause = BearSQLiteValues._ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(car.get_id())};
        db.update(BearSQLiteValues.CARS_TBL, values, whereClause, whereArgs);
//        if (isClose) {
//            closeDatabase(db);
//        }
    }

    @Override
    public List<CarEntity> queryCars() {
        List<CarEntity> cars = new ArrayList<>(5);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.query(BearSQLiteValues.CARS_TBL, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int indexId = cursor.getColumnIndex(BearSQLiteValues._ID);
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
     //   closeDatabase(db);
        return cars;
    }

    @Override
    public CarEntity querySelectedCar() {
        return querySelectedCar(openDatabase(), true);
    }

    // 查询出当前选中的小车
    private CarEntity querySelectedCar(SQLiteDatabase db, boolean isClose) {
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
            int indexId = cursor.getColumnIndex(BearSQLiteValues._ID);
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
            cursor.close();
            return car;
        }

//        if (isClose) {
//            closeDatabase(db);
//        }
        return null;
    }

    @Override
    public void changeSelectedCar(int id) {
        SQLiteDatabase db = openDatabase();
        // 先把原来选中的车辆设置为不选中
        CarEntity currentSelectedCar = querySelectedCar(db, false);
        if (currentSelectedCar != null) {
            currentSelectedCar.setSelect(0);
            updateCar(db, false, currentSelectedCar);
        }
        // 将新选中的车设置为选中状态
        ContentValues values = new ContentValues();
        values.put(BearSQLiteValues.SELECTED, 1);
        String whereClause = BearSQLiteValues._ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.update(BearSQLiteValues.CARS_TBL,
                values,
                whereClause,
                whereArgs);
   //     closeDatabase(db);
    }

    @Override
    public void changeSelectedCar(CarEntity newSelectedCar) {
        SQLiteDatabase db = openDatabase();
        // 先把原来选中的车辆设置为不选中
        CarEntity currentSelectedCar = querySelectedCar(db, false);
        if (currentSelectedCar != null) {
            currentSelectedCar.setSelect(0);
        }
        updateCar(db, false, currentSelectedCar);
        // 将新选中的车设置为选中状态
        newSelectedCar.setSelect(1);
        updateCar(db, false, newSelectedCar);
   //     closeDatabase(db);
    }

    private SQLiteDatabase openDatabase() {
        return mHelper.getWritableDatabase();
    }

    private void closeDatabase(SQLiteDatabase db) {
        if (db != null) {
            db.close();
        }
    }

}
