package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import    java.text.SimpleDateFormat;
import java.util.Date;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xiaoer.app.Constant.Constant;
import com.xiaoer.app.Util.RestClient;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by houfang on 2015/4/29.
 */
public class HomepageActivity extends Activity {
    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    TextView textview;
    String time;

    String type;
    String price;
    String siteid;
    String waiterid;
    String latitude=null;
    String longitude=null;
    MapView mMapView;
    BaiduMap mBaiduMap;
    LinearLayout lay1;
    LinearLayout lay2;
    String orderid;
    boolean isFirstLoc = true;// 是否首次定位

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.rab_order);
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日    HH:mm     ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);


        RequestParams requestParams = new RequestParams();
        Log.i("lat", "latitude");
        latitude = "2";
        longitude = "1";
        SharedPreferences mySharedPreferences = getSharedPreferences("user",
                Activity.MODE_PRIVATE);
        waiterid = mySharedPreferences.getString("waiterid", "0");


        requestParams.put("waiterid", waiterid);
        requestParams.put("long", longitude);
        requestParams.put("lat", latitude);
        Log.i("lat", "latitude");

        RestClient.post(Constant.UpdateLocation, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String status = response.getString("state");
                    Log.i("click", status);
                    Log.i("name", waiterid);
                    if (status.equals("success")) {
                        RequestParams requestParams1 = new RequestParams();
                        requestParams1.put("waiterid", waiterid);
                        Log.i("waiterid", waiterid);
                        RestClient.post(Constant.getOrder, requestParams1, new JsonHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                        Log.i("waiterid", waiterid);
                                        Log.i("http login jsonobject", response.toString());
                                        try {
                                            orderid = response.getString("orderid");
                                            RequestParams requestParams2 = new RequestParams();
                                            requestParams2.put("orderid", orderid);
                                            RestClient.get(Constant.getOrderDetail, requestParams2, new JsonHttpResponseHandler() {
                                                        @Override
                                                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                                            try {

                                                                Log.i("http login jsonobject", response.toString());

                                                                time = response.getString("createtime");
                                                                price = response.getString("price");
                                                                siteid = response.getString("siteid");
                                                                type = response.getString("type");
                                                                SharedPreferences mySharedPreferences = getSharedPreferences("user",
                                                                        Activity.MODE_PRIVATE);
                                                                SharedPreferences.Editor editor = mySharedPreferences.edit();
                                                                //用putString的方法保存数据
                                                                editor.putString("time", time);
                                                                editor.putString("price", price);
                                                                editor.putString("siteid", siteid);
                                                                editor.putString("type", type);
                                                                editor.commit();
                                                                siteid = "距离您" + siteid + "公里";
                                                                textview = (TextView) findViewById(R.id.siteid);
                                                                textview.setText(siteid);

                                                                textview = (TextView) findViewById(R.id.type);
                                                                if(type.equals("0"))
                                                                {
                                                                    textview.setText("户外清洗");
                                                                }
                                                                else
                                                                {
                                                                    textview.setText("室内清洗");
                                                                }
                                                                textview = (TextView) findViewById(R.id.price);
                                                                textview.setText(price);
                                                                SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日    HH:mm     ");

                                                               // time = formatter.format(time);
                                                                Log.i("time", time);
                                                                textview = (TextView) findViewById(R.id.time);
                                                                textview.setText(time);

                                                                //提交当前数据


                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }

                                                    }
                                            );
                                            //实例化SharedPreferences对象（第一步）
                                            SharedPreferences mySharedPreferences = getSharedPreferences("user",
                                                    Activity.MODE_PRIVATE);
                                            //实例化SharedPreferences.Editor对象（第二步）
                                            SharedPreferences.Editor editor = mySharedPreferences.edit();
                                            //用putString的方法保存数据

                                            //提交当前数据
                                            editor.commit();


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

        // time = formatter.format(time);


        mCurrentMode = LocationMode.NORMAL;

        // 地图初始化
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

     public void click_to_success(View v) {
         Intent intent = new Intent();
         intent.setClass(this, SuccessActivity.class);
         startActivity(intent);
         overridePendingTransition(R.anim.abc_fade_in,
                 R.anim.abc_fade_out);

     }

     public void click_to_myorder(View v) {
         Intent intent = new Intent();
         intent.setClass(this, MyOrderActivity.class);
         startActivity(intent);
         overridePendingTransition(R.anim.abc_fade_in,
                 R.anim.abc_fade_out);

     }

     public void click_to_showme(View v) {
         Intent intent = new Intent();
         intent.setClass(this, showmeActivity.class);
         startActivity(intent);
         overridePendingTransition(R.anim.abc_fade_in,
                 R.anim.abc_fade_out);
     }

     /**
      * 定位SDK监听函数
      */
     public class MyLocationListenner implements BDLocationListener {

         @Override
         public void onReceiveLocation(BDLocation location) {
             // map view 销毁后不在处理新接收的位置
             if (location == null || mMapView == null)
                 return;
             MyLocationData locData = new MyLocationData.Builder()
                     .accuracy(location.getRadius())
                             // 此处设置开发者获取到的方向信息，顺时针0-360
                     .direction(100).latitude(location.getLatitude())
                     .longitude(location.getLongitude()).build();
            // latitude = String.valueOf(location.getLatitude());
             // longitude = String.valueOf(location.getLongitude());
             mBaiduMap.setMyLocationData(locData);
             if (isFirstLoc) {
                 isFirstLoc = false;
                 LatLng ll = new LatLng(location.getLatitude(),
                         location.getLongitude());
                 MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                 mBaiduMap.animateMapStatus(u);
             }
         }

         public void onReceivePoi(BDLocation poiLocation) {
         }
     }

     public void click_to_contract(View v) {
         Log.i("click", "i");
         lay2 = (LinearLayout) findViewById(R.id.goout);
         lay2.setVisibility(View.VISIBLE);
         lay1 = (LinearLayout) findViewById(R.id.show);
         lay1.setVisibility(View.INVISIBLE);


     }

     public void click_to_showorder(View v) {
         lay1 = (LinearLayout) findViewById(R.id.show);
         lay1.setVisibility(View.VISIBLE);
         lay2 = (LinearLayout) findViewById(R.id.goout);
         lay2.setVisibility(View.INVISIBLE);


     }

     @Override
     protected void onPause() {
         mMapView.onPause();
         super.onPause();
     }

     @Override
     protected void onResume() {
         mMapView.onResume();
         super.onResume();
     }

     @Override
     protected void onDestroy() {
         // 退出时销毁定位
         mLocClient.stop();
         // 关闭定位图层
         mBaiduMap.setMyLocationEnabled(false);
         mMapView.onDestroy();
         mMapView = null;
         super.onDestroy();
     }


 }
