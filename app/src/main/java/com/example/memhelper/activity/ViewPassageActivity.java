package com.example.memhelper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.memhelper.DBHelper;
import com.example.memhelper.DBUtil;
import com.example.memhelper.R;
import com.example.memhelper.entity.Passage;
import com.example.memhelper.view.TestTextView;

public class ViewPassageActivity extends AppCompatActivity {

    Button butCardMemory;
    Button butPassageMemory;
    private Button butClear;
    private Button butRestore;
    private Button butBack;
    private Button butEdit;
    private TestTextView texPassage;
    private Passage passage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_passage);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int passageId = bundle.getInt("passageId");
        passage = new DBUtil(new DBHelper(this)).getPassageById(passageId);
        texPassage = findViewById(R.id.tex_viewpas);
        texPassage.setText(passage.getContent());
        initButtons();
        butCardMemory = findViewById(R.id.but_card);
        butCardMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPassageActivity.this, CarsetActivity.class);
                startActivity(intent);
            }
        });
        butPassageMemory = findViewById(R.id.but_passage);
        butPassageMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPassageActivity.this, PassageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initButtons(){
        butClear = findViewById(R.id.but_viewpas_clear);
        butClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texPassage.clear();
            }
        });
        butRestore = findViewById(R.id.but_viewpas_restore);
        butRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texPassage.restore();
            }
        });
        butBack = findViewById(R.id.but_viewpas_back);
        butBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPassageActivity.this, PassageActivity.class);
                startActivity(intent);
            }
        });
        butEdit = findViewById(R.id.but_viewpas_edit);
        butEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewPassageActivity.this, EditBarActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("passage", passage);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
