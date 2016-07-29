package com.example.peesit.playground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;


/**
 * Created by ChoiJa on 2016-07-26.
 */
public class ClauseActivity extends AppCompatActivity {

    Button btn_check;
    Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clause);

        final RadioButton rbtn_agree = (RadioButton) findViewById(R.id.rbtn_agree);
        final RadioButton rbtn_nonagree = (RadioButton) findViewById(R.id.rbtn_nonagree);
        btn_check = (Button) findViewById(R.id.btnClauseCheck);
        btn_cancel = (Button) findViewById(R.id.btnClauseCancel);

        btn_check.setEnabled(false);



        rbtn_agree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btn_check.setEnabled(true);
                btn_check.setClickable(true);
                btn_check.setFocusable(true);
            }
        });


        rbtn_nonagree.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btn_check.setEnabled(false);
                btn_check.setClickable(false);
                btn_check.setFocusable(false);

            }
        });


        btn_check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ClauseActivity.this, SignUpActivity.class); //인텐트 생성(현 액티비티, 새로 실행할 액티비티)
                startActivity(i);
                finish();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

