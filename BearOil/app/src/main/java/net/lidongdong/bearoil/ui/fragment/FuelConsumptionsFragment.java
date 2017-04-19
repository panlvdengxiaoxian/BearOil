package net.lidongdong.bearoil.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.lidongdong.bearoil.R;
import net.lidongdong.bearoil.ui.view.LinearChartView;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @date 17/4/19
 * @explain
 * @function
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

    public FuelConsumptionsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    int count = 1;
    int num=4;

    @Override
    public void onClick(View v) {
        String[] strings = getResources().getStringArray(R.array.oil_chart);

        switch (v.getId()) {

            case R.id.fuel_consumptions_right_iv:
                if (count == 0) {
                    switchRound(count);
                    fuelConsumptionsNameTv.setText(strings[count]);
                    num=4;
                    count=1;

                } else if (count == 1) {
                    switchRound(count);
                    fuelConsumptionsNameTv.setText(strings[count]);
                    num=0;
                    count=2;

                } else if (count == 2) {
                    switchRound(count);
                    fuelConsumptionsNameTv.setText(strings[count]);
                    num=1;
                    count = 3;
                } else if (count == 3) {
                    switchRound(count);
                    fuelConsumptionsNameTv.setText(strings[count]);
                    num=2;
                    count = 4;
                } else if (count == 4) {
                    switchRound(count);
                    fuelConsumptionsNameTv.setText(strings[count]);
                    num=3;
                    count = 0;
                }

                break;

            case R.id.fuel_consumptions_left_iv:
                if (num == 0) {
                    switchRound(num);
                    fuelConsumptionsNameTv.setText(strings[num]);
                    count=1;
                    num = 4;
                } else if (num == 4) {
                    switchRound(num);
                    fuelConsumptionsNameTv.setText(strings[num]);
                    count=0;
                    num = 3;
                } else if (num == 3) {
                    switchRound(num);
                    fuelConsumptionsNameTv.setText(strings[num]);
                    count=4;
                    num = 2;
                } else if (num == 2) {
                    switchRound(num);
                    fuelConsumptionsNameTv.setText(strings[num]);
                    count=3;
                    num = 1;
                } else if (num == 1) {
                    switchRound(num);
                    fuelConsumptionsNameTv.setText(strings[num]);
                    count=2;
                    num = 0;
                }
                break;
        }
    }

    private void switchRound(int count) {
        if (count == 0) {
            fuelImg1.setImageResource(R.mipmap.baisexiaoyuandian);
            fuelImg2.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg3.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg4.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg5.setImageResource(R.mipmap.shixinxiaoyuandian);

            String[] name=new String[]{"2016","2017","2018"};
            chartView.setNames(name);
            chartView.setTvSizes(3);
            chartView.setxNums(18);

        } else if (count == 1) {
            fuelImg1.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg2.setImageResource(R.mipmap.baisexiaoyuandian);
            fuelImg3.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg4.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg5.setImageResource(R.mipmap.shixinxiaoyuandian);

            String[] name=new String[]{"5","6","7","8","9","10","11","12","2017","2","3","4"};
            chartView.setNames(name);
            chartView.setTvSizes(12);
            chartView.setxNums(12);
        } else if (count == 2) {
            fuelImg1.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg2.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg3.setImageResource(R.mipmap.baisexiaoyuandian);
            fuelImg4.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg5.setImageResource(R.mipmap.shixinxiaoyuandian);

            String[] name=new String[]{"11","12","2017","2","3","4"};
            chartView.setNames(name);
            chartView.setTvSizes(6);
            chartView.setxNums(6);

        } else if (count == 3) {
            fuelImg1.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg2.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg3.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg4.setImageResource(R.mipmap.baisexiaoyuandian);
            fuelImg5.setImageResource(R.mipmap.shixinxiaoyuandian);

            String[] name=new String[]{"2","3","4"};
            chartView.setNames(name);
            chartView.setTvSizes(3);
            chartView.setxNums(3);
        } else if (count == 4) {
            fuelImg1.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg2.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg3.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg4.setImageResource(R.mipmap.shixinxiaoyuandian);
            fuelImg5.setImageResource(R.mipmap.baisexiaoyuandian);

            String[] name=new String[]{"5","6","7","8","9","10","11","12","2017","2","3","4"};
            chartView.setNames(name);
            chartView.setTvSizes(12);
            chartView.setxNums(12);
        }

    }

}
