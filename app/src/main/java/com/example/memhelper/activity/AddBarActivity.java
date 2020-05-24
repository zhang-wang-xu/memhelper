package com.example.memhelper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.memhelper.R;
import com.example.memhelper.entity.Passage;
import com.example.memhelper.view.PassageView;

public class AddBarActivity extends AppCompatActivity {
    Button butBack;
    Button butFinish;
    Button butAddRandom;
    PassageView texPassage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bar);
        initButBack();
        initTexPassage();


    }

    private void initTexPassage(){
        texPassage = findViewById(R.id.tex_addbar_passage);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Passage passage = (Passage)bundle.getSerializable("passage");
        //texPassage.setText(passage.getContent());
    }

    private void initButBack(){
        butBack = findViewById(R.id.but_add_back);
        butBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddBarActivity.this, AddPassageActivity.class);
                startActivity(intent);
            }
        });
    }
}
