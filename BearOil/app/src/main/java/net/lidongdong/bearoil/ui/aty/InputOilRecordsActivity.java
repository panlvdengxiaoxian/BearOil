package net.lidongdong.bearoil.ui.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.lidongdong.bearoil.R;
import net.lidongdong.bearoil.db.ObservableSQLite;
import net.lidongdong.bearoil.entity.RecordEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InputOilRecordsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView titleName;
    private ImageView saveIv;
    private EditText recordsDateEt;
    private EditText recordsOdometerEt;
    private EditText recordsPriceEt;
    private EditText recordsYuanEt;
    private EditText recordsGassupEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_oil_records);
        initView();
        setClick();
        initData();
    }

    private void initData() {

        ObservableSQLite.querySelectedCar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carEntity -> titleName.setText(carEntity.getName()));

    }

    private void setClick() {
        saveIv.setOnClickListener(this);
    }

    private void initView() {

        titleName = (TextView) findViewById(R.id.title_name);
        saveIv = (ImageView) findViewById(R.id.save_iv);
        recordsDateEt = (EditText) findViewById(R.id.records_date_et);
        recordsOdometerEt = (EditText) findViewById(R.id.records_odometer_et);
        recordsPriceEt = (EditText) findViewById(R.id.records_price_et);
        recordsYuanEt = (EditText) findViewById(R.id.records_yuan_et);
        recordsGassupEt = (EditText) findViewById(R.id.records_gassup_et);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_iv:
                //保存数据
                saveData();

                break;
        }
    }

    private void saveData() {
        RecordEntity recordEntity = new RecordEntity(0);

        if (recordsGassupEt.getText() != null &&
                recordsOdometerEt.getText() != null &&
                recordsDateEt.getText() != null &&
                recordsYuanEt.getText() != null &&
                recordsPriceEt.getText() != null){

            recordEntity.setDate(recordsDateEt.getText().toString());
            recordEntity.setOdometer(recordsOdometerEt.getText().toString());
            recordEntity.setPrice(recordsPriceEt.getText().toString());
            recordEntity.setYuan(Float.parseFloat(recordsYuanEt.getText().toString()));
            recordEntity.setGasSup(recordsGassupEt.getText().toString());
            ObservableSQLite.querySelectedCar()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(carEntity ->{
                                recordEntity.setCarId(carEntity.get_id());
                                ObservableSQLite.addRecord(recordEntity);
                            }
                        );





            //发送广播通知数据已保存,刷新 ui
            Intent intent=new Intent();
            intent.setAction("UPDATE_RECORDS");
            sendBroadcast(intent);

            finish();

        }else {
            Toast.makeText(this, "输入有 null,请检查输入数据!!", Toast.LENGTH_SHORT).show();
        }


    }


}
