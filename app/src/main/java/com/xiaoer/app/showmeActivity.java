package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by houfang on 2015/4/29.
 */
public class  showmeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showme);
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