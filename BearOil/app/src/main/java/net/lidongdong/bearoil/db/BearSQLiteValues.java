package net.lidongdong.bearoil.db;

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
 class BearSQLiteValues {
    static final String NAME_DB = "oil.db";
    static final int VERSION = 8;

    static final String CARS_TBL = "cars_tbl";
    static final String RECORDS_TBL = "records_tbl";
    //Car 相关
    static final String CAR_ID = "_id";
    static final String NAME = "name";
    static final String SELECTED = "selected";
    static final String MODEL = "model";
    static final String UUID = "uuid";
    //建表语句
    static final String SQL_CAR= "create table " + CARS_TBL + " (" + CAR_ID +
            " integer primary key autoincrement," +
            NAME + " text not null," +
            SELECTED + " integer not null," +
            MODEL + " integer," +
            UUID + " integer);";
    //records 相关
    static final String RECORD_ID="_id";
    static final String DATE="date";
    static final String ODOMETER="odometer";
    static final String PRICE="price";
    static final String YUAN="yuan";
    static final String TYPE="type";
    static final String GASSUP="gassup";
    static final String REMARK="remark";
    static final String CARID="carId";
    static final String FORGET="forget";
    static final String LIGHTON="lightOn";
    static final String STATIONID="stationId";

    //建表语句
    static final String SQL_RECORD= "create table " + RECORDS_TBL + " (" + RECORD_ID +
            " integer primary key autoincrement," +
            DATE + " text not null," +
            ODOMETER + " text not null," +
            PRICE + " text not null," +
            YUAN + " Integer not null," +
            TYPE + " integer," +
            GASSUP + " text," +
            REMARK + " text," +
            CARID + " text not null," +
            FORGET + " integer," +
            LIGHTON + " integer," +
            STATIONID + " integer );";
}
