package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xiaoer.app.Constant.Constant;
import com.xiaoer.app.Util.RestClient;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**import java.io.UnsupportedEncodingException;
 * Created by houfang on 2015/4/29.
 */
public class Image5Activity extends Activity {
    private Myapp myapp;
    private View etName;
     ImageView image1;
    private  String src=null;
    Bitmap bitmap;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upimagine5);
         image1 = (ImageView) findViewById(R.id.picturesrc);

       Bundle b=getIntent().getExtras();
        String info=b.getString("picturesrc");
        src=info;
        Log.i("click", " photo sure1");
        bitmap=BitmapFactory.decodeFile(info);
        image1.setImageBitmap(bitmap);
        myapp = (Myapp) getApplication();

    }

    public void click_to_image6(View v)
    {
       String imagename=Tool.bitmaptoString(bitmap);
        RequestParams requestParams = new RequestParams();
        requestParams.put("icon", imagename);
        RestClient.post(Constant.uploadpic, requestParams, new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                try {
                    String iamgeid = response.getString("imageid");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
                });
         image1.setImageBitmap(null);
        if(!bitmap.isRecycled())
        {
            bitmap.recycle();

        }
        bitmap=null;
        String a=myapp.getLabel();

        Intent intent = new Intent();
        List list=new ArrayList();
        if(a.equals("0")) {
            int number=myapp.getNum1();
            number=number+1;
            myapp.setNum1(number);
            intent.setClass(this, Image6Activity.class);
            list=myapp.getlist1();
            list.add(src);
            myapp.setlist1(list);
        }
        if(a.equals("1")) {
            int number=myapp.getNum2();
            number=number+1;
            myapp.setNum2(number);
            intent.setClass(this, photoFinish1Activity.class);
            list=myapp.getlist2();
            list.add(src);
            myapp.setlist2(list);

        }

        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);

        Log.i("click","push2");

    }
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void  onDestroy()
    {

        super.onDestroy();


    }

}