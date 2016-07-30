package org.androidtown.testapp1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    ImageView imView;
    TextView txtView;

    phpDown task; //웹서버의 php를 실행하기 위한 인스턴스

    ArrayList<ListItem> listitem = new ArrayList<ListItem>();
    ListItem Item;
    //ListItem객체는 JSON형식으로 데이터를 셋팅하기 위한 클래스
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        task = new phpDown();
        txtView = (TextView)findViewById(R.id.txtView);

        task.execute("http://en605.woobi.co.kr/gcmtest.php");//도메인을 실행하게되면 php가 실행되서 데이터전달이 일어난다.


    }

    private class phpDown extends AsyncTask<String, Integer,String>{

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{
                // 연결 url 설정
                URL url = new URL(urls[0]);
                // 커넥션 객체 생성
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                // 연결되었으면.
                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    // 연결되었음 코드가 리턴되면.
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        for(;;){
                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                            String line = br.readLine();
                            if(line == null) break;
                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
            return jsonHtml.toString();

        }

        /*
        protected void onPostExecute(String str){
            txtView.setText(str);
        }
        */

        protected void onPostExecute(String str){
            String id;

            try{
                JSONObject root = new JSONObject(str);
                JSONArray ja = root.getJSONArray("results"); //get the JSONArray which I made in the php file. the name of JSONArray is "results"

                for(int i=0;i<ja.length();i++){
                    JSONObject jo = ja.getJSONObject(i);
                    id = jo.getString("id");
                    listitem.add(new ListItem(id));
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            txtView.setText("id : "+listitem.get(0).getData(0));
        }

    }

}