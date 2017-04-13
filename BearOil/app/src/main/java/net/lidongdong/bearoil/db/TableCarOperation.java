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

public interface TableCarOperation {

    void addCar (CarEntity car);
    void removeCar (int id);
    void updateCar(CarEntity car);
    List<CarEntity> queryCars ();
    CarEntity querySelectedCar();
    void changeSelectedCar(int id);
    void changeSelectedCar(CarEntity newSelectedCar);


}
