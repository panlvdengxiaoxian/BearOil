package net.lidongdong.bearoil.entity;

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
public class RecordEntity {

    private int _id;
    private int date;
    private int price;
    private int yuan;
    private int type;
    private int gasSup;
    private String remark;
    private int carId;
    private int forget;
    private int lightOn;
    private int stationId;
    private int odometer;

    @Override
    public String toString() {
        return "RecordEntity{" +
                "_id=" + _id +
                ", date=" + date +
                ", price=" + price +
                ", carId=" + carId +
                ", lightOn=" + lightOn +
                '}';
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public RecordEntity(int _id) {
        this._id = _id;
    }

    public int get_id() {
        return _id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYuan() {
        return yuan;
    }

    public void setYuan(int yuan) {
        this.yuan = yuan;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGasSup() {
        return gasSup;
    }

    public void setGasSup(int gasSup) {
        this.gasSup = gasSup;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getForget() {
        return forget;
    }

    public void setForget(int forget) {
        this.forget = forget;
    }

    public int getLightOn() {
        return lightOn;
    }

    public void setLightOn(int lightOn) {
        this.lightOn = lightOn;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }
}
