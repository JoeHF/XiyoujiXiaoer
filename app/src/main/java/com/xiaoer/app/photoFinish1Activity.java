package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by houfang on 2015/4/29.
 */
public class  photoFinish1Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_finish1);
    }
    public void click_to_upimage2(View v)
    {
        Intent intent = new Intent();
        intent.setClass(this, finishImage2Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);
    }
}

