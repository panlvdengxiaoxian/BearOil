package net.lidongdong.bearoil.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import net.lidongdong.bearoil.R;
import net.lidongdong.bearoil.db.DatabaseTool;
import net.lidongdong.bearoil.db.ObservableSQLite;
import net.lidongdong.bearoil.entity.CarEntity;
import net.lidongdong.bearoil.entity.RecordEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecordEntity recordEntity=new RecordEntity(0);
        recordEntity.setDate(1);
        recordEntity.setStationId(1);
        recordEntity.setLightOn(1);
        recordEntity.setForget(1);
        recordEntity.setYuan(1);
        recordEntity.setCarId(1);
        recordEntity.setGasSup(1);
        recordEntity.setOdometer(4);
        recordEntity.setType(4);
        DatabaseTool.getInstance().addRecord(recordEntity);
      //  Log.d("xxx", "DatabaseTool.getInstance().queryRecords():" + DatabaseTool.getInstance().queryRecords());

        ObservableSQLite.addCar(new CarEntity(0,"无敌是多么寂寞",6));

        ObservableSQLite.queryAllCar().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(carEntities -> Log.d("xxx", "carEntities:" + carEntities));

    }
}
