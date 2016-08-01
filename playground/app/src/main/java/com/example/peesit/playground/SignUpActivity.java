package com.example.peesit.playground;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private EditText etPhone;
    private EditText etPasswordConfirm;
    private EditText etPersonName;
    private Button btnDone;
    private Button btnCancel;

    TextView year, month, day;

    String temp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
        etPersonName = (EditText) findViewById(R.id.etPersonName);
        btnDone = (Button) findViewById(R.id.btnDone);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        year = (TextView)findViewById(R.id.mYear);
        month = (TextView)findViewById(R.id.mMonth);
        day = (TextView)findViewById(R.id.mDay);


        LinearLayout birth = (LinearLayout)findViewById(R.id.birth);


        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        temp = format.format(date);

        final String arr[] = temp.split("-");


        year.setText(arr[0]);
        month.setText(arr[1]);
        day.setText(arr[2]);

        birth.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),BirthActivity.class);

                intent.putExtra("year",arr[0]);
                intent.putExtra("month",arr[1]);
                intent.putExtra("day", arr[2]);

                startActivityForResult(intent, 1001);

            }

        });


        // 비밀번호 일치 검사
        etPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = etPassword.getText().toString();
                String confirm = etPasswordConfirm.getText().toString();

                if (password.equals(confirm)) {
                    etPassword.setBackgroundColor(Color.GREEN);
                    etPasswordConfirm.setBackgroundColor(Color.GREEN);
                } else {
                    etPassword.setBackgroundColor(Color.RED);
                    etPasswordConfirm.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 이메일 입력 확인
                if (etEmail.getText().toString().length() == 0) {
                    Toast.makeText(SignUpActivity.this, "Email을 입력하세요!", Toast.LENGTH_LONG).show();
                    etEmail.requestFocus();
                    return;
                }

                // 비밀번호 입력 확인
                if (etPassword.getText().toString().length() == 0) {
                    Toast.makeText(SignUpActivity.this, "비밀번호를 입력하세요!", Toast.LENGTH_LONG).show();
                    etPassword.requestFocus();
                    return;
                }

                // 비밀번호 확인 입력 확인
                if (etPasswordConfirm.getText().toString().length() == 0) {
                    Toast.makeText(SignUpActivity.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_LONG).show();
                    etPasswordConfirm.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if (!etPassword.getText().toString().equals(etPasswordConfirm.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_LONG).show();
                    etPassword.setText("");
                    etPasswordConfirm.setText("");
                    etPassword.requestFocus();
                    return;
                }
                //이름 입력 확인
                if (etPersonName.getText().toString().length() == 0) {
                    Toast.makeText(SignUpActivity.this, "이름을 입력하세요!", Toast.LENGTH_LONG).show();
                    etPersonName.requestFocus();
                    return;
                }
                //핸드폰 번호 입력 확인
                if (etPhone.getText().toString().length() == 0) {
                    Toast.makeText(SignUpActivity.this, "휴대폰번호를 입력하세요!", Toast.LENGTH_LONG).show();
                    etPhone.requestFocus();
                    return;
                }
                //생년월일 입력 확인
                if (year.getText().toString().length() == 0) {
                    Toast.makeText(SignUpActivity.this, "생년원일을 입력하세요!", Toast.LENGTH_LONG).show();
                    year.requestFocus();
                    return;
                }
                if (month.getText().toString().length() == 0) {
                    Toast.makeText(SignUpActivity.this, "생년원일을 입력하세요!", Toast.LENGTH_LONG).show();
                    month.requestFocus();
                    return;
                }
                if (day.getText().toString().length() == 0) {
                    Toast.makeText(SignUpActivity.this, "생년원일을 입력하세요!", Toast.LENGTH_LONG).show();
                    day.requestFocus();
                    return;
                }

                Intent result = new Intent();
                result.putExtra("email", etEmail.getText().toString());

                // 자신을 호출한 Activity로 데이터를 보낸다.
                setResult(RESULT_OK, result);

                insertToDatabase(etEmail.getText().toString(),etPassword.getText().toString(),etPhone.getText().toString(),etPersonName.getText().toString());

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override

    //생년월일을 위한 코드
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == 0){

            String temp2 = data.getExtras().getString("year");

            String temp3 = data.getExtras().getString("month");

            String temp4 = data.getExtras().getString("day");


            temp = temp2 + "년 " + temp3 + "월 " + temp4 + "일";


            year.setText(temp2);

            month.setText(temp3);

            day.setText(temp4);

        }

    }

        //여기서 부터 통신을 위한 코드

    public void insert(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String phone = etPhone.getText().toString();
        String personname = etPersonName.getText().toString();

        insertToDatabase(email, password, phone, personname);

    }

    private void insertToDatabase(String email, String password, String phone, String personname) {

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SignUpActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                try {
                    String email = (String) params[0];
                    String password = (String) params[1];
                    String phone = (String) params[2];
                    String personname = (String) params[3];

                    String link = "http://52.78.95.178:8080/user/registration/";  //여기 주소값은 바꿔줘야됨
                    String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    data += "&" + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8");
                    data += "&" + URLEncoder.encode("personname", "UTF-8") + "=" + URLEncoder.encode(personname, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    Log.e("result - ",  " Exception : " + e.getMessage());
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        task.execute(email, password, phone, personname);
    }
}


