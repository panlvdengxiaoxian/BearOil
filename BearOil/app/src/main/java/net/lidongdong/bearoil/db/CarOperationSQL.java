package net.lidongdong.bearoil.db;

import net.lidongdong.bearoil.entity.CarEntity;

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

public class CarOperationSQL implements TableCarOperation {
    @Override
    public void addCar(CarEntity car) {

    }

    @Override
    public void removeCar(int id) {

    }

    @Override
    public void updateCar(CarEntity car) {

    }

    @Override
    public List<CarEntity> queryCars() {
        return null;
    }

    @Override
    public CarEntity querySelectedCar() {
        return null;
    }

    @Override
    public void changeSelectedCar(int id) {

    }

    @Override
    public void changeSelectedCar(CarEntity newSelectedCar) {

    }
}
