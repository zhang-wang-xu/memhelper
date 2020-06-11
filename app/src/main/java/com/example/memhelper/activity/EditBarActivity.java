package com.example.memhelper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.memhelper.DBHelper;
import com.example.memhelper.DBUtil;
import com.example.memhelper.R;
import com.example.memhelper.entity.Passage;
import com.example.memhelper.view.PassageView;

public class EditBarActivity extends AppCompatActivity {

    Button butCardMemory;
    Button butPassageMemory;
    private PassageView texPassage;
    private Button butFinish;
    private Button butCancel;
    private Passage passage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        passage = (Passage)bundle.getSerializable("passage");
        initButtons();
        butCardMemory = findViewById(R.id.but_card);
        butCardMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditBarActivity.this, CarsetActivity.class);
                startActivity(intent);
            }
        });
        butPassageMemory = findViewById(R.id.but_passage);
        butPassageMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditBarActivity.this, PassageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initTexPassage();
    }

    private void initTexPassage(){
        texPassage = findViewById(R.id.tex_addbar);
        texPassage.setEncodedText(passage.getContent());
    }


    private void initButtons(){
        butCancel = findViewById(R.id.but_addbar_cancel);
        butCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditBarActivity.this, ViewPassageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("passageId", passage.getPassageId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        butFinish = findViewById(R.id.but_addbar_finish);
        butFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBUtil dbUtil = new DBUtil(new DBHelper(EditBarActivity.this));
                dbUtil.modifyPassage(passage.getPassageId(), texPassage.getPassage(), passage.getTitle());
                Intent intent = new Intent(EditBarActivity.this, ViewPassageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("passageId", passage.getPassageId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
