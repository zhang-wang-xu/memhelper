package com.example.memhelper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PassageView extends View {
    Paint paint;
    private ArrayList<PointF> graphics = new ArrayList<PointF>();
    PointF point;

    public PassageView(Context context) {
        super(context);
        paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
    }

    public PassageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paint.setColor(Color.RED);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
    }

    public PassageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paint.setColor(Color.BLUE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        graphics.add(new PointF(event.getX(),event.getY()));
        System.out.println(event.getX() + " " + event.getY());
        invalidate(); //重新绘制区域
        return true;
    }
    //在这里我们将测试canvas提供的绘制图形方法
    @Override
    protected void onDraw(Canvas canvas) {
        for (PointF point : graphics) {
            canvas.drawPoint(point.x, point.y, paint);
        }
    }
}
