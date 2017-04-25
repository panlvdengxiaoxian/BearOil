package net.lidongdong.bearoil.utils;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @ date 17/4/22
 * @ explain
 * @ function
 */

public class DataCalculationUntil {

    /**
     * 计算每百公里油耗
     * @param currentOdometer 当前的公里数
     * @param nextYuan 下一次加油的钱数
     * @param nextPrice 下一次油价
     * @param nextOdometer 下一次加油的公里数
     * @return 当前的每百公里油耗
     */
    public static float kmData(float nextYuan, float nextPrice, String currentOdometer,String nextOdometer) {

         return (nextYuan*100)/
                 (nextPrice*( Float.parseFloat(nextOdometer)
                         -Float.parseFloat(currentOdometer)
                 ));
    }


}
