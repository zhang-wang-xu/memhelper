package com.example.memhelper.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CardView extends View {
    private String front;
    private String back;
    private String STRING_FRONT = "正面:";
    private String STRING_BACK = "背面：";
    private int side;
    private final int SIDE_FRONT = 0;
    private final int SIDE_BACK = 1;
    private Paint paintFront;
    private Paint paintBack;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(side == SIDE_FRONT){

        }
    }

    private void init(){
        side = SIDE_FRONT;
        paintFront = new Paint();
        paintFront.setColor(Color.CYAN);
        paintBack = new Paint();
        paintBack.setColor(Color.BLUE);
    }

    public void setFront(String front){
        this.front = front;
    }

    public void setBack(String back){
        this.back = back;
    }

    public CardView(Context context) {
        super(context);
    }

    public CardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
