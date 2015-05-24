package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private  String src=null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upimagine5);
        ImageView image1 = (ImageView) findViewById(R.id.picturesrc);

       Bundle b=getIntent().getExtras();
        String info=b.getString("picturesrc");
        Log.i("click", " photo sure1");
        Bitmap bitmap = getLoacalBitmap(info);
        image1.setImageBitmap(bitmap);
        myapp = (Myapp) getApplication();

    }

    public void click_to_image6(View v)
    {
        String a=myapp.getLabel();
        Bundle mBundle=new Bundle();
        mBundle.putString("picturesrc",src);
        Intent intent = new Intent();
        List list=new ArrayList();
        if(a=="0") {
            intent.setClass(this, Image6Activity.class);
            list=myapp.getlist1();
            list.add(src);
            myapp.setlist1(list);
        }
        if(a=="1") {
            intent.setClass(this, photoFinish1Activity.class);
            list=myapp.getlist2();
            list.add(src);
            myapp.setlist1(list);

        }
        intent.putExtras(mBundle);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);

        Log.i("click","push2");

    }
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}