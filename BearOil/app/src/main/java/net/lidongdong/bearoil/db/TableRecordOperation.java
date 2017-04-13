package net.lidongdong.bearoil.db;

import java.util.List;

/**
*
*
*  @author lidongdong(一个帅的惊天动地的男人)
*  @date 17/4/13
*  @explain
*  @function
*  @version 1.0
*
*/

public interface TableRecordOperation {
    void addCar (RecordEntity record);
    void removeCar (int id);
    void updateCar(RecordEntity record);
    List<RecordEntity> queryCars ();
    RecordEntity querySelectedCar();
    void changeSelectedCar(int carId);
    void changeSelectedRecord(RecordEntity newRecordEntity);
}
