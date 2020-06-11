package com.example.memhelper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.memhelper.DBHelper;
import com.example.memhelper.DBUtil;
import com.example.memhelper.R;
import com.example.memhelper.entity.Card;

import java.util.List;

public class CardTestActivity extends AppCompatActivity {
    Button butCardMemory;
    Button butPassageMemory;
    private List<Card> cardList;
    private Card currentCard;
    private TextView texCurrentSide;
    private TextView texCardContent;
    private Button butFinish;
    private Button butNext;
    private int cardsetId;
    private int currentSide;
    private final int SIDE_FRONT = 0;
    private final int SIDE_BACK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_test);
        //获取卡片集ID
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cardsetId = bundle.getInt("cardsetId");
        //读取卡片集内的所有卡片
        loadCards();
        //初始化控件和currentSide
        initTexts();
        initButtons();
        nextCard();
        butCardMemory = findViewById(R.id.but_card);
        butCardMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardTestActivity.this, CarsetActivity.class);
                startActivity(intent);
            }
        });
        butPassageMemory = findViewById(R.id.but_passage);
        butPassageMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardTestActivity.this, PassageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initButtons(){
        butFinish = findViewById(R.id.but_cardtest_finish);
        butFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardTestActivity.this, CardListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("cardsetId", cardsetId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        butNext = findViewById(R.id.but_next_card);
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextCard();
            }
        });
    }

    private void initTexts(){
        currentSide = SIDE_FRONT;
        texCurrentSide = findViewById(R.id.tex_current_side);
        texCardContent = findViewById(R.id.tex_card_content);
        texCardContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flip();
            }
        });
    }

    private void nextCard(){
        if(cardList.isEmpty() == false){
            currentCard = cardList.remove(0);
            Log.d("剩余卡片：",cardList.size()+"");
            currentSide = SIDE_FRONT;
            texCurrentSide.setText(R.string.front_side);
            texCardContent.setText(currentCard.getFront());
            texCardContent.setBackgroundColor(Color.LTGRAY);
        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(CardTestActivity.this).create();
            alertDialog.setTitle("无剩余卡片");
            alertDialog.setMessage("所有卡片都看完了，要重新加载卡片吗？");
            //确定按钮
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "要", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    loadCards();
                    nextCard();
                }
            });
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "不，回卡片集", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(CardTestActivity.this, CardListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("cardsetId", cardsetId);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            alertDialog.show();
        }
    }

    private void loadCards(){
        DBUtil dbUtil = new DBUtil(new DBHelper(this));
        cardList = dbUtil.getCardByCardsetId(cardsetId);
    }

    //to do
    private void flip(){
        switch (currentSide){
            case SIDE_FRONT:
                currentSide = SIDE_BACK;
                texCurrentSide.setText(R.string.back_side);
                texCardContent.setText(currentCard.getBack());
                texCardContent.setBackgroundColor(Color.CYAN);
                break;
            case SIDE_BACK:
                currentSide = SIDE_FRONT;
                texCurrentSide.setText(R.string.front_side);
                texCardContent.setText(currentCard.getFront());
                texCardContent.setBackgroundColor(Color.LTGRAY);
                break;
        }
    }
}
