package net.lidongdong.bearoil.ui.aty;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;

import net.lidongdong.bearoil.R;

public class MainActivity extends FragmentActivity {
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
         mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
//
//        ObservableSQLite.queryRecords()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<RecordEntity>>() {
//                    @RequiresApi(api = Build.VERSION_CODES.N)
//                    @Override
//                    public void accept(@NonNull List<RecordEntity> recordEntities) throws Exception {
//                        Log.d("xxx", "TimeUntil.unixTimeYear(recordEntities):"
//                                + TimeUntil.unitChartTime(recordEntities)[10]);
//                    }
//                });


    }

}
