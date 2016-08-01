package com.example.peesit.playground;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ChoiJa on 2016-07-28.
 */

public class BirthActivity extends AppCompatActivity {


    Context mContext;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_birth);


        Intent intent = getIntent();

        String mYear = intent.getExtras().getString("year");
        String mMonth = intent.getExtras().getString("month");
        String mDay = intent.getExtras().getString("day");


        final EditText year = (EditText)findViewById(R.id.sYear);
        final EditText month = (EditText)findViewById(R.id.sMonth);
        final EditText day = (EditText)findViewById(R.id.sDay);
        final Button closeBtn = (Button)findViewById(R.id.closeBtn);
        final Button compBtn = (Button)findViewById(R.id.comp);


        year.setText(mYear);
        month.setText(mMonth);
        day.setText(mDay);


        compBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("year",year.getText().toString());
                intent.putExtra("month",month.getText().toString());
                intent.putExtra("day",day.getText().toString());

                setResult(0, intent);

                finish();

            }

        });


        closeBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);

<<<<<<< HEAD
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

=======
>>>>>>> 5d63b3c94c98620771b8e0a50695406d046983fc
                startActivity(intent);

                finish();

            }

        });

    }

}