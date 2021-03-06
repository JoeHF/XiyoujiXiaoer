package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.io.IOException;
import java.net.URL;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.xiaoer.app.Constant.Constant;
import com.xiaoer.app.Util.RestClient;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
public class  OwnerphotoActivity extends Activity implements android.view.GestureDetector.OnGestureListener {
    private int[] imgs = { R.drawable.mycar, R.drawable.messi, R.drawable.mycar,

            R.drawable.messi };
    ImageView image1;
    private GestureDetector gestureDetector;

    private ViewFlipper viewFlipper;

    private Activity mActivity;
    LocationClient mLocClient;
   // public MyLocationListenner myListener = new MyLocationListenner();
    private LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;

    MapView mMapView;
    BaiduMap mBaiduMap;

    boolean isFirstLoc = true;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
     ///  SDKInitializer.initialize(getApplicationContext());
        Log.i("click","a" );
        setContentView(R.layout.photo_success);
      String src="http://api.map.baidu.com/staticimage?width=400&height=400&zoom=10";
      image1=(ImageView) findViewById(R.id.image);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(src,new  AsyncHttpResponseHandler()
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
       //Bitmap bitmap=Tool.getNetBitmap(src);
      /*    image1.setImageBitmap(bitmap);
   /*     Drawable drawable = loadImageFromNetwork(src);
        image1.setImageDrawable(drawable) ;
*/
        mActivity = this;

        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);



        gestureDetector = new GestureDetector(this);

        Log.i("click","a" );

        for (int i = 0; i < imgs.length; i++) { // 添加图片源

            ImageView iv = new ImageView(this);

            iv.setImageResource(imgs[i]);
            Log.i("click","a" );
            iv.setScaleType(ImageView.ScaleType.FIT_XY);

            viewFlipper.addView(iv, new LayoutParams(LayoutParams.FILL_PARENT,

                    LayoutParams.FILL_PARENT));

        }



        viewFlipper.setAutoStart(true); // 设置自动播放功能（点击事件，前自动播放）

        viewFlipper.setFlipInterval(3000);

        if (viewFlipper.isAutoStart() && !viewFlipper.isFlipping()) {

            viewFlipper.startFlipping();

        }
      //  mCurrentMode = LocationMode.NORMAL;

        // 地图初始化
    /* mMapView = (MapView) findViewById(R.id.bmapView);
        mMapView.setEnabled(false);
          mMapView.setFocusable(false);
        mMapView.onWindowFocusChanged(true);

        mMapView.setFocusableInTouchMode(false);

      mMapView.setOnTouchListener(new View.OnTouchListener(){public boolean onTouch(View v, MotionEvent event){if(event.getPointerCount() > 1) { return true; } return false;} });
      mBaiduMap=mMapView.getMap();
     */
      /*  mBaiduMap = mMapView.getMap();
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
        */

    }
    /*
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
    */
    public boolean onTouchEvent(MotionEvent event) {

        // TODO Auto-generated method stub

        viewFlipper.stopFlipping(); // 点击事件后，停止自动播放

        viewFlipper.setAutoStart(false);

        return gestureDetector.onTouchEvent(event); // 注册手势事件

    }



    @Override

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



            viewFlipper.setInAnimation(rInAnim);

            viewFlipper.setOutAnimation(rOutAnim);

            viewFlipper.showPrevious();

            return true;

        } else if (e2.getX() - e1.getX() < -120) { // 从右向左滑动（右进左出）

            Animation lInAnim = AnimationUtils.loadAnimation(mActivity,

                    R.anim.push_left_in); // 向左滑动左侧进入的渐变效果（alpha 0.1 -> 1.0）

            Animation lOutAnim = AnimationUtils.loadAnimation(mActivity,

                    R.anim.push_left_out); // 向左滑动右侧滑出的渐变效果（alpha 1.0 -> 0.1）



            viewFlipper.setInAnimation(lInAnim);

            viewFlipper.setOutAnimation(lOutAnim);

            viewFlipper.showNext();

            return true;

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

    /*
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
    */
    public void click_to_success(View v)
    {
        Intent intent = new Intent();
        intent.setClass(this, SuccessActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);
    }
    private Drawable loadImageFromNetwork(String imageUrl)
    {
        Drawable drawable = null;
        try {
            // 可以在这里通过文件名来判断，是否本地有此图片
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), "image.jpg");
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
        if (drawable == null) {
            Log.d("test", "null drawable");
        } else {
            Log.d("test", "not null drawable");
        }

        return drawable ;
    }
}