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
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xiaoer.app.Constant.Constant;
import com.xiaoer.app.Util.RestClient;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by houfang on 2015/4/29.
 */
public class  showmeActivity extends Activity {
    String waiterid;
    String star;
    String income;
    String count;
    TextView textview;
    String imageid;
    int flag=0;
    ImageView image1;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showme);
        SharedPreferences mySharedPreferences= getSharedPreferences("user",
                Activity.MODE_PRIVATE);
        waiterid=mySharedPreferences.getString("waiterid","0");
        star=mySharedPreferences.getString("star","0");
        income=mySharedPreferences.getString("income","0");
       count=mySharedPreferences.getString("count","0");
        RequestParams requestParams1 = new RequestParams();
        requestParams1.put("waiterid", waiterid);

        RestClient.get(Constant.getUserIcon, requestParams1, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response)
            {

                    int a=response.length();
                try {
                    JSONObject jsonObject=response.getJSONObject(a-1);
                    imageid=jsonObject.getString("name");
                    String url="http://121.40.130.54/xiyouji/upload/"+imageid+".jpg";
                   Log.i("a",url);
                    image1=(ImageView) findViewById(R.id.head_photo);
                    AsyncHttpClient client = new AsyncHttpClient();
                    client.get(url,new  AsyncHttpResponseHandler()
                    {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (statusCode == 200) {
                                //创建工厂对象
                                BitmapFactory bitmapFactory = new BitmapFactory();
                                //工厂对象的decodeByteArray把字节转换成Bitmap对象
                                Bitmap bitmap = bitmapFactory.decodeByteArray(responseBody, 0, responseBody.length);
                                //设置图片
                                image1.setImageBitmap(bitmap);
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers,
                                              byte[] responseBody, Throwable error) {
                            error.printStackTrace();
                        }
                    });
              //      Tool.getNetBitmap(url);

           //      image1.setImageBitmap(Tool.getNetBitmap(url));

                } catch (JSONException e) {
                    e.printStackTrace();

                }
              /*  try {
                    Log.i("myimage","a");
                    imageid=response.getString("imageid");
                    Log.i("imageid2",imageid);
                    String url="http://121.40.130.54/xiyouji/upload/"+imageid;
                    ImageView image1=(ImageView) findViewById(R.id.head_photo);
                    image1.setImageBitmap(Tool.getLoacalBitmap(url));



              /*  } catch (JSONException e) {
                    e.printStackTrace();
                }
            */

            }
        });


       //实例化SharedPreferences.Editor对象（第二步）
        textview=(TextView)  findViewById(R.id.waiter);
        waiterid="小二"+waiterid;
        textview.setText(waiterid);
        textview=(TextView)  findViewById(R.id.count);

        textview.setText(count);
        textview=(TextView)  findViewById(R.id.star);

        textview.setText(star);
        textview=(TextView)  findViewById(R.id.income);

        textview.setText(income);

    }
    public void click_to_history(View v) {
        Intent intent = new Intent();
        intent.setClass(this, orderHistoryActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);

    }
    public void click_to_money(View v) {
        Intent intent = new Intent();
        intent.setClass(this, SumActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);

    }
    public void click_to_out(View v) {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
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