package net.lidongdong.bearoil.ui.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.lidongdong.bearoil.R;
import net.lidongdong.bearoil.adapter.DialogAdapter;
import net.lidongdong.bearoil.adapter.PopupWindowAdapter;
import net.lidongdong.bearoil.db.DatabaseTool;
import net.lidongdong.bearoil.db.ObservableSQLite;
import net.lidongdong.bearoil.entity.CarEntity;
import net.lidongdong.bearoil.ui.aty.AddCarActivity;
import net.lidongdong.bearoil.ui.aty.InputOilRecordsActivity;
import net.lidongdong.bearoil.ui.aty.ShowAllRecordsActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @ date 17/4/17
 * @ explain
 * @ function
 */

public class MainFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout mainAccountRl;
    private RelativeLayout mainCarNameRl;
    private TextView mainToolbarCarNameTv;
    private ImageButton mainAddBtn;
    private ImageButton mainContentBtn;
    private ImageButton mainMoreBtn;
    private ImageButton mainShareBtn;
    private TabLayout mainTl;
    private ViewPager mainVp;
    private String[] titles;
    private List<Fragment> mFragments;

    private List<CarEntity> mCarEntities;
    private PopupWindow mPopupWindow;
    private DialogAdapter mDialogAdapter;
    private AddCarBroadcastReceiver mReceiver;
    private RemoveCarBroadcastReceiver mRemoveCarBroadcastReceiver;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {

        setQueryCarName();

        mFragments.add(new FuelConsumptionsFragment());
        mFragments.add(new RankFragment());
        mFragments.add(new FuelCostsFragment());
        mFragments.add(new CostsFragment());
        mainVp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mainVp.setCurrentItem(0);
        mainTl.setupWithViewPager(mainVp);
        mainTl.setTabTextColors(Color.WHITE, Color.WHITE);
        mainTl.setSelectedTabIndicatorColor(Color.GREEN);
    }

    //设置查询到的小车名
    private void setQueryCarName() {
        ObservableSQLite.querySelectedCar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CarEntity>() {
                    @Override
                    public void accept(@NonNull CarEntity carEntity) throws Exception {
                        if (carEntity != null) {
                            mainToolbarCarNameTv.setText(carEntity.getName());
                        }
                    }
                });
    }

    private void initView(View v) {

        mainAccountRl = (RelativeLayout) v.findViewById(R.id.main_account_rl);
        mainCarNameRl = (RelativeLayout) v.findViewById(R.id.main_car_name_rl);
        mainToolbarCarNameTv = (TextView) v.findViewById(R.id.main_toolbar_car_name_tv);
        mainAddBtn = (ImageButton) v.findViewById(R.id.main_add_btn);
        mainContentBtn = (ImageButton) v.findViewById(R.id.main_content_btn);
        mainMoreBtn = (ImageButton) v.findViewById(R.id.main_more_btn);
        mainShareBtn = (ImageButton) v.findViewById(R.id.main_share_btn);
        mainTl = (TabLayout) v.findViewById(R.id.main_tl);
        mainVp = (ViewPager) v.findViewById(R.id.main_vp);
        mFragments = new ArrayList<>();


        mainAccountRl.setOnClickListener(this);
        mainCarNameRl.setOnClickListener(this);
        mainAddBtn.setOnClickListener(this);
        mainContentBtn.setOnClickListener(this);
        mainMoreBtn.setOnClickListener(this);
        mainShareBtn.setOnClickListener(this);

        titles = getResources().getStringArray(R.array.main_titles);

        mReceiver = new AddCarBroadcastReceiver();
        IntentFilter filter = new IntentFilter("UPDATE_UI");
        getContext().registerReceiver(mReceiver, filter);

        mRemoveCarBroadcastReceiver = new RemoveCarBroadcastReceiver();
        IntentFilter removeFilter = new IntentFilter("REMOVE_UPDATE_UI");
        getContext().registerReceiver(mRemoveCarBroadcastReceiver, removeFilter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_account_rl:
                break;
            case R.id.main_car_name_rl:
                //显示 popupWindow
                showPopupWindow(v);
                break;
            case R.id.main_add_btn:

                //输入当前车的加油记录
                Intent intent = new Intent(getContext(), InputOilRecordsActivity.class);
                startActivity(intent);

                break;
            case R.id.main_content_btn:

                //显示当前小车的所有信息
                Intent showAllRecordsIntent = new Intent(getContext(), ShowAllRecordsActivity.class);
                startActivity(showAllRecordsIntent);

                break;
            case R.id.main_more_btn:
                break;
            case R.id.main_share_btn:
                break;

        }
    }

    private void showPopupWindow(View parent) {
        //加载布局
        LinearLayout layout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.list_popup_window, null);
        ListView listView = (ListView) layout.findViewById(R.id.lv_dialog);

        //数据
        ObservableSQLite.queryAllCar().map(carEntities -> {
            CarEntity car = new CarEntity(50);
            car.setName("车辆管理");
            carEntities.add(car);
            if (carEntities.size() == 1) {
                CarEntity myCar = new CarEntity(1);
                myCar.setName("我的小车");
                myCar.setSelect(1);
                carEntities.add(0, myCar);
                DatabaseTool.getInstance().addCar(myCar);
            }
            return carEntities;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(carEntities -> {
                    mCarEntities = new ArrayList<>();
                    mCarEntities = carEntities;
                    PopupWindowAdapter adapter = new PopupWindowAdapter(getContext());
                    adapter.setCars(carEntities);
                    listView.setAdapter(adapter);
                });

        //实例化 popupWindow
        mPopupWindow = new PopupWindow(layout, 300, WindowManager.LayoutParams.WRAP_CONTENT);
        //控制键盘是否可以获取焦点
        mPopupWindow.setFocusable(true);
        //设置 popupWindow 弹出窗体的背景
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        WindowManager manager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        @SuppressWarnings("deprecation")
        //获取 xOff
                int xPos = manager.getDefaultDisplay().getWidth() / 2 - mPopupWindow.getWidth() / 2;
        //xOff,yOff基于 anchor 的左下角进行偏移.
        mPopupWindow.showAsDropDown(parent, xPos, 0);

        listView.setOnItemClickListener((parent1, view, position, id) -> {

            mPopupWindow.dismiss();
            mPopupWindow = null;

            if (id == 50) {

                showBottomSheet();

            } else {
                ObservableSQLite.changeSelectCar((int) id);
                Intent intent = new Intent();
                intent.setAction("UPDATE_CHART");
                getContext().sendBroadcast(intent);
                mainToolbarCarNameTv.setText(mCarEntities.get(position).getName());
            }

        });


    }

    private void showBottomSheet() {
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.list_popup_window, null);
        ListView listView = (ListView) layout.findViewById(R.id.lv_dialog);
        //初始化 dialog
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        //初始化头布局
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.item_popup_window_header, null);
        ImageView addCarIv = (ImageView) headerView.findViewById(R.id.add_car_iv);
        //增加车辆
        addCarIv.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddCarActivity.class);
            startActivity(intent);
        });

        listView.addHeaderView(headerView);
        bottomSheetDialog.setContentView(layout);
        mCarEntities.remove(mCarEntities.size() - 1);
        mDialogAdapter = new DialogAdapter(getContext());
        mDialogAdapter.setCars(mCarEntities);
        listView.setAdapter(mDialogAdapter);

        mDialogAdapter.setListener(id -> {

            //删除车辆
            ObservableSQLite.removeCar(id);
            Intent intent = new Intent();
            intent.setAction("REMOVE_UPDATE_UI");
            getContext().sendBroadcast(intent);
        });
        bottomSheetDialog.show();

    }


    private class AddCarBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            ObservableSQLite.queryAllCar()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(carEntities -> {
                        mCarEntities.clear();
                        mCarEntities.addAll(carEntities);
                        mDialogAdapter.notifyDataSetChanged();

                    });

        }
    }

    private class RemoveCarBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            ObservableSQLite.queryAllCar()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(carEntities -> {
                        mCarEntities.clear();
                        mCarEntities.addAll(carEntities);
                        mDialogAdapter.notifyDataSetChanged();

                    });

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(mReceiver);
        getContext().unregisterReceiver(mRemoveCarBroadcastReceiver);

    }
}
