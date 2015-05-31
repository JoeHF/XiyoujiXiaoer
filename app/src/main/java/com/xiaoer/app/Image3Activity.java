package com.xiaoer.app;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;



import android.net.Uri;

import android.os.Bundle;

import android.os.Environment;

import android.provider.MediaStore;

import android.app.Activity;

import android.content.Context;

import android.content.Intent;

import android.hardware.Camera;

import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PictureCallback;
import android.util.Log;

import android.view.Menu;

import android.view.Surface;

import android.view.SurfaceHolder;

import android.view.SurfaceView;

import android.view.View;

import android.widget.Button;

import android.widget.FrameLayout;

import android.widget.TextView;
import android.widget.Toast;



        public class Image3Activity extends Activity {

        public static final int MEDIA_TYPE_IMAGE = 1;

        public static final int MEDIA_TYPE_VIDEO = 2;

        protected static final String  TAG = null;
            public static String picturesrc=null;

private Camera mCamera;

private CameraPreview mPreview;




@Override

public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.upimagine3);



// Create an instance ofCamera

        mCamera = getCameraInstance();



// Create our Preview viewand set it as the content of our activity.

        mPreview = new CameraPreview(this, mCamera);

        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);

        preview.addView(mPreview);

        Button captureButton = (Button) findViewById(R.id.button_capture);

        captureButton.setOnClickListener(new View.OnClickListener() {

@Override

public void onClick(View v) {

// get an imagefrom the camera

        mCamera.takePicture(null, null, mPicture);
       TextView text=(TextView)  findViewById(R.id.finish);



     text.setBackgroundColor(R.color.main_theme_tab_color);
     text.setClickable(true);


        }

        });



        }



/** A safe way to get an instance of theCamera object. */

        public static Camera getCameraInstance() {

        Camera c = null;

        try {

        c = Camera.open(); // attempt to get a Camera instance

        } catch (Exception e) {

// Camera is notavailable (in use or does not exist)

        }

        return c; // returns nullif camera is unavailable

        }



/** A basic Camera preview class */

        public class CameraPreview extends SurfaceView implements

        SurfaceHolder.Callback {

private SurfaceHolder mHolder;

private Camera mCamera;



public CameraPreview(Context context, Camera camera) {

        super(context);

        mCamera = camera;



// Install aSurfaceHolder.Callback so we get notified when the

// underlyingsurface is created and destroyed.

        mHolder = getHolder();

        mHolder.addCallback(this);

// deprecatedsetting, but required on Android versions prior to 3.0

        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        }



        public void surfaceCreated(SurfaceHolder holder) {

// The Surfacehas been created, now tell the camera where to draw

// the preview.

        try {

        mCamera.setPreviewDisplay(holder);

        mCamera.setDisplayOrientation(90);

        mCamera.startPreview();

        } catch (IOException e) {

        Log.d(TAG, "Errorsetting camera preview: " + e.getMessage());

        }

        }



        public void surfaceDestroyed(SurfaceHolder holder) {

// empty. Takecare of releasing the Camera preview in your

// activity.

        }



        public void surfaceChanged(SurfaceHolder holder, int format, int w,

        int h) {

// If yourpreview can change or rotate, take care of those events

// here.

// Make sure tostop the preview before resizing or reformatting it.



        if (mHolder.getSurface() == null) {

// previewsurface does not exist

        return;

        }



// stop previewbefore making changes

        try {

        mCamera.stopPreview();

        } catch (Exception e) {

// ignore: triedto stop a non-existent preview

        }



// set previewsize and make any resize, rotate or

// reformattingchanges here



// start previewwith new settings

        try {

        mCamera.setPreviewDisplay(mHolder);

        mCamera.setDisplayOrientation(90);

        mCamera.startPreview();



        } catch (Exception e) {

        Log.d(TAG, "Errorstarting camera preview: " + e.getMessage());

        }

        }

        }



private PictureCallback mPicture = new PictureCallback() {



@Override

public void onPictureTaken(byte[] data, Camera camera) {



        File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);

        if (pictureFile == null) {



        return;

        }



        try {

        FileOutputStream fos = new FileOutputStream(pictureFile);

        fos.write(data);

        fos.close();

        Log.i("cameratest", "pictureFiledata=" + data.length);

        } catch (FileNotFoundException e) {

        Log.i("cameratest", "File notfound: " + e.getMessage());

        } catch (IOException e) {

        Log.i("cameratest", "Erroraccessing file: " + e.getMessage());

        }

        }

        };



        private static File getOutputMediaFile(int type) {

// To be safe, you shouldcheck that the SDCard is mounted

// usingEnvironment.getExternalStorageState() before doing this.



        File mediaStorageDir = new File(

        Environment

        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),

        "MyCameraApp");

// This location works bestif you want the created images to be shared

// between applications andpersist after your app has been uninstalled.



// Create the storagedirectory if it does not exist

        if (!mediaStorageDir.exists()) {

        if (!mediaStorageDir.mkdirs()) {

        Log.d("MyCameraApp", "failed tocreate directory");

        return null;

        }

        }



// Create a media file name

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")

        .format(new Date());

        File mediaFile;

        if (type == MEDIA_TYPE_IMAGE) {

        mediaFile = new File(mediaStorageDir.getPath() + File.separator

        + "IMG_" + timeStamp + ".jpg");
            picturesrc=mediaStorageDir.getPath() + File.separator

                    + "IMG_" + timeStamp + ".jpg";

        } else if (type == MEDIA_TYPE_VIDEO) {

        mediaFile = new File(mediaStorageDir.getPath() + File.separator

        + "VID_" + timeStamp + ".mp4");
         picturesrc=mediaStorageDir.getPath() + File.separator

                 + "VID_" + timeStamp + ".mp4";
        } else {

        return null;

        }



        return mediaFile;

        }



@Override

protected void onStop() {

        Log.i("cameratest", "onStop");

        super.onStop(); // Always callthe superclass method first



        }



@Override

public void onPause() {

        super.onPause(); // Alwayscall the superclass method first

        Log.i("cameratest", "onPause");

// Release the Camera becausewe don't need it when paused

// and other activities mightneed to use it.

        if (mCamera != null) {

        mCamera.release();

        mCamera = null;

        }

        }
            public void click_to_image5(View v)
            {

                Log.i("click", "photo sure");
                Bundle mBundle=new Bundle();
                mBundle.putString("picturesrc",picturesrc);
                Intent intent = new Intent();
                intent.setClass(Image3Activity.this, Image5Activity.class);
               intent.putExtras(mBundle);
                startActivity(intent);
                overridePendingTransition(R.anim.abc_fade_in,
                        R.anim.abc_fade_out	);
            }
        }
