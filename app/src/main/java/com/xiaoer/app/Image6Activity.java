package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xiaoer.app.Constant.Constant;
import com.xiaoer.app.Util.RestClient;

import java.util.List;

/**
 * Created by houfang on 2015/4/29.
 */
public class Image6Activity extends Activity {
  private Myapp myapp1;
    private List list;
    private int number;

    int pictureid[]={R.id.picture1,R.id.picture2,R.id.picture3,R.id.picture4};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upimagine6);
        myapp1 = (Myapp) getApplication();
        list=myapp1.getlist1();
        number=myapp1.getNum1();
        Log.i("click", "number");
        ImageView image1;


        if (number>=1)
        {
            image1=(ImageView)  findViewById(R.id.picture1);
            Log.i("click",list.get(0).toString());

            image1.setImageBitmap(Tool.getLoacalBitmap(list.get(0).toString()));

            image1.setVisibility(View.VISIBLE);


        }
        if (number>=2)
        {
            Log.i("click","number2");
             image1=(ImageView)  findViewById(R.id.picture2);
            Log.i("click",list.get(1).toString());

            Log.i("click","inumber2");
            image1.setImageBitmap(Tool.getLoacalBitmap(list.get(1).toString()));

            image1.setVisibility(View.VISIBLE);

        }
        if (number>=3)
        {
           image1=(ImageView)  findViewById(R.id.picture3);

            image1.setImageBitmap(Tool.getLoacalBitmap(list.get(2).toString()));


            image1.setVisibility(View.VISIBLE);

        }
        if (number>=4)
        {
            image1=(ImageView)  findViewById(R.id.picture4);
            image1.setImageBitmap(Tool.getLoacalBitmap(list.get(3).toString()));


            image1.setVisibility(View.VISIBLE);

        }
        if (number<4)
        {
            image1=(ImageView)  findViewById(pictureid[number]);
            image1.setClickable(true);
            image1.setVisibility(View.VISIBLE);

        }
    }
    public void click_to_upimage2(View v)
    {

        Intent intent = new Intent();
        intent.setClass(this, Image2Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);
    }
    public void click_to_wash(View v)
    {
        SharedPreferences mySharedPreferences = getSharedPreferences("user",
                Activity.MODE_PRIVATE);
        String code = mySharedPreferences.getString("code", "0");
        String password= mySharedPreferences.getString("password", "0");
        String orderid= mySharedPreferences.getString("orderid", "0");
        RequestParams requestParams = new RequestParams();
        requestParams.put("code",code);

        requestParams.put("password", password);
        requestParams.put("orderid", orderid);
        RestClient.get(Constant.startCleanCarByAccount, requestParams, new JsonHttpResponseHandler() {

        });
        myapp1.intialize();
        Intent intent = new Intent();
        intent.setClass(this, WashActivity.class);
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
