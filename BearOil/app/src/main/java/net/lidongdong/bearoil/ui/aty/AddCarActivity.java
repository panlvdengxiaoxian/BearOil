package net.lidongdong.bearoil.ui.aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import net.lidongdong.bearoil.R;
import net.lidongdong.bearoil.db.ObservableSQLite;
import net.lidongdong.bearoil.entity.CarEntity;

public class AddCarActivity extends AppCompatActivity {
    private ImageView saveIv;
    private EditText addNameEt;
    private EditText addBrandEt;
    private EditText addCategoryEt;
    private EditText addModelEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        initView();
        //保存数据
        saveCarData();
    }

    private void saveCarData() {

        saveIv.setOnClickListener(v -> {
             String addNameStr=addNameEt.getText().toString();
             String addBrandStr=addBrandEt.getText().toString();
             String addCategoryStr=addCategoryEt.getText().toString();
             String addModelStr=addModelEt.getText().toString();
             CarEntity carEntity=new CarEntity();
             if (addNameStr.length()>0){
                 carEntity.setName(addNameStr);
                 carEntity.setSelect(0);
                 carEntity.setModel(10010);
                 carEntity.setUuid("10086");
                 ObservableSQLite.addCar(carEntity);
            }

            Intent updateUIIntent=new Intent();
            updateUIIntent.setAction("UPDATE_UI");
            sendBroadcast(updateUIIntent);

            Intent intent=new Intent(AddCarActivity.this,MainActivity.class);
            intent.putExtra("flag",2);
            finish();
            setResult(1000,intent);

        });
    }

    private void initView() {

        saveIv = (ImageView) findViewById(R.id.save_iv);
        addNameEt = (EditText) findViewById(R.id.add_name_et);
        addBrandEt = (EditText) findViewById(R.id.add_brand_et);
        addCategoryEt = (EditText) findViewById(R.id.add_category_et);
        addModelEt = (EditText) findViewById(R.id.add_model_et);


    }
}
