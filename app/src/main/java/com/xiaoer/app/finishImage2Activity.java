package com.xiaoer.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

/**
 * Created by houfang on 2015/4/29.
 */
public class finishImage2Activity extends Activity {
    private static int RESULT_LOAD_IMAGE = 1;
    private Myapp myApp;
    String src;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_image2);
        myApp = (Myapp) getApplication();
        myApp.setLabel("1");
        int number=myApp.getNum2();
        TextView image1 = (TextView) findViewById(R.id.number);
        number=4-number;
        String numbertext=String.valueOf(number);
        image1.setText("您还可以上传"+numbertext+"张照片哦");


    }
    public void click_to_photo(View v)
    {
        Intent intent = new Intent();
        intent.setClass(this, Image3Activity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);
    }
    public void click_to_search(View v)
    {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            src=picturePath;
            SharedPreferences mySharedPreferences = getSharedPreferences("user",
                    Activity.MODE_PRIVATE);
            //实例化SharedPreferences.Editor对象（第二步）
            SharedPreferences.Editor editor = mySharedPreferences.edit();
            editor.putString("src",src);
            editor.commit();
            Intent intent = new Intent();
            intent.setClass(this, ImageGalleryDemoActivity.class);
            //   intent.setClass(this, Image4Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.abc_fade_in,
                    R.anim.abc_fade_out	);
        }

    }
    public void click_to_back(View v) {
        finish();
        overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
    }
}
