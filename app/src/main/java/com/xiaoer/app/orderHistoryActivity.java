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

/**
 * Created by houfang on 2015/4/29.
 */
public class  orderHistoryActivity extends Activity {
   String orderid[]=new String[3];
    String type[]=new String[3];
    String itype;
    String price[]=new String[3];
    String loc;
    String siteid[]=new String[3];
    String brand[]=new String[3];
    String versionid[]=new String[3];
    String carid[]=new String[3];
    String waiterid;
    String number[]=new String[3];
    String color[]=new String[3];
    String isitename;
    String sitename[]=new String[3];
    String isiteid;
    String ibrand;
    String iversionid;
    String icarid;
    String inumber;
    String icolor;
    String iprice;
    String count;
    TextView textview;
    String iversion;
    String []version=new String[3];

      int []ordername={R.id.orderid1,R.id.orderid2};
    int   []order={R.id.order1, R.id.order2};
    int []information={R.id.carinformation1,R.id.carinformation2};
    int []sitename1={R.id.siteid1,R.id.siteid2};
    int []type1={R.id.type1,R.id.type2};
    int []pricename={R.id.price1,R.id.price2};
    LinearLayout  linear;
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);
        SharedPreferences mySharedPreferences = getSharedPreferences("user",
                Activity.MODE_PRIVATE);
        waiterid = mySharedPreferences.getString("waiterid", "0");
        count = mySharedPreferences.getString("count", "0");
        textview = (TextView) findViewById(R.id.count);
        count = count + "单";
        textview.setText(count);
        RequestParams requestParams = new RequestParams();
        requestParams.put("waiterid", waiterid);
        RestClient.get(Constant.getOrderListByWaiterid, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                JSONObject jsonObject=new JSONObject();
                //Log.i("http login jsonobject", response.toString());

                try {
                    for (int i = 0; i < response.length() && i < 2; i++) {

                        Log.i("numer", "i1");
                        jsonObject = response.getJSONObject(i);
                        Log.i("numer1", "i1");
                        Log.i("http login1 jsonobject",jsonObject.toString());
                        orderid[i]=jsonObject.getString("orderid");

                        Log.i("a",orderid[i]);
                       sitename[i]=jsonObject.getString("sitename");
                        if(sitename[i].equals(null))
                            sitename[i]="暂无" ;
                        Log.i("a1",orderid[i]);
                        type[i]=jsonObject.getString("type");
                        Log.i("a2",orderid[i]);
                        number[i]=jsonObject.getString("number");
                        Log.i("a3",orderid[i]);
                        brand[i]=jsonObject.getString("brand");
                        Log.i("a4",orderid[i]);
                        version[i]=jsonObject.getString("version");
                        Log.i("a5",orderid[i]);
                        color[i]=jsonObject.getString("color");
                        Log.i("a6",orderid[i]);
                        price[i]=jsonObject.getString("price");
                        Log.i("a7",orderid[i]);
                        linear=(LinearLayout) findViewById(order[i]);
                        linear.setVisibility(View.VISIBLE);

                        Log.i("sitename",sitename[i]);
                        textview=(TextView) findViewById(ordername[i]);
                        textview.setText("No"+orderid[i]);
                        textview=(TextView) findViewById(sitename1[i]);
                        textview.setText(sitename[i]);
                        String inf=number[i]+"   "+brand[i]+version[i]+"  "+color[i];
                        textview=(TextView) findViewById(information[i]);
                        textview.setText(inf);
                        textview=(TextView) findViewById(pricename[i]);
                        textview.setText("费用总额：￥"+price[i]);
                        linear=(LinearLayout)  findViewById(order[i]);
                        textview=(TextView) findViewById(type1[i]);
                        if(type[i].equals("0"))
                            textview.setText("户外清洗");
                        else
                            textview.setText("室内清洗");

                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });






    }
}
