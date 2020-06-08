package com.example.memhelper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.memhelper.R;
import com.example.memhelper.entity.Passage;

public class EditBarActivity extends AppCompatActivity {

    private Button butBack;
    private Passage passage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        passage = (Passage)bundle.getSerializable("passage");
        initButtons();
    }

    private void initButtons(){
        butBack = findViewById(R.id.but_addbar_back);
        butBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditBarActivity.this, ViewPassageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("passage", passage);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
