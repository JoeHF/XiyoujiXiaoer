package com.xiaoer.app;
        import java.io.BufferedInputStream;
        import java.io.BufferedOutputStream;
        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.OutputStream;
        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.PixelFormat;
        import android.hardware.Camera;
        import android.hardware.Camera.PictureCallback;
        import android.hardware.Camera.ShutterCallback;
        import android.util.Log;
        import android.view.SurfaceHolder;
        import android.view.SurfaceView;
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    SurfaceHolder holder;
   Camera myCamera;
    private PictureCallback jpeg = new PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // TODO Auto-generated method stub
            try
            {
                Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);
                File file = new File("/sdcard/wjh.jpg");
                BufferedOutputStream bos
                        = new BufferedOutputStream(new FileOutputStream(file));
                bm.compress(Bitmap.CompressFormat.JPEG,100,bos);
                bos.flush();
                bos.close();
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    };
    public MySurfaceView(Context context)
    {
        super(context);
        holder = getHolder();//获得surfaceHolder引用
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//设置类型
    }
    public void tackPicture()
    {
        myCamera.takePicture(null,null,jpeg);
    }
    public void voerTack()
    {
        myCamera.startPreview();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        myCamera.startPreview();
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        if(myCamera == null)
        {
            myCamera = Camera.open();//开启相机,不能放在构造函数中，不然不会显示画面.
            try {
                myCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        myCamera.stopPreview();//停止预览
        myCamera.release();//释放相机资源
        myCamera = null;
    }
}
