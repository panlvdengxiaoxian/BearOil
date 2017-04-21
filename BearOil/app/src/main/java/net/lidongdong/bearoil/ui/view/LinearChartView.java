package net.lidongdong.bearoil.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import net.lidongdong.bearoil.entity.RecordEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lidongdong(一个帅的惊天动地的男人)
 * @version 1.0
 * @ date 17/4/18
 * @ explain
 * @ function  线性的图表
 */

public class LinearChartView extends View {

    private int[] nums;

    private List<RecordEntity> mRecordEntities;
    private int mWidth;
    private int mHeight;
    private int tvSizes=3;
    private int xNums=18;
    private String[] names ;


    public LinearChartView(Context context) {
        this(context, null);

    }

    public LinearChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();

    }

    public void setRecordEntities(List<RecordEntity> recordEntities) {
        mRecordEntities = recordEntities;
    }

    public void setTvSizes(int tvSizes) {
        this.tvSizes = tvSizes;
        invalidate();
    }

    public void setxNums(int xNums) {
        this.xNums = xNums;
        invalidate();
    }

    public void setNames(String[] names) {
        this.names = names;
        invalidate();
    }

    // 这两个数据是随便写的, 实际上应该根据不同的屏幕确定不同的值
    private static final int PADDING_WIDTH = 40;
    private static final int PADDING_HEIGHT = 50;
    private static final int COUNT_Y_MARK = 15;

    private List<Point> mPoints;

    private void init() {

        mPoints = new ArrayList<>();
        nums=new int[]{15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
        names=new String[]{"2016","2017","2018"};


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
        @SuppressLint("DrawAllocation")
        Paint paint = new Paint();
        //设置画笔颜色
        paint.setColor(Color.WHITE);
        //设置画笔粗细
        paint.setStrokeWidth(3);

        //x线
        drawAxisX(canvas, paint);
        drawAxisY(canvas, paint);
        //x字
        drawTextX(canvas, paint);
        drawMarkX(canvas, paint);
        drawMarkY(canvas, paint);
        drawPoint(canvas, paint);
        drawLine(canvas, paint);


    }

    private void drawLine(Canvas canvas, Paint paint) {

        Point lastPoint = null;
        for (int i = 0; i < mPoints.size(); i++) {
            Point currentP = mPoints.get(i);
            if (i != 0) {
                canvas.drawLine(lastPoint.x, lastPoint.y, currentP.x, currentP.y, paint);
            }
            lastPoint = currentP;
        }
    }

    private void drawPoint(Canvas canvas, Paint paint) {
        float length = (mWidth - PADDING_WIDTH * 2) / (tvSizes + 1);
        float heightY = mHeight - PADDING_HEIGHT * 2;
        mPoints.clear();
        for (int i = 0; i < mRecordEntities.size(); i++) {

            float cx = PADDING_WIDTH + length * (i + 1);
            float cy = (30 - mRecordEntities.get(i).getYuan()) * heightY / 30 + PADDING_HEIGHT;
            float radius = 5;
            canvas.drawCircle(cx, cy, radius, paint);
            mPoints.add(new Point(cx, cy));

        }
    }

    private void drawMarkX(Canvas canvas, Paint paint) {

        float length = (mWidth - PADDING_WIDTH * 2) / (xNums + 2);

        for (int i = 0; i < xNums; i++) {
            float startX = PADDING_WIDTH + length * (i + 1);
            float startY = mHeight - PADDING_HEIGHT - 10;
            float stopY = mHeight - PADDING_HEIGHT;
            canvas.drawLine(startX, startY, startX, stopY, paint);

        }
        //画箭头
        //上
        canvas.drawLine(mWidth-PADDING_WIDTH,mHeight-PADDING_HEIGHT,mWidth-PADDING_WIDTH-10,mHeight-PADDING_HEIGHT-10,paint);
        //下
        canvas.drawLine(mWidth-PADDING_WIDTH,mHeight-PADDING_HEIGHT,mWidth-PADDING_WIDTH-10,mHeight-PADDING_HEIGHT+10,paint);
        //画月份
        paint.setTextSize(30);
        canvas.drawText("月份",mWidth-PADDING_WIDTH-40,mHeight-PADDING_HEIGHT+40,paint);

    }

    private void drawTextX(Canvas canvas, Paint paint) {
        float length = (mWidth - PADDING_WIDTH * 2) / (tvSizes + 1);

        for (int i = 0; i < tvSizes; i++) {
            float startX = PADDING_WIDTH + length * (i + 1);
            float stopY = mHeight - PADDING_HEIGHT;
            float x = startX - 20;
            float y = stopY + 35;
            paint.setTextSize(30);
            canvas.drawText(names[i], x, y, paint);
        }
        //加入0
        float y=mHeight-PADDING_HEIGHT+30;
        paint.setTextSize(30);
        canvas.drawText("0",PADDING_WIDTH-30,y,paint);


    }

    private void drawMarkY(Canvas canvas, Paint paint) {
        float length = (mHeight - PADDING_HEIGHT * 2) / (COUNT_Y_MARK + 1);

        for (int i = 0; i < COUNT_Y_MARK; i++) {
            float startX = PADDING_WIDTH;
            float startY = PADDING_HEIGHT + length * (i + 1);
            float stopX = PADDING_WIDTH +10;
            float x = startX-35;
            float y= startY+10;
            canvas.drawLine(startX, startY, stopX, startY, paint);
            paint.setTextSize(25);
            canvas.drawText(String.valueOf(nums[i]),x,y,paint);
        }
        paint.setTextSize(30);
        canvas.drawText("油耗",PADDING_WIDTH-20,PADDING_HEIGHT-20,paint);

        //画箭头
        //左
        canvas.drawLine(PADDING_WIDTH,PADDING_HEIGHT,PADDING_WIDTH-10,PADDING_HEIGHT+10,paint);
        //右
        canvas.drawLine(PADDING_WIDTH,PADDING_HEIGHT,PADDING_WIDTH+10,PADDING_HEIGHT+10,paint);


    }

    private void drawAxisX(Canvas canvas, Paint paint) {
        float startX = PADDING_WIDTH;
        float startY = mHeight - PADDING_HEIGHT;
        float stopX = mWidth - PADDING_WIDTH;
        float stopY = startY;
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    private void drawAxisY(Canvas canvas, Paint paint) {
        float startY = PADDING_HEIGHT;
        float stopY = mHeight - PADDING_HEIGHT;
        float startX = PADDING_WIDTH;
        float stopX = startX;
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    private static class Point {
         final float x;
         final float y;

         Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }


}
