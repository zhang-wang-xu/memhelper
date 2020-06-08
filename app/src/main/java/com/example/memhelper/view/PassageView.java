package com.example.memhelper.view;

import android.annotation.SuppressLint;
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
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.memhelper.entity.Char;

import java.util.ArrayList;

@SuppressLint("AppCompatCustomView")
public class PassageView extends TextView {
    final int TOUCH_ERASE = 0;
    final int TOUCH_COVER = 1;
    int touchMode;
    Paint paint;
    private int size = 70;
    private int col = 12;
    ArrayList<Char> passage;

    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(1);
        paint.setTextSize(size);
        passage = new ArrayList<>();
        String text = getText()+"";
        for(int i = 0; i < text.length(); i++){
            passage.add(new Char(text.charAt(i)+""));
        }
    }

    public ArrayList<Char> getPassage(){
        return passage;
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

    public void setText(String text){
        passage = new ArrayList<>();
        for(int i = 0; i < text.length(); i++){
            passage.add(new Char(text.charAt(i)+""));
        }
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        if(event.getAction() == event.ACTION_DOWN){
            setTouchMode(x, y);
        }
        touchChar(x, y);
        invalidate(); //重新绘制区域
        return true;
    }

    //如果触摸的是黑块，则把touchMode设置为ERASE，否则设置为COVER。
    private void setTouchMode(int x, int y){
        int r = y / size;
        int c = x / size;
        int index = r * col + c;
        if(index < passage.size()){
            if(passage.get(index).isHidden()){
                touchMode = TOUCH_ERASE;
            }
            else{
                touchMode = TOUCH_COVER;
            }
        }
    }

    //隐藏/显示字符
    //输入：触摸事件的横坐标和纵坐标
    private void touchChar(int x, int y){
        int r = y / size;
        int c = x / size;
        int index = r * col + c;
        if(index < passage.size()){
            switch (touchMode){
                case TOUCH_COVER:
                    passage.get(index).setHidden(true);
                    break;
                case TOUCH_ERASE:
                    passage.get(index).setHidden(false);
                    break;
            }
        }
    }

    //在这里测试canvas提供的绘制图形方法
    @Override
    protected void onDraw(Canvas canvas) {
/*        for(int i = 0; i < 10; i++){
            canvas.drawLine(0, i*size, getMeasuredWidth()+0, i*size, paint);
        }
        for(int i = 0; i < col; i++){
            canvas.drawLine(i*size, 0, i*size, getMeasuredHeight(), paint);
        }*/
        String text = getText()+"";
        int r, c;
        for(int i = 0; i < passage.size(); i++){
            r = i / col;
            c = i % col;
            float offsetX = (float) 5;
            float offsetY = (float) (size*0.9);
            if(passage.get(i).isHidden()){
                canvas.drawText( "█", c * size + offsetX, r * size + offsetY, paint);
            }
            else {
                canvas.drawText(passage.get(i).getCh() + "", c * size + offsetX, r * size + offsetY, paint);
            }
        }
//        TextPaint textPaint = new TextPaint();
//        textPaint.setColor(Color.BLACK);
//        textPaint.setTextSize(50.0F);
//        StaticLayout layout = new StaticLayout(text, textPaint, getWidth() - 100, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
//        这里的参数300，表示字符串的长度，当满300时，就会换行，也可以使用“\r\n”来实现换行
//        canvas.save();
//        canvas.translate(100,100);//从100，100开始画
//        layout.draw(canvas);
//        canvas.restore();//别忘了restore
    }
}
