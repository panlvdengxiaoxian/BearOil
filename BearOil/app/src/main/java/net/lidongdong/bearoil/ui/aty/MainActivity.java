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

    }

}
