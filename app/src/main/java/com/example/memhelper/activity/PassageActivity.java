package com.example.memhelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memhelper.DBHelper;
import com.example.memhelper.DBUtil;
import com.example.memhelper.R;
import com.example.memhelper.entity.Passage;

import java.util.List;

public class PassageActivity extends AppCompatActivity {
    LinearLayout passageList;
    Button butAddPassage;
    DBUtil dbUtil = new DBUtil(new DBHelper(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passage);
        passageList = findViewById(R.id.lay_passage);
        java.util.List<Passage> passages = dbUtil.getPassages(); //从数据库中读取所有篇章
        showPassages(passages);
        initButAddPassage();
    }

    private void initButAddPassage(){
        butAddPassage = findViewById(R.id.but_add_passage);
        butAddPassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PassageActivity.this, AddPassageActivity.class);
                startActivity(intent);
            }
        });
    }

    //显示所有篇章
    private void showPassages(java.util.List<Passage> list){
        Button butPassage;
        for(final Passage passage : list){
            butPassage = new Button(this);
            butPassage.setText(passage.getTitle());
            butPassage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle idBundle = new Bundle();
                    idBundle.putInt("passageId", passage.getPassageId());
                    Intent intent = new Intent(PassageActivity.this, ViewPassageActivity.class);
                    intent.putExtras(idBundle);
                    startActivity(intent);
                    //Toast.makeText(PassageActivity.this, "" + passage.getPassageId(), Toast.LENGTH_SHORT).show();
                }
            });
            passageList.addView(butPassage);
        }
    }
}
