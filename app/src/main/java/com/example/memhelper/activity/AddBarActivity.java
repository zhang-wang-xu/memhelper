package com.example.memhelper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.memhelper.DBHelper;
import com.example.memhelper.DBUtil;
import com.example.memhelper.R;
import com.example.memhelper.entity.Passage;
import com.example.memhelper.view.PassageView;

public class AddBarActivity extends AppCompatActivity {
    Button butBack;
    Button butFinish;
    Button butAddRandom;
    Button butCancel;
    PassageView texPassage;
    DBUtil dbUtil;
    //String title; //篇章标题
    Passage passage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bar);
        dbUtil = new DBUtil(new DBHelper(this));
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        passage = (Passage)bundle.getSerializable("passage");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initButBack();
        initButFinish();
        initButCancel();
        initTexPassage();
    }

    private void initButCancel(){
        butCancel = findViewById(R.id.but_addbar_cancel);
        butCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddBarActivity.this, PassageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initButFinish(){
        butFinish = findViewById(R.id.but_addbar_finish);
        butFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbUtil.addPassage(texPassage.getPassage(), passage.getTitle());
                String text = dbUtil.testPassage();
                Intent intent = new Intent(AddBarActivity.this, PassageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initTexPassage(){
        texPassage = findViewById(R.id.tex_addbar);
        texPassage.setText(passage.getContent());
    }

    private void initButBack(){
        butBack = findViewById(R.id.but_addbar_back);
        butBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBarActivity.this, AddPassageActivity.class);
                startActivity(intent);
            }
        });
    }
}
