package net.lidongdong.bearoil.ui.fragment;

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
import net.lidongdong.bearoil.entity.MoneyEntity;
import net.lidongdong.bearoil.entity.RecordEntity;
import net.lidongdong.bearoil.ui.view.ColumnarView;
import net.lidongdong.bearoil.utils.DataCalculationUntil;
import net.lidongdong.bearoil.utils.RotationUntil;
import net.lidongdong.bearoil.utils.TimeUntil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @authorlidongdong(A handsome man)
 * @ date 17/4/27
 * @ explain
 * @ function
 * @ version 1.0
 */

public class FuelCostsFragment extends Fragment implements View.OnClickListener {
    private ImageView mFuelImg1;
    private ImageView mFuelImg2;
    private ImageView mFuelImg3;
    private ImageView mFuelImg4;
    private TextView mFuelConsumptionsNameTv;
    private ColumnarView mChartView;
    private TextView mDefaultName;
    private TextView mAvgOilTv;
    private TextView mCurrentKmTv;
    private TextView mMaxOilTv;
    private TextView mAllKmTv;
    private TextView mMinOilTv;
    private TextView mAllOilTv;
    private TextView mRecentOilTv;
    private TextView mAvgKmTv;
    private float mKm;

    public FuelCostsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mFuelImg1 = (ImageView) view.findViewById(R.id.fuel_img1);
        mFuelImg2 = (ImageView) view.findViewById(R.id.fuel_img2);
        mFuelImg3 = (ImageView) view.findViewById(R.id.fuel_img3);
        mFuelImg4 = (ImageView) view.findViewById(R.id.fuel_img4);
        mFuelConsumptionsNameTv = (TextView) view.findViewById(R.id.fuel_consumptions_name_tv);
        ImageView fuelConsumptionsLeftIv = (ImageView) view.findViewById(R.id.fuel_consumptions_left_iv);
        ImageView fuelConsumptionsRightIv = (ImageView) view.findViewById(R.id.fuel_consumptions_right_iv);
        mChartView = (ColumnarView) view.findViewById(R.id.chart_view);
        mDefaultName = (TextView) view.findViewById(R.id.default_name);
        mAvgOilTv = (TextView) view.findViewById(R.id.avg_oil_tv);
        mCurrentKmTv = (TextView) view.findViewById(R.id.current_km_tv);
        mMaxOilTv = (TextView) view.findViewById(R.id.max_oil_tv);
        mAllKmTv = (TextView) view.findViewById(R.id.all_km_tv);
        mMinOilTv = (TextView) view.findViewById(R.id.min_oil_tv);
        mAllOilTv = (TextView) view.findViewById(R.id.all_oil_tv);
        mRecentOilTv = (TextView) view.findViewById(R.id.recent_oil_tv);
        mAvgKmTv = (TextView) view.findViewById(R.id.avg__km_tv);

        fuelConsumptionsLeftIv.setOnClickListener(this);
        fuelConsumptionsRightIv.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        oilCostYear();
        setCostOil();

