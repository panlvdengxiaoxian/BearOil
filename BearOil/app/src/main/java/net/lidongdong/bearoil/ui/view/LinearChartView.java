package net.lidongdong.bearoil.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
*
*
*  @author lidongdong(一个帅的惊天动地的男人)
*  @ date 17/4/18
*  @ explain
*  @ function  线性的图表
*  @version 1.0
*
*/

public class LinearChartView extends View{
    private int[] values=new int[5];
    private String [] names=new String[]{};
    private Paint mPaint;

    public LinearChartView(Context context) {
        super(context);
    }

    public LinearChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    //这两个数据应该根据不同的屏幕得到不同的值
  private static  final int PADDING_WIDTH=50;
  private static  final int PADDING_HEIGHT=250;
    private void init() {
        values[0]=10;
        values[1]=20;
        values[2]=30;
        values[3]=40;
        values[4]=50;
        values[5]=60;
        values[6]=70;

        names[0]="一月";
        names[1]="一月";
        names[2]="一月";
        names[3]="一月";
        names[4]="一月";
        names[5]="一月";
        names[6]="一月";

        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
