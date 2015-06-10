package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xiaoer.app.Constant.Constant;
import com.xiaoer.app.Util.RestClient;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {

    private EditText username, password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

    public void click_to_homepage(View v) {
        final String username_value = username.getText().toString();
        final String password_value = password.getText().toString();

        RequestParams requestParams = new RequestParams();
        requestParams.put("code", username_value);
        requestParams.put("password", password_value);
        Log.i("code",username_value);
        Log.i("password",password_value);
        RestClient.get(Constant.LOGIN, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("http login jsonobject", response.toString());
                try {
                    String star=response.getString("star");
                    String count=response.getString("count");
                    String income=response.getString("income");
                    String waiterid=response.getString("waiterid");
                    String longitude = response.getString("long");
                    String latitude = response.getString("lat");
                    String code=response.getString("code");

                    //实例化SharedPreferences对象（第一步）
                    SharedPreferences mySharedPreferences = getSharedPreferences("user",
                            Activity.MODE_PRIVATE);
                    //实例化SharedPreferences.Editor对象（第二步）
                    SharedPreferences.Editor editor = mySharedPreferences.edit();
                    //用putString的方法保存数据
                    editor.putString("star", star);
                    editor.putString("count",count );
                    editor.putString("code", code);
                    editor.putString("waiterid",waiterid );
                    editor.putString("longitude", longitude);
                    editor.putString("latitude", latitude);
                    editor.putString("income", income);
                    editor.putString("password",password_value);
                    //提交当前数据
                    editor.commit();
                    Log.i("a","a");
                    Intent intent1 = new Intent();
                    intent1.setClass(MainActivity.this, HomepageActivity.class);
                    startActivity(intent1);
                    overridePendingTransition(R.anim.push_left_in,
                            R.anim.push_left_out);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}






