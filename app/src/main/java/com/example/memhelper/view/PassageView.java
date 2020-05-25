package com.example.memhelper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PassageView extends View {
    Paint paint;
    private ArrayList<PointF> graphics = new ArrayList<PointF>();
    String text;

    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
        paint.setTextSize(100);
    }

    public void setText(String t){
        text = t;
    }

    public PassageView(Context context) {
        super(context);
        init();
    }

    public PassageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PassageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        graphics.add(new PointF(event.getX(),event.getY()));
        System.out.println(event.getX() + " " + event.getY());
        invalidate(); //重新绘制区域
        return true;
    }
    //在这里测试canvas提供的绘制图形方法
    @Override
    protected void onDraw(Canvas canvas) {
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(50.0F);
        StaticLayout layout = new StaticLayout(text, textPaint, getWidth() - 100, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
        // 这里的参数300，表示字符串的长度，当满300时，就会换行，也可以使用“\r\n”来实现换行
        canvas.save();
        canvas.translate(100,100);//从100，100开始画
        layout.draw(canvas);
        canvas.restore();//别忘了restore
    }
}