        ObservableSQLite.queryRecords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setDataSource);
    }

    int count = 1;
    int num = 3;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fuel_consumptions_right_iv:
                if (count == 0) {
                    oilCostYear();
                    RotationUntil.switchRound(getContext(), count, mFuelConsumptionsNameTv, mFuelImg1, mFuelImg2, mFuelImg3, mFuelImg4);
                    count = 1;
                    num = 3;
                } else if (count == 1) {
                    oilCostMonth();
                    RotationUntil.switchRound(getContext(), count, mFuelConsumptionsNameTv, mFuelImg1, mFuelImg2, mFuelImg3, mFuelImg4);
                    count = 2;
                    num = 0;
                } else if (count == 2) {
                    oilCostMonthTwo();
                    RotationUntil.switchRound(getContext(), count, mFuelConsumptionsNameTv, mFuelImg1, mFuelImg2, mFuelImg3, mFuelImg4);
                    count = 3;
                    num = 1;
                } else if (count == 3) {
                    oilCostMonthThree();
                    RotationUntil.switchRound(getContext(), count, mFuelConsumptionsNameTv, mFuelImg1, mFuelImg2, mFuelImg3, mFuelImg4);
                    count = 0;
                    num = 2;
                }

                break;
            case R.id.fuel_consumptions_left_iv:
                if (num == 0) {
                    oilCostYear();
                    RotationUntil.switchRound(getContext(), num, mFuelConsumptionsNameTv, mFuelImg1, mFuelImg2, mFuelImg3, mFuelImg4);
                    count = 1;
                    num = 3;
                } else if (num == 1) {
                    oilCostMonth();
                    RotationUntil.switchRound(getContext(), num, mFuelConsumptionsNameTv, mFuelImg1, mFuelImg2, mFuelImg3, mFuelImg4);
                    count = 2;
                    num = 0;
                } else if (num == 2) {
                    oilCostMonthTwo();
                    RotationUntil.switchRound(getContext(), num, mFuelConsumptionsNameTv, mFuelImg1, mFuelImg2, mFuelImg3, mFuelImg4);
                    count = 3;
                    num = 1;
                } else if (num == 3) {
                    oilCostMonthThree();
                    RotationUntil.switchRound(getContext(), num, mFuelConsumptionsNameTv, mFuelImg1, mFuelImg2, mFuelImg3, mFuelImg4);
                    count = 0;
                    num = 2;
                }
                break;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void oilCostYear() {
        ObservableSQLite.queryRecordMoneyEachYear()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::oilCostTime);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void oilCostMonth() {
        ObservableSQLite.queryRecordMoneyEachMonth()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::oilCostMonth);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void oilCostMonthTwo() {
        ObservableSQLite.queryRecordMoneyEachMonth()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::oilCostMonthTwo);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void oilCostMonthThree() {
        ObservableSQLite.queryRecordMoneyEachMonth()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::oilCostMonthThree);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void oilCostTime(List<MoneyEntity> moneyEntities) {
        float[] mm = new float[moneyEntities.size()];
        List<String> time = new ArrayList<>();
        if (moneyEntities.size() > 0) {
            for (int i = 0; i < moneyEntities.size(); i++) {
                mm[i] = moneyEntities.get(i).getMoney();
                time.add(moneyEntities.get(i).getTime());
            }
            mChartView.setAllMoney(mm);
            mChartView.setUnitChartTime(time);
            mChartView.setMaxMoney(TimeUntil.unitHundred(TimeUntil.maxMoney(moneyEntities)));

        }
        mChartView.setStr("年份");

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void oilCostMonth(List<MoneyEntity> moneyEntities) {
        float[] mm = new float[moneyEntities.size()];
        if (moneyEntities.size() > 0) {
            for (int i = 0; i < moneyEntities.size(); i++) {
                if (i < moneyEntities.size() / 3)
                    mm[i] = moneyEntities.get(i).getMoney();
            }
            mChartView.setAllMoney(mm);
            mChartView.setUnitChartTime(TimeUntil.unixYearUnit(moneyEntities));
            mChartView.setMaxMoney(TimeUntil.unitHundred(TimeUntil.maxMoney(moneyEntities)));

        }
        mChartView.setStr("月份");

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void oilCostMonthTwo(List<MoneyEntity> moneyEntities) {
        List<Float> nn = new ArrayList<>();
        float[] mm = new float[moneyEntities.size()];
        if (moneyEntities.size() > 0) {
            for (int i = 0; i < moneyEntities.size(); i++) {
                if (i > moneyEntities.size() / 3 && i < (moneyEntities.size() / 3 * 2))
                    nn.add(moneyEntities.get(i).getMoney());
            }


            for (int i = 0; i < nn.size(); i++) {
                mm[i] = nn.get(i);
            }
            mChartView.setAllMoney(mm);
            mChartView.setUnitChartTime(TimeUntil.unixYearUnitTwo(moneyEntities));
            mChartView.setMaxMoney(TimeUntil.unitHundred(TimeUntil.maxMoney(moneyEntities)));

        }
        mChartView.setStr("月份");

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void oilCostMonthThree(List<MoneyEntity> moneyEntities) {
        List<Float> nn = new ArrayList<>();
        float[] mm = new float[moneyEntities.size()];
        if (moneyEntities.size() > 0) {
            for (int i = 0; i < moneyEntities.size(); i++) {
                if (i > (moneyEntities.size() / 3 * 2))
                    nn.add(moneyEntities.get(i).getMoney());
            }

            for (int i = 0; i < nn.size(); i++) {
                mm[i] = nn.get(i);
            }
            mChartView.setAllMoney(mm);
            mChartView.setUnitChartTime(TimeUntil.unixYearUnitThree(moneyEntities));
            mChartView.setMaxMoney(TimeUntil.unitHundred(TimeUntil.maxMoney(moneyEntities)));

        }
        mChartView.setStr("月份");

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setDataSource(List<RecordEntity> recordEntities) {
        if (recordEntities.size() > 0) {
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
                    if (min > 0) {
                        if (data[i] < min) {
                            min = data[i];
                        }
                    }
                    //求公里数之和
                    kmSum = kmSum + Float.valueOf(recordEntities.get(i).getOdometer());
                }
            }
            for (int stat = 0, end = data.length - 1; stat < end; stat++, end--) {
                float temp = data[end];
                data[end] = data[stat];
                data[stat] = temp;
            }
            mKm = Float.valueOf(recordEntities.get(0).getOdometer());
            //当前里程数
            mCurrentKmTv.setText(recordEntities.get(0).getOdometer());

            //总的里程数
            mAllKmTv.setText(String.valueOf(Float.valueOf(recordEntities.get(0).getOdometer()) - 10));

            //加油总量
            mAllOilTv.setText(String.valueOf(new DecimalFormat(".00").format(
                    (sum / data.length) * (Float.valueOf(recordEntities.get(0).getOdometer()) / 100))));

            //里程平均
            mAvgKmTv.setText(String.valueOf(new DecimalFormat(".00").format(
                    ((Float.valueOf(recordEntities.get(0).getOdometer())) - 10)
                            / (float) (365 * 2 + 31))));

        }
    }

    //计算花了多少钱
    private void setCostOil() {

        ObservableSQLite.queryRecordMoneyEachYear()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moneyEntities -> {
                    if (moneyEntities.size() > 0) {
                        float money = 0;
                        float[] data = new float[moneyEntities.size()];
                        for (int i = 0; i < moneyEntities.size(); i++) {
                            data[i] = moneyEntities.get(i).getMoney();
                            money = money + data[i];
                        }
                        //油费总计
                        mAvgOilTv.setText(String.valueOf(new DecimalFormat(".00").format(money)));
                        //油费平均(元/月)
                        mMaxOilTv.setTextColor(Color.YELLOW);
                        mMaxOilTv.setText(String.valueOf(new DecimalFormat(".00").format(money/25)));
                        //油费平均(元/天)
                        mMinOilTv.setText(String.valueOf(new DecimalFormat(".00").format(money/760)));
                        //油费平均(元/公里)
                        mRecentOilTv.setText(String.valueOf(new DecimalFormat("0.00").format(money/mKm)));

                    }
                });




    }


}
