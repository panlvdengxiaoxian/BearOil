package net.lidongdong.bearoil.ui.aty;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import net.lidongdong.bearoil.R;
import net.lidongdong.bearoil.adapter.AddCarPopupWindowAdapter;
import net.lidongdong.bearoil.db.ObservableSQLite;
import net.lidongdong.bearoil.entity.BrandEntity;
import net.lidongdong.bearoil.entity.CarEntity;
import net.lidongdong.bearoil.net.HttpManger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
/**
*
*
*  @authorlidongdong(A handsome man)
*  @ date 17/4/25
*  @ explain 
*  @ function 
*  @ version 1.0
*   
*/
public class AddCarActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView saveIv;
    private EditText addNameEt;
    private TextView addBrandEt;
    private TextView addCategoryEt;
    private TextView addModelEt;
    private int mIndex = 0;
    private int mNum = 0;
    private int mNums = 0;
    private TextView mEngine;
    private TextView mTransmission;
    private AddCarPopupWindowAdapter mAdapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        initView();
        saveCarData();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveCarData() {

        saveIv.setOnClickListener(v -> {
            String addNameStr = addNameEt.getText().toString();
            CarEntity carEntity = new CarEntity();
            if (addNameStr.length() > 0) {
                carEntity.setName(addNameStr);
                carEntity.setSelect(0);
                carEntity.setModel(10010);
                carEntity.setUuid("10086");
                ObservableSQLite.addCar(carEntity);

            }


            Intent updateUIIntent = new Intent();
            updateUIIntent.setAction("UPDATE_UI");
            sendBroadcast(updateUIIntent);

            Intent intent = new Intent(AddCarActivity.this, MainActivity.class);
            intent.putExtra("flag", 2);
            finish();
            setResult(1000, intent);

        });
    }

    private void initView() {

        saveIv = (ImageView) findViewById(R.id.save_iv);
        addNameEt = (EditText) findViewById(R.id.add_name_et);
        addBrandEt = (TextView) findViewById(R.id.add_brand_et);
        addCategoryEt = (TextView) findViewById(R.id.add_category_et);
        addModelEt = (TextView) findViewById(R.id.add_model_et);
        mEngine = (TextView) findViewById(R.id.engine);
        mTransmission = (TextView) findViewById(R.id.transmission);

        addBrandEt.setOnClickListener(this);
        addCategoryEt.setOnClickListener(this);
        addModelEt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_brand_et:
                HttpManger.getInstance().getBearOilApi().getBrands()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(brandEntity -> showPopup(v, brandEntity));

                addCategoryEt.setText("未选");
                addModelEt.setText("未选");
                mEngine.setVisibility(View.INVISIBLE);
                mTransmission.setVisibility(View.INVISIBLE);
                break;
            case R.id.add_category_et:
                HttpManger.getInstance().getBearOilApi().getCarSeries(mIndex)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(brandEntity -> showPopup(v, brandEntity));
                addModelEt.setText("未选");

                break;
            case R.id.add_model_et:
                HttpManger.getInstance().getBearOilApi().getCarType(mIndex, mNum)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(brandEntity -> showPopup(v, brandEntity));


                break;
        }
    }

    private void showPopup(View parent, BrandEntity brandEntity) {
        //加载布局
        LinearLayout layout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.list_popup_window, null);
        ListView listView = (ListView) layout.findViewById(R.id.lv_dialog);
        mAdapter = new AddCarPopupWindowAdapter(this);

        mAdapter.setBrandEntity(brandEntity);
        listView.setAdapter(mAdapter);

        //实例化 popupWindow
        PopupWindow popupWindow = new PopupWindow(layout, 500, WindowManager.LayoutParams.WRAP_CONTENT);
        //控制键盘是否可以获取焦点
        popupWindow.setFocusable(true);
        //设置 popupWindow 弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
   //     WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        @SuppressWarnings("deprecation")
        //获取 xOff
      //          int xPos = manager.getDefaultDisplay().getWidth() / 2 - popupWindow.getWidth() / 2;
        //xOff,yOff基于 anchor 的左下角进行偏移.
        TextView tv = (TextView) LayoutInflater.from(this).inflate(android.R.layout.simple_list_item_1, null);
        tv.setText("未选");
        listView.addHeaderView(tv);

        if (Build.VERSION.SDK_INT < 24) {
            popupWindow.showAsDropDown(parent, 0, 0);
        } else {
            int[] location = new int[2];
            parent.getLocationOnScreen(location);
            popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, 0, location[1] + parent.getHeight());
        }

        switch (parent.getId()) {
            case R.id.add_brand_et:
                listView.setOnItemClickListener((parent1, view, position, id) -> {
                    addBrandEt.setText(String.valueOf(mAdapter.getItem(position - 1)));
                    mIndex = brandEntity.getIdxes().get(position - 1);
                    addCategoryEt.setClickable(true);
                    popupWindow.dismiss();
                });
                break;
            case R.id.add_category_et:
                listView.setOnItemClickListener((parent1, view, position, id) -> {
                    addCategoryEt.setText(String.valueOf(mAdapter.getItem(position - 1)));
                    mNum = brandEntity.getIdxes().get(position - 1);
                    addModelEt.setClickable(true);
                    popupWindow.dismiss();
                });
                break;
            case R.id.add_model_et:
                listView.setOnItemClickListener((parent1, view, position, id) -> {
                    addModelEt.setText(String.valueOf(mAdapter.getItem(position - 1)));
                    mNums = brandEntity.getIdxes().get(position - 1);


                    HttpManger.getInstance().getBearOilApi().getCarDetail(mNums)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(carDetailEntity -> {
                                if (carDetailEntity != null) {
                                    mEngine.setText(carDetailEntity.getEngine());
                                    mTransmission.setText(carDetailEntity.getGearbox());
                                }
                            });
                    mEngine.setVisibility(View.VISIBLE);
                    mTransmission.setVisibility(View.VISIBLE);

                    popupWindow.dismiss();
                });

                break;
        }


    }
}
