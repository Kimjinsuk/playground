package com.example.peesit.playground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by ChoiJa on 2016-07-26.
 */
public class ClauseActivity extends AppCompatActivity {

    Button btn;
    RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clause);

        radio = (RadioGroup) findViewById(R.id.rbtnclause);

        btn = (Button) findViewById(R.id.btnclasecheck);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //public int getCheckedRadioButtonId () : 선택된 라디오버튼의 ID값을 반환
                RadioButton rd = (RadioButton) findViewById(radio.getCheckedRadioButtonId());
                String str_Qtype = rd.getText().toString();

                Toast.makeText(getApplicationContext(), str_Qtype+" 선택됨",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
