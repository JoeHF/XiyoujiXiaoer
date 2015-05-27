package com.xiaoer.app;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ImageGalleryDemoActivity extends Activity {
   private Myapp myapp;
    private static int RESULT_LOAD_IMAGE = 1;
   private String src=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upimagine4);

        myapp = (Myapp) getApplication();

    }
public void click_to_picture(View v)
{
    Intent i = new Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

    startActivityForResult(i, RESULT_LOAD_IMAGE);
}
    @Override
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
            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }

    }
    public void click_to_image6(View v)
    {

        String a=myapp.getLabel();

        Intent intent = new Intent();
        List list=new ArrayList();
        if(a.equals("0")) {
            int number=myapp.getNum1();
            number=number+1;
            myapp.setNum1(number);
            intent.setClass(this, Image6Activity.class);
            list=myapp.getlist1();
            list.add(src);
            myapp.setlist1(list);
        }
        if(a.equals("1")) {
            int number=myapp.getNum2();
            number=number+1;
            myapp.setNum2(number);
            intent.setClass(this, photoFinish1Activity.class);
            list=myapp.getlist2();
            list.add(src);
            myapp.setlist2(list);

        }

        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in,
                R.anim.abc_fade_out	);

        Log.i("click", "push2");

    }
}
