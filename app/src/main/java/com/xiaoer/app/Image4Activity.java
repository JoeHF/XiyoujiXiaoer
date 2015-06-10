package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by houfang on 2015/4/29.
 */
public class Image4Activity extends Activity {

    private Myapp  myapp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upimagine4);
        myapp = (Myapp) getApplication();
    }
    public void click_to_image6(View v)
    {
        String a=myapp.getLabel();
        Intent intent = new Intent();
        if(a.equals("0"))
            intent.setClass(this, Image6Activity.class);
        if(a.equals("1"))
            intent.setClass(this, photoFinish1Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);
    }
    public void click_to_back(View v) {
        finish();
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
    }
}
