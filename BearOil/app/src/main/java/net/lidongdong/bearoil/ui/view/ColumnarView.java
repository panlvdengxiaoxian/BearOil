package net.lidongdong.bearoil.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * @authorlidongdong(A handsome man)
 * @ date 17/4/27
 * @ explain
 * @ function
 * @ version 1.0
 */

public class ColumnarView extends View {

    private List<String> mUnitChartTime;
    private int mWidth;
    private int mHeight;
    private String mStr;
    private Paint mPaint;

    private float[] mAllMoney;
    private float mMaxMoney;


    public ColumnarView(Context context) {
        super(context);
    }

    public ColumnarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColumnarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    // 这两个数据是随便写的, 实际上应该根据不同的屏幕确定不同的值
    private static final int PADDING_WIDTH = 20;
    private static final int PADDING_WIDTH_RIGHT = 80;
    private static final int PADDING_HEIGHT = 80;


    private void init() {

    }

    public void setUnitChartTime(List<String> unitChartTime) {
        this.mUnitChartTime = unitChartTime;
    }

    public void setStr(String str) {
        mStr = str;
    }

    public void setAllMoney(float[] allMoney) {
        this.mAllMoney = allMoney;
        invalidate();
    }

    public void setMaxMoney(float maxMoney) {
        mMaxMoney = maxMoney;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        drawAxisX(canvas);
     //   drawMarketX(canvas);
        drawTextX(canvas);
        //数据(矩形,柱状图)
        drawDataRect(canvas);

    }

    private void drawDataRect(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        float length = (mWidth - PADDING_WIDTH - PADDING_WIDTH_RIGHT) / mUnitChartTime.size();
        float lengthY = (mHeight- PADDING_HEIGHT * 2 ) / mMaxMoney;
        for (int i = 0; i < mAllMoney.length; i++) {
            float startX = PADDING_WIDTH + length * i + 10;
            float stopX = PADDING_WIDTH + length * i + 80;
            float startY = (mHeight - PADDING_HEIGHT) - mAllMoney[i] * lengthY;
            float stopY = mHeight - PADDING_HEIGHT;
            canvas.drawRect(startX, startY, stopX, stopY, mPaint);

        }

        for (int i = 0; i < mAllMoney.length; i++) {
            float startX = PADDING_WIDTH + length * i + 20;
            float startY = (mHeight - PADDING_HEIGHT) - mAllMoney[i] * lengthY;
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(20);
            canvas.drawText(String.valueOf(mAllMoney[i]), startX +20, startY - 20, mPaint);

        }


    }

//    private void drawMarketX(Canvas canvas) {
//
//        float length = (mWidth - PADDING_WIDTH - PADDING_WIDTH_RIGHT) / (mUnitChartTime.size()+1);
//         mPaint.setColor(Color.parseColor("#605454"));
//         for (int i = 0; i < mUnitChartTime.size(); i++) {
//            canvas.drawLine((PADDING_WIDTH + length * i ), mHeight - PADDING_HEIGHT, (PADDING_WIDTH + length * i ) ,
//                   PADDING_HEIGHT, mPaint);
//
//        }
//
//    }

    private void drawTextX(Canvas canvas) {

        float length = (mWidth - PADDING_WIDTH - PADDING_WIDTH_RIGHT) / mUnitChartTime.size();
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(25);
        for (int i = 0; i < mUnitChartTime.size(); i++) {
            canvas.drawText(mUnitChartTime.get(i), (PADDING_WIDTH + length * i) ,
                    mHeight - PADDING_HEIGHT +30, mPaint);

        }
    }

    private void drawAxisX(Canvas canvas) {

        float startX = PADDING_WIDTH;
        float stopX = mWidth - PADDING_WIDTH_RIGHT;
        float stopY = mHeight - PADDING_HEIGHT;

        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(2);

        canvas.drawLine(startX, stopY, stopX, stopY, mPaint);
        //箭头(上)
        canvas.drawLine(stopX, stopY, stopX - 10, stopY - 10, mPaint);
        //箭头(下)
        canvas.drawLine(stopX, stopY, stopX - 10, stopY + 10, mPaint);
        //日期

        mPaint.setTextSize(30);
        canvas.drawText(mStr, stopX + 10, stopY +10 , mPaint);
    }
}
