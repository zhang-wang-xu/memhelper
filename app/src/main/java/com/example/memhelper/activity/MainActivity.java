package com.example.memhelper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.memhelper.R;

public class MainActivity extends AppCompatActivity {
    Button butPassageMem; //篇章记忆按钮
    Button butCardMem; //卡片记忆按钮
    Intent intentToPassage;
    Intent intentToCardset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentToPassage  = new Intent(MainActivity.this, PassageActivity.class);
        intentToCardset = new Intent(MainActivity.this,CarsetActivity.class);
        butPassageMem = findViewById(R.id.butPassageMem);
        butCardMem = findViewById(R.id.butCardMem);
        butCardMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentToCardset);
            }
        });
        butPassageMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentToPassage);
            }
        });
    }
}
