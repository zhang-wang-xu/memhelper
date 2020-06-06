package com.example.memhelper.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.memhelper.entity.Char;

import java.util.ArrayList;

@SuppressLint("AppCompatCustomView")
public class TestTextView extends TextView {
    private ArrayList<Char> passage;
    Paint paint;
    private int size = 70;
    private int col = 12;

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
            if(unconversedText.charAt(i) == 1){ // hidden
                passage.add(new Char(unconversedText.charAt(i+1)+"", true));
            }
            else{
                passage.add(new Char(unconversedText.charAt(i+1)+"", false));
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i < 10; i++){
            canvas.drawLine(0, i*size, getMeasuredWidth()+0, i*size, paint);
        }
        for(int i = 0; i < col; i++){
            canvas.drawLine(i*size, 0, i*size, getMeasuredHeight(), paint);
        }
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
    }
}
