package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by houfang on 2015/4/29.
 */
public class  showmeActivity extends Activity {
    String waiterid;
    String star;
    String income;
    String count;
    TextView textview;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showme);
        SharedPreferences mySharedPreferences= getSharedPreferences("user",
                Activity.MODE_PRIVATE);
        waiterid=mySharedPreferences.getString("waiterid","0");
        star=mySharedPreferences.getString("star","0");
        income=mySharedPreferences.getString("income","0");
       count=mySharedPreferences.getString("count","0");
        //实例化SharedPreferences.Editor对象（第二步）
        textview=(TextView)  findViewById(R.id.waiter);
        waiterid="小二"+waiterid;
        textview.setText(waiterid);
        textview=(TextView)  findViewById(R.id.count);

        textview.setText(count);
        textview=(TextView)  findViewById(R.id.star);

        textview.setText(star);
        textview=(TextView)  findViewById(R.id.income);

        textview.setText(income);

    }
    public void click_to_history(View v) {
        Intent intent = new Intent();
        intent.setClass(this, orderHistoryActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);

    }
    public void click_to_money(View v) {
        Intent intent = new Intent();
        intent.setClass(this, SumActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);

    }
    public void click_to_out(View v) {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);

    }
}