package net.lidongdong.bearoil.ui.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.lidongdong.bearoil.R;
import net.lidongdong.bearoil.db.ObservableSQLite;
import net.lidongdong.bearoil.entity.RecordEntity;
import net.lidongdong.bearoil.ui.view.LinearChartView;
import net.lidongdong.bearoil.utils.DataCalculationUntil;
import net.lidongdong.bearoil.utils.RotationUntil;
import net.lidongdong.bearoil.utils.TimeUntil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @ date 17/4/19
 * @ explain
 * @ function
 */
public class FuelConsumptionsFragment extends Fragment implements View.OnClickListener {

    private TextView fuelConsumptionsNameTv;
    private ImageView fuelConsumptionsLeftIv;
    private ImageView fuelConsumptionsRightIv;
    private LinearChartView chartView;
    private TextView defaultName;
    private TextView avgOilTv;
    private TextView currentKmTv;
    private TextView maxOilTv;
    private TextView allKmTv;
    private TextView minOilTv;
    private TextView allOilTv;
    private TextView recentOilTv;
    private TextView avgKmTv;

    private ImageView fuelImg1;
    private ImageView fuelImg2;
    private ImageView fuelImg3;
    private ImageView fuelImg4;
    private ImageView fuelImg5;
    private UpdateChartBroadcastReceiver mReceiver;
    private List<RecordEntity> mRecordEntityList;

    public FuelConsumptionsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        fuelConsumptionsNameTv = (TextView) view.findViewById(R.id.fuel_consumptions_name_tv);
        fuelConsumptionsLeftIv = (ImageView) view.findViewById(R.id.fuel_consumptions_left_iv);
        fuelConsumptionsRightIv = (ImageView) view.findViewById(R.id.fuel_consumptions_right_iv);
        chartView = (LinearChartView) view.findViewById(R.id.chart_view);
        defaultName = (TextView) view.findViewById(R.id.default_name);
        avgOilTv = (TextView) view.findViewById(R.id.avg_oil_tv);
        currentKmTv = (TextView) view.findViewById(R.id.current_km_tv);
        maxOilTv = (TextView) view.findViewById(R.id.max_oil_tv);
        allKmTv = (TextView) view.findViewById(R.id.all_km_tv);
        minOilTv = (TextView) view.findViewById(R.id.min_oil_tv);
        allOilTv = (TextView) view.findViewById(R.id.all_oil_tv);
        recentOilTv = (TextView) view.findViewById(R.id.recent_oil_tv);
        avgKmTv = (TextView) view.findViewById(R.id.avg__km_tv);


        fuelImg1 = (ImageView) view.findViewById(R.id.fuel_img1);
        fuelImg2 = (ImageView) view.findViewById(R.id.fuel_img2);
        fuelImg3 = (ImageView) view.findViewById(R.id.fuel_img3);
        fuelImg4 = (ImageView) view.findViewById(R.id.fuel_img4);
        fuelImg5 = (ImageView) view.findViewById(R.id.fuel_img5);


        fuelConsumptionsLeftIv.setOnClickListener(this);
        fuelConsumptionsRightIv.setOnClickListener(this);

        mRecordEntityList = new ArrayList<>();

