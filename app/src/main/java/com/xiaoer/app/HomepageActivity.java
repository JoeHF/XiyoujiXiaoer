package com.xiaoer.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import    java.text.SimpleDateFormat;
import java.util.Date;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

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
    String status;
    String type;
    String price;
    String siteid;
    String waiterid;
    String latitude="0";
    String longitude="0";
    MapView mMapView;
    BaiduMap mBaiduMap;
    LinearLayout lay1;
    LinearLayout lay2;
    String orderid;
    boolean isFirstLoc = true;// 是否首次定位

    boolean flag=false;
    String long1;
    String lat1;
    private static final double EARTH_RADIUS = 6378137;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.rab_order);
    //SimpleDateFormat formatter = new SimpleDateFormat("YY年MM月dd日    HH:mm：ss     ");
      //获取当前时间
        isFirstLoc = true;
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










        // time = formatter.format(time);




        // 地图初始化

    }

     public void click_to_success(View v) {
         RequestParams requestParams1 = new RequestParams();
         requestParams1.put("waiterid", waiterid);
         requestParams1.put("orderid",orderid);
         RestClient.get(Constant.acceptOrderByWaiterid, requestParams1, new JsonHttpResponseHandler()
         {

                 });
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

             mBaiduMap.setMyLocationData(locData);
             if (isFirstLoc) {
                 latitude = String.valueOf(location.getLatitude());
                 longitude = String.valueOf(location.getLongitude());
               SharedPreferences mySharedPreferences = getSharedPreferences("user",
                         Activity.MODE_PRIVATE);
                  waiterid = mySharedPreferences.getString("waiterid", "0");
        long1=mySharedPreferences.getString("longitude", "0");
        lat1=mySharedPreferences.getString("latitude", "0");
             RequestParams requestParams = new RequestParams();
                 requestParams.put("waiterid", waiterid);

                 requestParams.put("long", longitude);
                 requestParams.put("lat", latitude);
                 Log.i("lat", latitude);

                 RestClient.post(Constant.UpdateLocation, requestParams, new JsonHttpResponseHandler() {
                     @Override
                     public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                         try {
                             status = response.getString("state");
                             Log.i("click", status);
                             Log.i("name", waiterid);
                             Log.i("http login jsonobject", response.toString());

                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                         if (status.equals("success")) {
                             RequestParams requestParams1 = new RequestParams();
                             requestParams1.put("waiterid", waiterid);
                             Log.i("waiterid", waiterid);
                             RestClient.get(Constant.getOrder, requestParams1, new JsonHttpResponseHandler() {
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
                                                                     String sitename = response.getString("sitename");
                                                                     String brand = response.getString("brand");
                                                                     String version = response.getString("version");
                                                                     String number = response.getString("number");
                                                                     String color = response.getString("color");
                                                                     type = response.getString("type");
                                                                    long1=response.getString("long");
                                                                     lat1=response.getString("lat");
                                                                     SharedPreferences mySharedPreferences = getSharedPreferences("user",
                                                                             Activity.MODE_PRIVATE);
                                                                     SharedPreferences.Editor editor = mySharedPreferences.edit();
                                                                     //用putString的方法保存数据

                                                                     String inf = number + "   " + brand + version + "  " + color;
                                                                     editor.putString("inf", inf);
                                                                     editor.putString("sitename", sitename);
                                                                     editor.putString("orderid",orderid);
                                                                     editor.putString("type",type);
                                                                     editor.putString("price",price);
                                                                     editor.commit();
                                                                     double dis = GetDistance(long1, lat1, longitude, latitude);
                                                                     dis=(double)  Math.round(dis*100/100.0);
                                                                     dis=dis/1000;
                                                                     int mark=0;

                                                                     if(dis<1000)
                                                                         mark=0;
                                                                         if(dis>=1000 && dis<1000*1000)
                                                                         {
                                                                             dis=dis/1000;
                                                                             mark=1;
                                                                         }
                                                                     if(dis>=1000*1000)
                                                                     {
                                                                         dis=dis/(1000*1000);
                                                                         mark=2;
                                                                     }
                                                                     String dis1;
                                                                     if(dis<=1)
                                                                    dis1=String.format("%.1f",dis);
                                                                       else
                                                                     dis1=String.format("%.0f",dis);
                                                                     Log.i("dis1",dis1);
                                                                     if(mark==1)
                                                                         dis1=dis1+"k";
                                                                     if(mark==2)
                                                                         dis1=dis1+"m";
                                                                     siteid = "距离您" + String.valueOf(dis1) + "公里";
                                                                     textview = (TextView) findViewById(R.id.siteid);
                                                                     textview.setText(siteid);

                                                                     textview = (TextView) findViewById(R.id.type);
                                                                     if (type.equals("2")) {
                                                                         textview.setText("车内清洗");
                                                                     } else {
                                                                         textview.setText("车外清洗");
                                                                     }
                                                                     textview = (TextView) findViewById(R.id.price);
                                                                     textview.setText(price);
                                                                        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日    HH:mm     ");
                                                                     Date date= new Date(Long.parseLong(time.trim()));


                                                                                time = formatter.format(date);



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

                                                 //用putString的方法保存数据

                                                 //提交当前数据


                                             } catch (JSONException e) {
                                                 e.printStackTrace();
                                             }
                                         }
                                     }
                             );
                         }
                     }

                 });


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
    public static double GetDistance(String nlng1, String nlat1, String nlng2, String nlat2)
       {
            double   lat1=Double.valueOf(nlat1);
           double   lat2=Double.valueOf(nlat2);
           double   lng1=Double.valueOf(nlng1);
           double   lng2=Double.valueOf(nlng2);
              double radLat1 = rad(lat1);
             double radLat2 = rad(lat2);
             double a = radLat1 - radLat2;
              double b = rad(lng1) - rad(lng2);
            double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +   Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
             s = s * EARTH_RADIUS;
              s = Math.round(s * 10000) / 10000;
             return s;
          }
    private static double rad(double d)
        {
             return d * Math.PI / 180.0;
            }


 }
