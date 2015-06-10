package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

/**
 * Created by houfang on 2015/4/29.
 */
public class Image2Activity extends Activity {
    private Myapp myApp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upimagine2);
        myApp = (Myapp) getApplication();
       int number=myApp.getNum1();
       TextView image1 = (TextView) findViewById(R.id.number);
        number=4-number;
        String numbertext=String.valueOf(number);
        image1.setText(numbertext);
        myApp.setLabel("0");
    }
    public void click_to_photo(View v)
    {
        Intent intent = new Intent();
        intent.setClass(this, Image3Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);
    }
    public void click_to_search(View v)
    {

        Intent intent = new Intent();
        intent.setClass(this, ImageGalleryDemoActivity.class);
     //   intent.setClass(this, Image4Activity.class);
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
