package net.lidongdong.bearoil.utils;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import net.lidongdong.bearoil.entity.MoneyEntity;
import net.lidongdong.bearoil.entity.RecordEntity;

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
     *
     * @return String 类型的时间
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        return format.format(curDate);
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     *
     * @ return 日期
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String DateTimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return nowTimeStamp
     */
    public static String getNowTimeStamp() {
        long time = System.currentTimeMillis();
        return String.valueOf(time / 1000);
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

            if (Integer.valueOf(units.get(i).substring(4, 5)) == 0) {
                dates[i] = units.get(i).substring(5, 6);
            } else {
                dates[i] = units.get(i).substring(4, 6);
            }
        }
        return dates;


    }

    /**
     * 将 num 的十位进一位
     *
     * @param num 参数 单位
     * @return 完成的柱状图y轴的最大值单位
     */
    public static float unitHundred(float num) {
        if (num > 999) {
            float thousand = num / 1000;
            float hundred = num / 100 - thousand * 10;
            return thousand * 1000 + (hundred + 2) * 100;

        } else {
            float hundred = num / 100;
            return (hundred + 1) * 100;
        }

    }


    public static float maxMoney(List<MoneyEntity> moneyEntities) {

         List<Float> money = new ArrayList<>();
         for (int i = 0; i < moneyEntities.size(); i++) {
            float max = moneyEntities.get(i).getMoney();
            money.add(max);
        }
        return Collections.max(money);
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<String> unixYearUnit(List<MoneyEntity> moneyEntities) {

        List<String> dates = new ArrayList<>();

        for (int i = 0; i < moneyEntities.size()/3; i++) {
                String time = moneyEntities.get(i).getTime();
                dates.add(time);
        }
        return dates;

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<String> unixYearUnitTwo(List<MoneyEntity> moneyEntities) {

        List<String> dates = new ArrayList<>();

        for (int i = 0; i < moneyEntities.size(); i++) {
            if (i > moneyEntities.size() / 3 && i < (moneyEntities.size() / 3 * 2 ) ){
                String time = moneyEntities.get(i).getTime();
                dates.add(time);
            }

        }
        return dates;

    }
 @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<String> unixYearUnitThree(List<MoneyEntity> moneyEntities) {

        List<String> dates = new ArrayList<>();

        for (int i = 0; i < moneyEntities.size(); i++) {
            if ( i > (moneyEntities.size() / 3 * 2 ) ){
                String time = moneyEntities.get(i).getTime();
                dates.add(time);
            }

        }
        return dates;

    }




}