package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;


public class MainActivity extends Activity {

    private EditText username, password;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
    }
    public void click_to_homepage(View v)
    {
        String username_value = username.getText().toString();
        String password_value = password.getText().toString();

        RequestParams requestParams = new RequestParams();
        requestParams.put("phone", username_value);
        requestParams.put("password", password_value);

        Intent intent = new Intent();
        intent.setClass(this, HomepageActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);
    }
}







