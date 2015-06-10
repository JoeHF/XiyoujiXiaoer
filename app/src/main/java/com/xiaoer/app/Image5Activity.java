package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
    Bitmap bitmap1;
    String imageid;
    String orderid;
    int mark=-1;
    String waiterid;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upimagine5);
         image1 = (ImageView) findViewById(R.id.picturesrc);

       Bundle b=getIntent().getExtras();
        String info=b.getString("picturesrc");
        src=info;
        Log.i("click", " photo sure1");
        bitmap=Tool.getLoacalBitmap(src);
        bitmap1=Tool.getLoacalBitmap1(src);
        image1.setImageBitmap(bitmap);
        myapp = (Myapp) getApplication();


    }

    public void click_to_image6(View v)
    {
        String imagename=Tool.bitmaptoString(bitmap1);
        RequestParams requestParams = new RequestParams();
        requestParams.put("icon", imagename);


       /*String imagename=Tool.bitmaptoString(bitmap);
        RequestParams requestParams = new RequestParams();
        requestParams.put("icon", imagename);
        */
        SharedPreferences mySharedPreferences = getSharedPreferences("user",
                Activity.MODE_PRIVATE);
      orderid=mySharedPreferences.getString("orderid","0");
       waiterid=mySharedPreferences.getString("waiterid","0");
        image1.setImageBitmap(null);

        Log.i("bitmap","a");
     RestClient.get(Constant.uploadpic, requestParams, new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                try {
                    String imageid = response.getString("imageid");

                Log.i("imageid",imageid);

                    String a=myapp.getLabel();


                    List list=new ArrayList();
                    if(a.equals("0")) {
                        RequestParams requestParams1 = new RequestParams();
                        requestParams1.put("imageid", imageid);
                        requestParams1.put("orderid", orderid);

                        RestClient.post(Constant.waiterUploadImageBeforeWash, requestParams1, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                                Log.i("http login jsonobject", response.toString());
                             mark=0;


                            }
                        });


                    }
                    if(a.equals("1")) {
                        RequestParams requestParams1 = new RequestParams();
                        requestParams1.put("orderid", orderid);
                        requestParams1.put("imageid", imageid);
                        RestClient.post(Constant.waiterUploadImageAfterWash, requestParams1, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                                Log.i("http login jsonobject", response.toString());

                            mark=1;

                            }
                        });


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
                });


        String a=myapp.getLabel();
        if(a.equals("0")) {
            List list=new ArrayList();
            int number=myapp.getNum1();
            number=number+1;
            myapp.setNum1(number);

            list=myapp.getlist1();
            list.add(src);
            myapp.setlist1(list);
            Intent intent = new Intent();
            intent.setClass(this, Image6Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.abc_fade_in,
                    R.anim.abc_fade_out);
        }
        if(a.equals("1")) {
            List list=new ArrayList();
            int number=myapp.getNum2();
            number=number+1;
            myapp.setNum2(number);

            list=myapp.getlist2();
            list.add(src);
            myapp.setlist2(list);
            Intent intent = new Intent();
            intent.setClass(this, photoFinish1Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.abc_fade_in,
                    R.anim.abc_fade_out);

        }






    }
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
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
    public void click_to_back(View v) {
        finish();
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
    }
}