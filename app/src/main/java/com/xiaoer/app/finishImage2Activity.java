package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by houfang on 2015/4/29.
 */
public class finishImage2Activity extends Activity {
    private Myapp myApp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_image2);
        myApp = (Myapp) getApplication();
        myApp.setLabel("1");
        int number=myApp.getNum2();
        TextView image1 = (TextView) findViewById(R.id.number);
        number=4-number;
        String numbertext=String.valueOf(number);
        image1.setText(numbertext);

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
        intent.setClass(this, Image4Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);
    }

}
