package net.lidongdong.bearoil.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.lidongdong.bearoil.R;

import java.util.ArrayList;
import java.util.List;
/**
*
*
*  @author lidongdong(一个帅的惊天动地的男人)
*  @date 17/4/17
*  @explain
*  @function
*  @version 1.0
*
*/

public class MainFragment extends Fragment implements View.OnClickListener{

    private RelativeLayout mainAccountRl;
    private RelativeLayout mainCarNameRl;
    private TextView mainToolbarCarNameTv;
    private LinearLayout mLayout;
    private ImageButton mainAddBtn;
    private ImageButton mainContentBtn;
    private ImageButton mainMoreBtn;
    private ImageButton mainShareBtn;
    private TabLayout mainTl;
    private ViewPager mainVp;
    private  String[] titles;
    private List<Fragment> mFragments;

    public MainFragment() {
    }


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
        mainTl.setTabTextColors(Color.WHITE,Color.WHITE);
        mainTl.setSelectedTabIndicatorColor(Color.GREEN);
    }

    private void initView(View v) {

        mainAccountRl = (RelativeLayout)v. findViewById(R.id.main_account_rl);
        mainCarNameRl = (RelativeLayout)v. findViewById(R.id.main_car_name_rl);
        mainToolbarCarNameTv = (TextView)v. findViewById(R.id.main_toolbar_car_name_tv);
        mLayout = (LinearLayout)v. findViewById(R.id.main_rl);
        mainAddBtn = (ImageButton)v. findViewById(R.id.main_add_btn);
        mainContentBtn = (ImageButton)v. findViewById(R.id.main_content_btn);
        mainMoreBtn = (ImageButton)v. findViewById(R.id.main_more_btn);
        mainShareBtn = (ImageButton)v. findViewById(R.id.main_share_btn);
        mainTl = (TabLayout)v. findViewById(R.id.main_tl);
        mainVp = (ViewPager)v. findViewById(R.id.main_vp);
        mFragments=new ArrayList<>();


        mainAccountRl.setOnClickListener(this);
        mainCarNameRl.setOnClickListener(this);
        mainAddBtn.setOnClickListener(this);
        mainContentBtn.setOnClickListener(this);
        mainMoreBtn.setOnClickListener(this);
        mainShareBtn.setOnClickListener(this);

        titles=getResources().getStringArray(R.array.main_titles);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_account_rl:
                break;
            case R.id.main_car_name_rl:
                

                break;
            case R.id.main_add_btn:
                break;
            case R.id.main_content_btn:
                break;
            case R.id.main_more_btn:
                break;
            case R.id.main_share_btn:
                break;

        }
    }

}
