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
    private String date;
    private float price;
    private float yuan;
    private int type;
    private String gasSup;
    private String remark;
    private int carId;
    private int forget;
    private int lightOn;
    private int stationId;
    private String odometer;

    public RecordEntity(int _id) {
        this._id = _id;
    }

    public RecordEntity() {
    }

    @Override
    public String toString() {
        return "RecordEntity{" +
                "_id=" + _id +
                ", date='" + date + '\'' +
                ", price='" + price + '\'' +
                ", yuan='" + yuan + '\'' +
                ", gasSup='" + gasSup + '\'' +
                ", carId='" + carId + '\'' +
                ", odometer='" + odometer + '\'' +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getYuan() {
        return yuan;
    }

    public void setYuan(float yuan) {
        this.yuan = yuan;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGasSup() {
        return gasSup;
    }

    public void setGasSup(String gasSup) {
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

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }
}
