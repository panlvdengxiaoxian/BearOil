package net.lidongdong.bearoil.db;


import net.lidongdong.bearoil.app.BearApplication;
import net.lidongdong.bearoil.entity.CarEntity;
import net.lidongdong.bearoil.entity.MoneyEntity;
import net.lidongdong.bearoil.entity.RecordEntity;

import java.util.List;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @date 17/4/13
 * @explain
 * @function
 */

public class DatabaseTool implements TableRecordOperation, TableCarOperation {

    /**
     * 静态内部类单例写法
     */
    private static class SingletonHolder {
        private static final DatabaseTool INSTANCE = new DatabaseTool();
    }

    public static DatabaseTool getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 另外一种单例的写法
     */
//    private static final AtomicReference<DatabaseTool> INSTANCE = new AtomicReference<>();
//
//    public static DatabaseTool getInstance() {
//        for (; ; ) {
//            DatabaseTool current = INSTANCE.get();
//            if (current != null) {
//                return current;
//            }
//            current = new DatabaseTool();
//            if (INSTANCE.compareAndSet(null, current)) {
//                return current;
//            }
//        }
//    }


    private BearSQLiteHelper mHelper;
    private TableCarOperation mTableCarOperation;
    private final RecordOperationSQL mOperationSQL;

    //单例私有构造方法
    private DatabaseTool() {
        mHelper = new BearSQLiteHelper(BearApplication.getContext());
        mTableCarOperation = new CarOperationAndroid(mHelper);
        mOperationSQL = new RecordOperationSQL(mHelper);
    }

    //记录表相关

    @Override
    public void addRecord(RecordEntity record) {
        mOperationSQL.addRecord(record);
    }

    @Override
    public void removeRecord(int id) {
        mOperationSQL.removeRecord(id);
    }

    @Override
    public void updateRecords(RecordEntity record) {
        updateRecords(record);
    }

    @Override
    public RecordEntity queryRecord(int id) {
        return mOperationSQL.queryRecord(id);
    }

    @Override
    public List<RecordEntity> queryRecords() {
        return mOperationSQL.queryRecords();
    }

    @Override
    public List<RecordEntity> queryRecordsEachYear() {
        return mOperationSQL.queryRecordsEachYear();
    }

    @Override
    public List<RecordEntity> queryRecordsEachHalfOfYear() {
        return mOperationSQL.queryRecordsEachHalfOfYear();
    }

    @Override
    public List<RecordEntity> queryRecordsThreeMonth() {
        return mOperationSQL.queryRecordsThreeMonth();
    }

    @Override
    public List<MoneyEntity> queryRecordsMoneyEachYear() {
        return mOperationSQL.queryRecordsMoneyEachYear();
    }

    @Override
    public List<MoneyEntity> queryRecordMoneyEachMonth() {
        return mOperationSQL.queryRecordMoneyEachMonth();
    }


    //汽车表相关

    public void addCar(CarEntity car) {
        mTableCarOperation.addCar(car);
    }


    public void removeCar(int id) {
        mTableCarOperation.removeCar(id);
    }

    public void updateCar(CarEntity car) {
        mTableCarOperation.updateCar(car);
    }

    @Override
    public List<CarEntity> queryCars() {
        return mTableCarOperation.queryCars();
    }


    public CarEntity querySelectedCar() {
        return mTableCarOperation.querySelectedCar();
    }

    // 更改选中的车辆
    public void changeSelectedCar(int id) {
        mTableCarOperation.changeSelectedCar(id);
    }

    public void changeSelectedCar(CarEntity newSelectedCar) {
        mTableCarOperation.changeSelectedCar(newSelectedCar);
    }

}

