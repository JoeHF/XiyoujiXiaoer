package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

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
public class  orderHistoryActivity extends Activity implements android.view.GestureDetector.OnGestureListener{
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
    int mark=0;
    int objectnumber=0;
    JSONArray response1;
    private GestureDetector gestureDetector;

    private ViewFlipper viewFlipper;

    private Activity mActivity;
      int []ordername={R.id.orderid1};
    int   []order={R.id.order1};
    int []information={R.id.carinformation1};
    int []sitename1={R.id.siteid1};
    int []type1={R.id.type1};
    int []pricename={R.id.price1};
    LinearLayout  linear;
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);
        mActivity = this;
        gestureDetector = new GestureDetector(this);
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
                mark=0;
                //  JSONObject jsonObject=new JSONObject();
                //Log.i("http login jsonobject", response.toString());

                response1=response;
                objectnumber=response.length();

                int flag=0;
                int a=0;
                while(flag == 0 && a<objectnumber) {
                    mark = (mark + 1) % objectnumber;
                    try {
                        JSONObject jsonObject = response1.getJSONObject(mark);
                        Log.i("http login1 jsonobject", jsonObject.toString());
                        int i = 0;
                        String endtime = jsonObject.getString("endtime");
                        String stage = jsonObject.getString("stage");
                        if (!endtime.equals("0") & !stage.equals("已关闭")) {
                            orderid[i] = jsonObject.getString("orderid");

                            Log.i("a", orderid[i]);
                            sitename[i] = jsonObject.getString("sitename");
                            if (sitename[i].equals(null))
                                sitename[i] = "暂无";
                            Log.i("a1", orderid[i]);
                            type[i] = jsonObject.getString("type");
                            Log.i("a2", orderid[i]);
                            number[i] = jsonObject.getString("number");
                            Log.i("a3", orderid[i]);
                            brand[i] = jsonObject.getString("brand");
                            Log.i("a4", orderid[i]);
                            version[i] = jsonObject.getString("version");
                            Log.i("a5", orderid[i]);
                            color[i] = jsonObject.getString("color");
                            Log.i("a6", orderid[i]);
                            price[i] = jsonObject.getString("price");
                            Log.i("a7", orderid[i]);
                            linear = (LinearLayout) findViewById(order[i]);
                            linear.setVisibility(View.VISIBLE);

                            Log.i("sitename", sitename[i]);
                            textview = (TextView) findViewById(ordername[i]);
                            textview.setText("No" + orderid[i]);
                            textview = (TextView) findViewById(sitename1[i]);
                            textview.setText(sitename[i]);
                            String inf = number[i] + "   " + brand[i] + version[i] + "  " + color[i];
                            textview = (TextView) findViewById(information[i]);
                            textview.setText(inf);
                            textview = (TextView) findViewById(pricename[i]);
                            textview.setText("费用总额：￥" + price[i]);
                            linear = (LinearLayout) findViewById(order[i]);
                            textview = (TextView) findViewById(type1[i]);
                            if (type[i].equals("1"))
                                textview.setText("车外清洗");
                            else
                                textview.setText("车内清洗");
                            flag = 1;
                        }  else {
                            a=a+1;
                            flag = 0;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                }
                });


                    //  JSONObject jsonObject=new JSONObject();
                //Log.i("http login jsonobject", response.toString());

              /*  try {
                 /*   for (int i = 0; i < response.length() && i < 2; i++) {

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
            */








    }
    public boolean onTouchEvent(MotionEvent event) {

        // TODO Auto-generated method stub



        return gestureDetector.onTouchEvent(event); // 注册手势事件

    }
    public boolean onDown(MotionEvent arg0) {

        // TODO Auto-generated method stub

        return false;

    }



    @Override

    public boolean onFling(MotionEvent e1, MotionEvent e2, float arg2,

                           float arg3) {

        if (e2.getX() - e1.getX() > 120) { // 从左向右滑动（左进右出）

            Animation rInAnim = AnimationUtils.loadAnimation(mActivity,

                    R.anim.push_right_in); // 向右滑动左侧进入的渐变效果（alpha 0.1 -> 1.0）

            Animation rOutAnim = AnimationUtils.loadAnimation(mActivity,

                    R.anim.push_right_out); // 向右滑动右侧滑出的渐变效果（alpha 1.0 -> 0.1）
            int flag=0;
            int a=0;
            while(flag == 0 && a<objectnumber) {
                mark = (mark + 1) % objectnumber;
                try {
                    JSONObject jsonObject = response1.getJSONObject(mark);
                    Log.i("http login1 jsonobject", jsonObject.toString());
                    int i = 0;
                    String endtime = jsonObject.getString("endtime");
                    String stage=jsonObject.getString("stage");
                    if (!endtime.equals("0") & !stage.equals("已关闭") ) {
                        orderid[i] = jsonObject.getString("orderid");

                        Log.i("a", orderid[i]);
                        sitename[i] = jsonObject.getString("sitename");
                        if (sitename[i].equals(null))
                            sitename[i] = "暂无";
                        Log.i("a1", orderid[i]);
                        type[i] = jsonObject.getString("type");
                        Log.i("a2", orderid[i]);
                        number[i] = jsonObject.getString("number");
                        Log.i("a3", orderid[i]);
                        brand[i] = jsonObject.getString("brand");
                        Log.i("a4", orderid[i]);
                        version[i] = jsonObject.getString("version");
                        Log.i("a5", orderid[i]);
                        color[i] = jsonObject.getString("color");
                        Log.i("a6", orderid[i]);
                        price[i] = jsonObject.getString("price");
                        Log.i("a7", orderid[i]);
                        linear = (LinearLayout) findViewById(order[i]);
                        linear.setVisibility(View.VISIBLE);

                        Log.i("sitename", sitename[i]);
                        textview = (TextView) findViewById(ordername[i]);
                        textview.setText("No" + orderid[i]);
                        textview = (TextView) findViewById(sitename1[i]);
                        textview.setText(sitename[i]);
                        String inf = number[i] + "   " + brand[i] + version[i] + "  " + color[i];
                        textview = (TextView) findViewById(information[i]);
                        textview.setText(inf);
                        textview = (TextView) findViewById(pricename[i]);
                        textview.setText("费用总额：￥" + price[i]);
                        linear = (LinearLayout) findViewById(order[i]);
                        textview = (TextView) findViewById(type1[i]);
                        if (type[i].equals("1"))
                            textview.setText("车外清洗");
                        else
                            textview.setText("车内清洗");
                        flag = 1;
                    }
                    else {
                        a=a+1;
                        flag = 0;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


            return true;

        } else if (e2.getX() - e1.getX() < -120) { // 从右向左滑动（右进左出）

            Animation lInAnim = AnimationUtils.loadAnimation(mActivity,

                    R.anim.push_left_in); // 向左滑动左侧进入的渐变效果（alpha 0.1 -> 1.0）

            Animation lOutAnim = AnimationUtils.loadAnimation(mActivity,

                    R.anim.push_left_out); // 向左滑动右侧滑出的渐变效果（alpha 1.0 -> 0.1）
                int flag=0;
            int a=0;
            while(flag == 0 && a<objectnumber) {

                mark = (mark + 1) % objectnumber;
                try {
                    JSONObject jsonObject = response1.getJSONObject(mark);
                    Log.i("http login1 jsonobject", jsonObject.toString());
                    int i = 0;
                    String endtime = jsonObject.getString("endtime");
                    String stage=jsonObject.getString("stage");
                    if (!endtime.equals("0") && !stage.equals("已关闭")) {
                        orderid[i] = jsonObject.getString("orderid");

                        Log.i("a", orderid[i]);
                        sitename[i] = jsonObject.getString("sitename");
                        if (sitename[i].equals(null))
                            sitename[i] = "暂无";
                        Log.i("a1", orderid[i]);
                        type[i] = jsonObject.getString("type");
                        Log.i("a2", orderid[i]);
                        number[i] = jsonObject.getString("number");
                        Log.i("a3", orderid[i]);
                        brand[i] = jsonObject.getString("brand");
                        Log.i("a4", orderid[i]);
                        version[i] = jsonObject.getString("version");
                        Log.i("a5", orderid[i]);
                        color[i] = jsonObject.getString("color");
                        Log.i("a6", orderid[i]);
                        price[i] = jsonObject.getString("price");
                        Log.i("a7", orderid[i]);
                        linear = (LinearLayout) findViewById(order[i]);
                        linear.setVisibility(View.VISIBLE);

                        Log.i("sitename", sitename[i]);
                        textview = (TextView) findViewById(ordername[i]);
                        textview.setText("No" + orderid[i]);
                        textview = (TextView) findViewById(sitename1[i]);
                        textview.setText(sitename[i]);
                        String inf = number[i] + "   " + brand[i] + version[i] + "  " + color[i];
                        textview = (TextView) findViewById(information[i]);
                        textview.setText(inf);
                        textview = (TextView) findViewById(pricename[i]);
                        textview.setText("费用总额：￥" + price[i]);
                        linear = (LinearLayout) findViewById(order[i]);
                        textview = (TextView) findViewById(type1[i]);
                        if (type[i].equals("1"))
                            textview.setText("车外清洗");
                        else
                            textview.setText("车内清洗");
                        flag=1;
                    }
                    else {
                        a=a+1;
                        flag = 0;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }

        return true;

    }



    @Override

    public void onLongPress(MotionEvent arg0) {

        // TODO Auto-generated method stub



    }



    @Override

    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,

                            float arg3) {

        // TODO Auto-generated method stub

        return false;

    }



    @Override

    public void onShowPress(MotionEvent arg0) {

        // TODO Auto-generated method stub



    }



    @Override

    public boolean onSingleTapUp(MotionEvent arg0) {

        // TODO Auto-generated method stub

        return false;

    }
   public void click_to_back(View v) {
        finish();
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
    }

}
