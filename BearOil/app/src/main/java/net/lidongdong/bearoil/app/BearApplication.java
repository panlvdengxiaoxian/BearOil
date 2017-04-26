package net.lidongdong.bearoil.app;

import android.app.Application;
import android.content.Context;

/**
 *
 *
 *  @author lidongdong(一个帅的惊天动地的男人)
 *  @date 17/4/13
 *  @explain
 *  @function
 *  @version 1.0
 *
 */

public class BearApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate () {
        super.onCreate();
        sContext = this;
//        DatabaseTool.getInstance();
    }

    public static Context getContext () {
        return sContext;
    }
}
