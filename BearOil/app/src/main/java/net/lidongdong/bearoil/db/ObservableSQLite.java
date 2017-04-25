package net.lidongdong.bearoil.db;

import net.lidongdong.bearoil.entity.CarEntity;
import net.lidongdong.bearoil.entity.MoneyEntity;
import net.lidongdong.bearoil.entity.RecordEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @ date 17/4/15
 * @ explain
 * @ function
 */

public class ObservableSQLite {


    /**
     * car表相关
     **/
    //insert car表
    public static void addCar(CarEntity car) {
        Observable.just(car).map((Function<CarEntity, Object>) carEntity -> {
            DatabaseTool.getInstance().addCar(carEntity);
            return carEntity;

        }).subscribeOn(Schedulers.io()).subscribe();
    }

    //remove car 表
    public static void removeCar(int id) {
        Observable.just(id).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                DatabaseTool.getInstance().removeCar(integer);
                return "mm";
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    //update Car 表
    public static void updateCar(CarEntity car) {
        Observable.just(car).map(new Function<CarEntity, String>() {
            @Override
            public String apply(@NonNull CarEntity carEntity) throws Exception {
                DatabaseTool.getInstance().updateCar(carEntity);
                return null;
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }



    //query所有车
    public static Observable<List<CarEntity>> queryAllCar() {
        return Observable.just("").map(s -> DatabaseTool.getInstance().queryCars());
    }

    //query 当前选中的车
    public static Observable<CarEntity> querySelectedCar(){
        return Observable.just("").map(s -> DatabaseTool.getInstance().querySelectedCar());
    }

    //change 当前选中的车(通过 id)
    public static void changeSelectCar(int id){
        Observable.just(id).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(@NonNull Integer integer) throws Exception {
                DatabaseTool.getInstance().changeSelectedCar(integer);
                return integer;
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    //change 当前选中的车(参数是 CarEntity)
    public static void changeSelectCar(CarEntity car){
        Observable.just(car).map(new Function<CarEntity, String>() {
            @Override
            public String apply(@NonNull CarEntity carEntity) throws Exception {
                DatabaseTool.getInstance().changeSelectedCar(carEntity);
                return "fff";
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }


    /**
     * record表相关
     **/

    //添加当前record
    public static void addRecord(RecordEntity record) {
        Observable.just(record).map(new Function<RecordEntity, String>() {
            @Override
            public String apply(@NonNull RecordEntity recordEntity) throws Exception {
                DatabaseTool.getInstance().addRecord(recordEntity);
                return "我的大爷";
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    //删除当前record
    public static void deleteRecord(int id) {
        Observable.just(id).map(integer -> {
            DatabaseTool.getInstance().removeRecord(integer);
            return null;
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    //update当前的 record
    public static void update(RecordEntity record) {
        Observable.just(record).map(new Function<RecordEntity, String>() {
            @Override
            public String apply(@NonNull RecordEntity recordEntity) throws Exception {
                DatabaseTool.getInstance().updateRecords(recordEntity);
                return null;
            }
        }).subscribeOn(Schedulers.io()).subscribe();

    }
    //根据 id 查询车的记录表

    public static Observable<RecordEntity> queryRecord(int id){
        return Observable.just(id).map(integer -> DatabaseTool.getInstance().queryRecord(integer));
    }

    //查询当前选中车辆的所有信息
    public static Observable<List<RecordEntity>> queryRecords() {
        return Observable.just("").map(s -> DatabaseTool.getInstance().queryRecords());
    }

    //查询当前选中车辆每年的信息
    public static Observable<List<RecordEntity>> queryRecordsEachYear() {
        return Observable.just("").map(s -> DatabaseTool.getInstance().queryRecordsEachYear());
    }

    //查询当前车辆每半年的信息
    public static Observable<List<RecordEntity>> queryRecordsEachHalfOfYear() {
        return Observable.just("").map(s -> DatabaseTool.getInstance().queryRecordsEachHalfOfYear());
    }

    //查询当前车辆每三个月的信息
    public static Observable<List<RecordEntity>> queryRecordsEachThreeMonth() {
        return Observable.just("").map(s -> DatabaseTool.getInstance().queryRecordsThreeMonth());
    }

    //查询当前车辆每一年的油费
    public static Observable<List<MoneyEntity>> queryRecordMoneyEachYear() {
        return Observable.just("").map(s -> DatabaseTool.getInstance().queryRecordsMoneyEachYear());
    }


    //查询当前车辆每个月的油费
    public static Observable<List<MoneyEntity>> queryRecordMoneyEachMonth() {
        return Observable.just("").map(s -> DatabaseTool.getInstance().queryRecordMoneyEachMonth());
    }

}
