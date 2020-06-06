package com.example.memhelper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.memhelper.DBHelper;
import com.example.memhelper.DBUtil;
import com.example.memhelper.R;
import com.example.memhelper.entity.Passage;

public class AddPassageActivity extends AppCompatActivity {

    Button butBack;
    Button butProceed;
    EditText ediAddTitle;
    EditText ediAddContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_passage);
        ediAddTitle = findViewById(R.id.edi_add_title);
        ediAddContent = findViewById(R.id.edi_add_content);
        initButBack();
        initButProceed();
    }

    //创建passage对象，切换到AddBarActivity并将passage对象传递到AddBarActivity
    private void addPassage(){
        //DBUtil dbUtil = new DBUtil(new DBHelper(this));
        String title = ediAddTitle.getText() + "";
        String content = ediAddContent.getText() + "";
        Passage passage = new Passage(title, content);
        //dbUtil.insertPassage(passage);
        Intent intent = new Intent(this, AddBarActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("passage", passage);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //初始化返回按钮
    private void initButBack(){
        butBack = findViewById(R.id.but_add_back);
        butBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddPassageActivity.this, PassageActivity.class);
                startActivity(intent);
            }
        });
    }

    //初始化“下一步”按钮
    private void initButProceed(){
        butProceed = findViewById(R.id.but_add_proceed);
        butProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPassage();
            }
        });
    }
}
