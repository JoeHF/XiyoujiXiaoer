package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xiaoer.app.Constant.Constant;
import com.xiaoer.app.Util.RestClient;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by houfang on 2015/4/29.
 */
public class  SumActivity extends Activity {
   String price;
  String orderid;
    String waiterid;
    String type;
    String income;
    String time;
    String stage;
    int objectnumber=0;
    int typename[]={R.id.type1,R.id.type2,R.id.type3,R.id.type4,R.id.type5};
    int pricename[]={R.id.price1,R.id.price2,R.id.price3,R.id.price4,R.id.price5};
    int recordname[]={R.id.record1,R.id.record2,R.id.record3,R.id.record4,R.id.record5};
    int timename[]={R.id.time1,R.id.time2,R.id.time3,R.id.time4,R.id.time5};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summoney);
        SharedPreferences mySharedPreferences = getSharedPreferences("user",
                Activity.MODE_PRIVATE);
        waiterid = mySharedPreferences.getString("waiterid", "0");
        income = mySharedPreferences.getString("income", "0");
        TextView textview = (TextView) findViewById(R.id.income);
        textview.setText(income);
        RequestParams requestParams = new RequestParams();
        requestParams.put("waiterid", waiterid);
        RestClient.get(Constant.getOrderListByWaiterid, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                JSONObject jsonObject=new JSONObject();
                //Log.i("http login jsonobject", response.toString());

                try {
                    for (int j = 0; j < response.length() && objectnumber < 5; j++) {

                        Log.i("numer", "i1");
                        jsonObject = response.getJSONObject(j);
                        int i=objectnumber;
                        Log.i("http login1 jsonobject", jsonObject.toString());
                        String endtime = jsonObject.getString("endtime");
                        String stage=jsonObject.getString("stage");
                        if (!endtime.equals("0") && !stage.equals("已关闭")) {
                            objectnumber=objectnumber+1;
                            type = jsonObject.getString("type");
                            price = jsonObject.getString("price");
                            LinearLayout linear = (LinearLayout) findViewById(recordname[i]);
                            linear.setVisibility(View.VISIBLE);
                            TextView textview = (TextView) findViewById(typename[i]);
                            if (type.equals("1")) {
                                textview.setText("收到车外清洗费用");
                            } else {
                                textview.setText("收到车内清洗费用");
                            }
                            textview = (TextView) findViewById(pricename[i]);
                            textview.setText(price + "元");
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm     ");
                            Date date= new Date(Long.parseLong(endtime.trim()));


                            endtime = formatter.format(date);
                            textview = (TextView) findViewById(timename[i]);
                            textview.setText(endtime + "");
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }
    public void click_to_back(View v) {
        finish();
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
    }
}
