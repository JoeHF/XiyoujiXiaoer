package com.xiaoer.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Base64;
import android.util.Log;

/**
 * Created by Administrator on 2015/5/24.
 */
public class Tool {
    public static Bitmap getLoacalBitmap(String url) {

        try {
            FileInputStream fis = new FileInputStream(url);
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inJustDecodeBounds = true;
            Bitmap bm = BitmapFactory.decodeFile(url, opt);
            opt.inSampleSize =10;
            opt.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(url,opt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

           return null;
    }
    public static Bitmap getLoacalBitmap1(String url) {

        //  FileInputStream fis = new FileInputStream(url);
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        Bitmap bm = BitmapFactory.decodeFile(url, opt);
        opt.inSampleSize =100;
        opt.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(url,opt);

    }
    public static String bitmaptoString(Bitmap bitmap) {

        // 将Bitmap转换成字符串
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }
public static Bitmap   getNetBitmap(String url)
{
   BitmapFactory.Options opt = new BitmapFactory.Options();
    opt.inJustDecodeBounds = true;

    Bitmap bitmap=null;
    HttpURLConnection conn = null;
    try {
        Log.i("1", "1");
        conn = (HttpURLConnection) new URL(url).openConnection();

        conn.setConnectTimeout(5 * 1000);
        conn.setDoInput(true);
        Log.i("1", "1");
      conn.connect();
    } catch (IOException e) {
        e.printStackTrace();
    }
    try {




        InputStream is= conn.getInputStream();
        Log.i("1", "1");
        Bitmap bit=BitmapFactory.decodeStream(is,null,opt);
        Log.i("1", "1");
        opt.inSampleSize =10;

        opt.inJustDecodeBounds = false;
        bitmap =BitmapFactory.decodeStream(is,null,opt);
        Log.i("1", "1");
        is.close();
    } catch (IOException e) {
        e.printStackTrace();
    }

    return bitmap;
}
}
