package net.lidongdong.bearoil.utils;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import net.lidongdong.bearoil.entity.RecordEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @ date 17/4/22
 * @ explain
 * @ function
 */

public class TimeUntil {


    /**
     * 将 unix 时间戳转换成标准时间
     *
     * @param timestamp long 类型的时间戳
     * @return String 类型的时间
     */

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String unixTimeStamp(long timestamp) {
        return new SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date(timestamp));
    }

    /**
     * 获取当前的时间
     * @return String 类型的时间
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String getCurrentTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        return format.format(curDate);
    }

    /**
     * 将当前的时间转化为 unix 时间戳
     *
     * @return long类型的 unix 时间戳
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static long setUnixTime() throws ParseException {
        Date epoch = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(getCurrentTime());
        return Long.valueOf(String.valueOf(epoch));
    }

    /**
     * 获取图表的单位(月份)
     *
     * @param recordEntities 数据源
     * @return 单位的集合(yyyy/MM)
     */

    @RequiresApi(api = Build.VERSION_CODES.N)

    public static List<String> unixTimeUnit(List<RecordEntity> recordEntities) {

        List<String> dates = new ArrayList<>();

        for (int i = 0; i < recordEntities.size(); i++) {

            long timestamp = Long.valueOf(recordEntities.get(i).getDate());

            String date = new SimpleDateFormat("yyyy/MM").format(new java.util.Date(timestamp));
            String newDate = date.substring(0, 4) + date.substring(5, 7);

            if (!dates.contains(newDate)) {

                dates.add(newDate);

            }
        }

        //将集合倒序排列
        Collections.reverse(dates);
        return dates;

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String[] unitChartTime(List<RecordEntity> recordEntities) {
        List<String> units = TimeUntil.unixTimeUnit(recordEntities);
        String[] dates = new String[units.size()];

        for (int i = 0; i < units.size(); i++) {

            if (Integer.valueOf(units.get(i).substring(4,5)) == 0) {
                dates[i] = units.get(i).substring(5, 6);
            }else {
                dates[i] = units.get(i).substring(4, 6);
            }
        }
        return dates;


    }
}