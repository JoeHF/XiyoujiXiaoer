package com.xiaoer.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2015/5/24.
 */
public class Tool {
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
