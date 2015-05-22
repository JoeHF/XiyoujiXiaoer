package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class  ArriveActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arrive);
    }

    public void click_to_photo(View v) {
        Intent intent = new Intent();
        intent.setClass(this, OwnerphotoActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out);
    }

    public void click_to_myorder(View v) {
        Intent intent = new Intent();
        intent.setClass(this, MyOrderActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out);
    }

    public void click_to_imagine1(View v) {
        Intent intent = new Intent();
        intent.setClass(this, Image1Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out);

    }

    public void click_to_go(View v) {
        Intent intent = new Intent();
        intent.setClass(this, MyOrderActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out);

    }

    public void image1(View v) {
        Intent intent = new Intent();
        intent.setClass(this, Image1Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out);

    }
    public void click_to_image1(View v) {
        Intent intent = new Intent();
        intent.setClass(this, Image1Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out);

    }
}

