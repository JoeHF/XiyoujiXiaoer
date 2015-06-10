package com.xiaoer.app;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xiaoer.app.Constant.Constant;
import com.xiaoer.app.Util.RestClient;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ImageGalleryDemoActivity extends Activity {
   private Myapp myapp;
    private static int RESULT_LOAD_IMAGE = 1;
   private String src=null;
   ImageView imageView;
  Bitmap bitmap;
    Bitmap bitmap1;

    String imageid;
    String orderid;
    int mark=-1;
    String waiterid;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upimagine4);

        myapp = (Myapp) getApplication();


    }
public void click_to_picture(View v)
{
  /*  Intent i = new Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

    startActivityForResult(i, RESULT_LOAD_IMAGE);
    */
    SharedPreferences mySharedPreferences = getSharedPreferences("user",
            Activity.MODE_PRIVATE);
  src=mySharedPreferences.getString("src","0");
    imageView = (ImageView) findViewById(R.id.imgView);
    bitmap=Tool.getLoacalBitmap(src);
    imageView.setImageBitmap(bitmap);
    bitmap1=Tool.getLoacalBitmap1(src);
    TextView text=(TextView)  findViewById(R.id.finish);
    text.setBackgroundColor(this.getResources().getColor(R.color.main_theme_tab_color));

    text.setClickable(true);
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            src=picturePath;
             imageView = (ImageView) findViewById(R.id.imgView);
            bitmap=Tool.getLoacalBitmap(src);
            bitmap1=Tool.getLoacalBitmap1(src);
            imageView.setImageBitmap(bitmap);

        }

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
        imageView.setImageBitmap(null);

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

                    RequestParams requestParams2 = new RequestParams();
                    requestParams2.put("imageid", imageid);
                    requestParams2.put("waiterid", waiterid);

                    RestClient.post(Constant.userUploadIcon, requestParams2, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                            Log.i("http login jsonobjectme", response.toString());



                        }
                    });
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
            Log.i("src",src);
            myapp.setlist1(list);
            Intent intent = new Intent();
            intent.setClass(this, Image6Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.abc_fade_in,
                    R.anim.abc_fade_out);
        }
        if(a.equals("1")) {
            int number=myapp.getNum2();
            number=number+1;
            myapp.setNum2(number);


            List list=new ArrayList();
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
    public void click_to_back(View v) {
        finish();
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
    }
}
