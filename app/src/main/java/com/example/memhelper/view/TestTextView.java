package com.example.memhelper.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.memhelper.entity.Char;
import com.example.memhelper.entity.Char2;

import java.util.ArrayList;

@SuppressLint("AppCompatCustomView")
public class TestTextView extends TextView {
    final int INITIAL = 0; //初始状态，显示文本和黑块
    final int CLEAR = 1; //清屏状态，只显示文本不显示黑块
    final int ERASE = 2;
    final int TOUCH_ERASE = 3; //擦除状态，把触摸到的黑块变成文本
    final int TOUCH_COVER = 4;
    private ArrayList<Char2> passage;
    Paint paint, redPaint;
    private int size = 70;
    private int col = 12;
    int state = INITIAL;
    int touchMode;

    public TestTextView(Context context) {
        super(context);
        init();
    }

    public TestTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setText(String unconversedText){
        for(int i = 0; i < unconversedText.length()-1; i+=2){
            if(unconversedText.charAt(i) == '1'){ // hidden
                passage.add(new Char2(unconversedText.charAt(i+1)+"", true));
            }
            else{
                passage.add(new Char2(unconversedText.charAt(i+1)+"", false));
            }
        }
    }

    private void init(){
        passage = new ArrayList<>();

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(1);
        paint.setTextSize(size);

        redPaint = new Paint();
        redPaint.setColor(Color.BLACK);
        redPaint.setTextSize(size);
        redPaint.setStrokeWidth(1);
        redPaint.setShadowLayer(1, 3, 3, Color.BLACK);
    }

    public void clear(){
        state = CLEAR;
        invalidate();
    }

    public void restore(){
        state = INITIAL;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        state = ERASE;
        int x = (int)event.getX();
        int y = (int)event.getY();
        if(event.getAction() == event.ACTION_DOWN) setTouchMode(x, y);
        touchChar(x, y);
        invalidate();
        return true;
    }

    private void touchChar(int x, int y){
        int r = y / size;
        int c = x / size;
        int index = r * col + c;
        if(index < passage.size()){
            switch (touchMode){
                case TOUCH_COVER:
                    passage.get(index).setErased(false);
                    break;
                case TOUCH_ERASE:
                    passage.get(index).setErased(true);
                    break;
            }
        }
    }

    //如果触摸的是黑块，则把touchMode设置为ERASE，否则设置为COVER。
    private void setTouchMode(int x, int y){
        int r = y / size;
        int c = x / size;
        int index = r * col + c;
        if(index < passage.size()){
            if(passage.get(index).isErased()){
                touchMode = TOUCH_COVER;
            }
            else{
                touchMode = TOUCH_ERASE;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (state){
            case INITIAL:
                drawInitial(canvas);
                break;
            case CLEAR:
                drawClear(canvas);
                break;
            case ERASE:
                drawErase(canvas);
                break;
        }
    }

    //显示文本和黑块
    private void drawInitial(Canvas canvas){
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
    }

    //只显示文本，不显示黑块
    private void drawClear(Canvas canvas){
        int r, c;
        for(int i = 0; i < passage.size(); i++){
            r = i / col;
            c = i % col;
            float offsetX = (float) 5;
            float offsetY = (float) (size*0.9);
            canvas.drawText(passage.get(i).getCh() + "", c * size + offsetX, r * size + offsetY, paint);
        }
    }

    private void drawErase(Canvas canvas){
        int r, c;
        for(int i = 0; i < passage.size(); i++){
            r = i / col;
            c = i % col;
            float offsetX = (float) 5;
            float offsetY = (float) (size*0.9);
            if(passage.get(i).isHidden()){
                if(passage.get(i).isErased()){
                    canvas.drawText(passage.get(i).getCh() + "", c * size + offsetX, r * size + offsetY, redPaint);
                }
                else{
                    canvas.drawText( "█", c * size + offsetX, r * size + offsetY, paint);
                }
            }
            else {
                canvas.drawText(passage.get(i).getCh() + "", c * size + offsetX, r * size + offsetY, paint);
            }
        }
    }
}
