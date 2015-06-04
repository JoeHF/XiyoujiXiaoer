package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
public class  SumActivity extends Activity {
   String price;
  String orderid;
    String waiterid;
    String type;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summoney);
        SharedPreferences mySharedPreferences = getSharedPreferences("user",
                Activity.MODE_PRIVATE);
        waiterid = mySharedPreferences.getString("waiterid", "0");
        RequestParams requestParams = new RequestParams();
        requestParams.put("waiterid", waiterid);
        RestClient.get(Constant.getOrderListByWaiterid, requestParams, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.i("http login jsonobject", response.toString());
                try {
                    for (int i = 0; i < response.length() && i < 2; i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        orderid = jsonObject.getString("orderid");


                        RequestParams requestParams1 = new RequestParams();
                        requestParams1.put("orderid", orderid);
                        RestClient.get(Constant.getOrderDetail, requestParams1, new JsonHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                        try {

                                            Log.i("http login jsonobject", response.toString());

                                            price = response.getString("price");

                                           type=response.getString("type");

                                            //提交当前数据


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                        );
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
