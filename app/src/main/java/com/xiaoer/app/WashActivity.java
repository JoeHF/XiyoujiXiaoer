package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xiaoer.app.Constant.Constant;
import com.xiaoer.app.Util.RestClient;

/**
 * Created by houfang on 2015/4/29.
 */
public class  WashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.washing);
        SharedPreferences mySharedPreferences = getSharedPreferences("user",
                Activity.MODE_PRIVATE);
        String price = mySharedPreferences.getString("price", "0");
        String type= mySharedPreferences.getString("type", "0");
        String inf= mySharedPreferences.getString("inf", "0");
        String sitename= mySharedPreferences.getString("sitename", "0");
        TextView textView=(TextView) findViewById(R.id.inf);
        textView.setText(inf);
        textView=(TextView) findViewById(R.id.price);
        textView.setText("费用总额：￥"+price);
        textView=(TextView)  findViewById(R.id.sitename);
        textView.setText(sitename);
        textView=(TextView) findViewById(R.id.type);

        if(type.equals("0"))
        {
           textView.setText("车外清洗");
        }
        else
        {
            textView.setText("车内清洗");
        }
        if(type.equals(null))
        {
            textView.setText("未获取");
        }
    }
    public void click_to_photoFinish(View v) {
        SharedPreferences mySharedPreferences = getSharedPreferences("user",
                Activity.MODE_PRIVATE);
        String code = mySharedPreferences.getString("code", "0");
        String password= mySharedPreferences.getString("password", "0");
        String orderid= mySharedPreferences.getString("orderid", "0");
        RequestParams requestParams = new RequestParams();
        requestParams.put("code",code);

        requestParams.put("password", password);
        requestParams.put("orderid", orderid);
        RestClient.get(Constant.endCleanCarByAccount, requestParams, new JsonHttpResponseHandler() {

        });
        Intent intent = new Intent();
        intent.setClass(this, photoFinishActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out);

    }
    public void click_to_back(View v) {
        finish();
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
    }

}