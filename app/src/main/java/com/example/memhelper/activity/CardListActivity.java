package com.example.memhelper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.memhelper.DBHelper;
import com.example.memhelper.DBUtil;
import com.example.memhelper.R;
import com.example.memhelper.entity.Card;

import java.util.List;

public class CardListActivity extends AppCompatActivity {
    class CardOnClickListener extends Card implements View.OnClickListener{
        public CardOnClickListener(int cardId,int cardsetId,String front,String back){
            this.cardId = cardId;
            this.cardsetId = cardsetId;
            this.front = front;
            this.back = back;
        }

        @Override
        public void onClick(View view) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View alertView = layoutInflater.inflate(R.layout.layout_add_card, null);
            ediBack = alertView.findViewById(R.id.edi_card_back);
            ediFront = alertView.findViewById(R.id.edi_card_front);
            ediBack.setText(back+"");
            ediFront.setText(front+"");
            final AlertDialog alertDialog = new AlertDialog.Builder(CardListActivity.this).create();
            alertDialog.setView(alertView);
            alertDialog.setTitle("卡片详情");
            alertDialog.setMessage("查看/修改卡片正反面的内容。");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "修改", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DBUtil dbUtil = new DBUtil((new DBHelper(CardListActivity.this)));
                    dbUtil.modifyCard(cardId, ediFront.getText()+"", ediBack.getText()+"");
                    initList();
                }
            });
            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "删除", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DBUtil dbUtil = new DBUtil((new DBHelper(CardListActivity.this)));
                    dbUtil.deleteCard(cardId);
                    initList();
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        }
    }

    Button butCardMemory;
    Button butPassageMemory;
    private Button butAdd;
    private Button butTest;
    private Button butBack;
    private EditText ediFront;
    private EditText ediBack;
    private LinearLayout layCards;
    private int cardsetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        initId();
        initButtons();
        initList();
        butCardMemory = findViewById(R.id.but_card);
        butCardMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardListActivity.this, CarsetActivity.class);
                startActivity(intent);
            }
        });
        butPassageMemory = findViewById(R.id.but_passage);
        butPassageMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardListActivity.this, PassageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initId(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        cardsetId = bundle.getInt("cardsetId");
    }

    private void initList(){
        layCards = findViewById(R.id.lay_card_cards);
        layCards.removeAllViews();
        DBUtil dbUtil = new DBUtil(new DBHelper(this));
        List<Card> cardList = dbUtil.getCardByCardsetId(cardsetId);
        Button button;
        for(Card card : cardList){
            button = new Button(this);
            button.setText(card.getFront());
            button.setOnClickListener(new CardOnClickListener(card.getCardId(),card.getCardsetId(),card.getFront(),card.getBack()));
            layCards.addView(button);
        }
    }

    private void initButtons(){
        butAdd = findViewById(R.id.but_card_add);
        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = getLayoutInflater();
                View alertView = layoutInflater.inflate(R.layout.layout_add_card, null);
                ediBack = alertView.findViewById(R.id.edi_card_back);
                ediFront = alertView.findViewById(R.id.edi_card_front);
                AlertDialog alertDialog = new AlertDialog.Builder(CardListActivity.this).create();
                alertDialog.setView(alertView);
                alertDialog.setTitle("添加卡片");
                alertDialog.setMessage("请输入卡片正反面的内容。");
                //确定按钮
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String front = ediFront.getText()+"";
                        String back = ediBack.getText()+"";
                        Card card = new Card(cardsetId, front, back);
                        DBUtil dbUtil = new DBUtil(new DBHelper(CardListActivity.this));
                        dbUtil.addCard(card);
                        Log.d("卡片信息：", card.toString());
                        initList();
                    }
                });
                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.show();
            }
        });

        butTest = findViewById(R.id.but_card_test);
        butTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardListActivity.this, CardTestActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("cardsetId", cardsetId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        butBack = findViewById(R.id.but_cardlist_back);
        butBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardListActivity.this, CarsetActivity.class);
                startActivity(intent);
            }
        });
    }
}
