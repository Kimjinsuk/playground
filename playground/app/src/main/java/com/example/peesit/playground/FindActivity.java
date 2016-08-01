package com.example.peesit.playground;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ChoiJa on 2016-08-01.
 */
public class FindActivity extends AppCompatActivity {

    Button btn_Find;
    Button btn_Cancel;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        btn_Find = (Button) findViewById(R.id.Find_Com);
        btn_Cancel = (Button) findViewById(R.id.Find_Cancel);

        btn_Find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindActivity.this,FindInfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                    }
        });
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
