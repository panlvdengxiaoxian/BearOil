package net.lidongdong.bearoil.entity;
/**
*
*
*  @author lidongdong(一个帅的惊天动地的男人)
*  @date 17/4/14
*  @explain 
*  @function 
*  @version 1.0
*   
*/
public class MoneyEntity {
    private String time;
    private float money;


    @Override
    public String toString() {
        return "MoneyEntity{" +
                "time='" + time + '\'' +
                ", money=" + money +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