        mReceiver = new UpdateChartBroadcastReceiver();
        IntentFilter filter = new IntentFilter("UPDATE_CHART");
        getContext().registerReceiver(mReceiver, filter);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        queryAllRecords();

    }

    int count = 1;
    int num = 4;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.fuel_consumptions_right_iv:
                if (count == 0) {
                    RotationUntil .switchRound(getContext(),count,fuelConsumptionsNameTv,fuelImg1,fuelImg2,fuelImg3,fuelImg4,fuelImg5);
                    queryAllRecords();
                    num = 4;
                    count = 1;
                } else if (count == 1) {
                    RotationUntil .switchRound(getContext(),count,fuelConsumptionsNameTv,fuelImg1,fuelImg2,fuelImg3,fuelImg4,fuelImg5);
                    queryRecordsEachYear();
                    num = 0;
                    count = 2;

                } else if (count == 2) {
                    RotationUntil .switchRound(getContext(),count,fuelConsumptionsNameTv,fuelImg1,fuelImg2,fuelImg3,fuelImg4,fuelImg5);
                    queryRecordEachHalfYear();
                    num = 1;
                    count = 3;
                } else if (count == 3) {
                    RotationUntil .switchRound(getContext(),count,fuelConsumptionsNameTv,fuelImg1,fuelImg2,fuelImg3,fuelImg4,fuelImg5);
                    queryRecordThreeMonth();
                    num = 2;
                    count = 4;
                } else if (count == 4) {
                    RotationUntil .switchRound(getContext(),count,fuelConsumptionsNameTv,fuelImg1,fuelImg2,fuelImg3,fuelImg4,fuelImg5);
                    num = 3;
                    count = 0;
                }

                break;

            case R.id.fuel_consumptions_left_iv:
                if (num == 0) {
                    RotationUntil .switchRound(getContext(),num,fuelConsumptionsNameTv,fuelImg1,fuelImg2,fuelImg3,fuelImg4,fuelImg5);
                    queryAllRecords();
                    count = 1;
                    num = 4;
                } else if (num == 4) {
                    RotationUntil .switchRound(getContext(),num,fuelConsumptionsNameTv,fuelImg1,fuelImg2,fuelImg3,fuelImg4,fuelImg5);
                    count = 0;
                    num = 3;
                } else if (num == 3) {
                    RotationUntil .switchRound(getContext(),num,fuelConsumptionsNameTv,fuelImg1,fuelImg2,fuelImg3,fuelImg4,fuelImg5);
                    queryRecordThreeMonth();
                    count = 4;
                    num = 2;
                } else if (num == 2) {
                    RotationUntil .switchRound(getContext(),num,fuelConsumptionsNameTv,fuelImg1,fuelImg2,fuelImg3,fuelImg4,fuelImg5);
                    queryRecordEachHalfYear();
                    count = 3;
                    num = 1;
                } else if (num == 1) {
                    RotationUntil .switchRound(getContext(),num,fuelConsumptionsNameTv,fuelImg1,fuelImg2,fuelImg3,fuelImg4,fuelImg5);
                 queryRecordsEachYear();
                    count = 2;
                    num = 0;
                }
                break;
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private void queryAllRecords() {
        ObservableSQLite.queryRecords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setDataSource);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void queryRecordsEachYear() {
        ObservableSQLite.queryRecordsEachYear()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setDataSource);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void queryRecordEachHalfYear() {
        ObservableSQLite.queryRecordsEachHalfOfYear()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setDataSource);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void queryRecordThreeMonth() {
        ObservableSQLite.queryRecordsEachThreeMonth()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setDataSource);
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setDataSource(List<RecordEntity> recordEntities){
        if (recordEntities.size() > 0) {

            chartView.setTimeUnit(TimeUntil.unitChartTime(recordEntities));
            chartView.setList(TimeUntil.unixTimeUnit(recordEntities));

            float data[] = new float[recordEntities.size()];
            float sum = 0;
            float max = data[0];
            float min = data[0];
            float kmSum = 0;
            for (int i = 0; i < recordEntities.size(); i++) {
                if (i > 1) {
                    String currentOdometer = recordEntities.get(i).getOdometer();
                    float nextYuan = recordEntities.get(i - 1).getYuan();
                    float nextPrice = recordEntities.get(i - 1).getPrice();
                    String nextOdometer = recordEntities.get(i - 1).getOdometer();
                    data[i] = DataCalculationUntil.kmData(nextYuan, nextPrice, currentOdometer, nextOdometer);
                    //求油耗和
                    sum = sum + data[i];

                    //最大值
                    if (data[i] > max) {
                        max = data[i];
                    }
                    //最小值
                    if (min>0) {
                        if (data[i] < min) {
                            min = data[i];
                        }
                    }
                    //求公里数之和
                    kmSum = kmSum + Float.valueOf(recordEntities.get(i).getOdometer());

                    //最近油耗
                    recentOilTv.setText(String.valueOf(new DecimalFormat(".00").format(data[2])));

                }
            }
            for (int stat = 0, end = data.length - 1; stat < end; stat++, end--) {
                float temp = data[end];
                data[end] = data[stat];
                data[stat] = temp;
            }

            //数据插入
            chartView.setDatas(data);

            //求平均数
            avgOilTv.setTextColor(Color.YELLOW);
            avgOilTv.setText(String.valueOf(new DecimalFormat(".00").format(sum / data.length)));

            //当前里程数
            currentKmTv.setText(recordEntities.get(0).getOdometer());

            //油耗的最大值
            maxOilTv.setText(String.valueOf(new DecimalFormat(".00").format(max)));

            //油耗最小值
            minOilTv.setText(String.valueOf(new DecimalFormat("0.00").format(min)));

            //总的里程数
            allKmTv.setText(String.valueOf(Float.valueOf(recordEntities.get(0).getOdometer())-10));

            //加油总量
            allOilTv.setText(String.valueOf(new DecimalFormat(".00").format(
                    (sum/data.length)*(Float.valueOf(recordEntities.get(0).getOdometer())/100))));

            //里程平均
            avgKmTv.setText(String.valueOf(new DecimalFormat(".00").format(
                    ((Float.valueOf(recordEntities.get(0).getOdometer()))-10)
                            /(float)(365*2+31))));

        }
    }



    private class UpdateChartBroadcastReceiver extends BroadcastReceiver {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onReceive(Context context, Intent intent) {
            mRecordEntityList.clear();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(mReceiver);
    }

}
