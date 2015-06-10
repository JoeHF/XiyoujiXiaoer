package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by houfang on 2015/4/29.
 */
public class  photoFinish1Activity extends Activity {
    private Myapp myapp1;
    private List list;
    private int number;
    int pictureid[]={R.id.picture1,R.id.picture2,R.id.picture3,R.id.picture4};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_finish1);
        myapp1 = (Myapp) getApplication();
        list=myapp1.getlist2();
        number=myapp1.getNum2();
        if (number>=1)
        {
            ImageView image1=(ImageView)  findViewById(R.id.picture1);
            Bitmap bitmap=Tool.getLoacalBitmap(list.get(0).toString());
            image1.setImageBitmap(bitmap);
            image1.setVisibility(View.VISIBLE);


        }
        if (number>=2)
        {
            ImageView image1=(ImageView)  findViewById(R.id.picture2);
            Bitmap bitmap=Tool.getLoacalBitmap(list.get(1).toString());
            image1.setImageBitmap(bitmap);
            image1.setVisibility(View.VISIBLE);

        }
        if (number>=3)
        {
            ImageView image1=(ImageView)  findViewById(R.id.picture3);
            Bitmap bitmap=Tool.getLoacalBitmap(list.get(2).toString());
            image1.setImageBitmap(bitmap);
            image1.setVisibility(View.VISIBLE);

        }
        if (number>=4)
        {
            ImageView image1=(ImageView)  findViewById(R.id.picture4);
            Bitmap bitmap=Tool.getLoacalBitmap(list.get(3).toString());
            image1.setImageBitmap(bitmap);
            image1.setVisibility(View.VISIBLE);

        }
        if (number<4)
        {
            ImageView image1;
            image1=(ImageView)  findViewById(pictureid[number]);
            image1.setClickable(true);
            image1.setVisibility(View.VISIBLE);


        }

    }
    public void click_to_upimage2(View v)
    {
        Intent intent = new Intent();
        intent.setClass(this, finishImage2Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);
    }
    public void click_to_homepage(View v)
    {
        myapp1.intialize();
        Intent intent = new Intent();
        intent.setClass(this, HomepageActivity.class);
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

