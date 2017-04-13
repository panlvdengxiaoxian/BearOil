package net.lidongdong.bearoil.db;


import net.lidongdong.bearoil.app.BearApplication;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @date 17/4/13
 * @explain
 * @function
 */

public class DatabaseTool {

    private static class SingletonHolder {
        private static final DatabaseTool INSTANCE = new DatabaseTool();
    }

    public static DatabaseTool getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private BearSQLiteHelper mHelper;
    private TableCarOperation mTableCarOperation;
    private TableRecordOperation mTableRecordOperation;

    private DatabaseTool() {
        mHelper = new BearSQLiteHelper(BearApplication.getContext());
        mTableCarOperation = new CarOperationAndroid(mHelper);
        mTableRecordOperation=new RecordOperationAndroid(mHelper);
    }

    public void addCar(CarEntity car) {
        mTableCarOperation.addCar(car);
    }

    public void removeCar(int id) {
        mTableCarOperation.removeCar(id);
    }

    public void updateCar(CarEntity car) {
        mTableCarOperation.updateCar(car);
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

