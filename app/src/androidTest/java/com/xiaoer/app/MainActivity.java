
package com.xiaoer.app;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;



import android.content.Intent;
import android.view.GestureDetector.OnGestureListener;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;



public class MainActivity extends FragmentActivity implements GestureDetector.OnGestureListener
{
    public static Fragment[] fragments;
    public static LinearLayout[] linearLayouts;
    public static TextView[] textViews;
    /**定义手势检测实例*/
    public static GestureDetector detector;
    /**做标签，记录当前是哪个fragment*/
    public int MARK=0;
    /**定义手势两点之间的最小距离*/
    final int DISTANT=50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //分别实例化和初始化fragement、lineatlayout、textview
        setfragment();
        setlinearLayouts();
        settextview();
        //创建手势检测器
        detector=new GestureDetector(this, this, null);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**初始化fragment*/
    public void setfragment()
    {
        fragments=new Fragment[3];
        fragments[0]=getSupportFragmentManager().findFragmentById(R.id.fragment1);
        fragments[1]=getSupportFragmentManager().findFragmentById(R.id.fragment2);
        fragments[2]=getSupportFragmentManager().findFragmentById(R.id.fragment3);
        getSupportFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[2])
                .show(fragments[0]).commit();
    }
    /**初始化linerlayout*/
    public void setlinearLayouts()
    {
        linearLayouts=new LinearLayout[3];
        linearLayouts[0]=(LinearLayout)findViewById(R.id.lay1);
        linearLayouts[1]=(LinearLayout)findViewById(R.id.lay2);
        linearLayouts[2]=(LinearLayout)findViewById(R.id.lay3);
        linearLayouts[0].setBackgroundResource(R.drawable.lay_select_bg);
    }
    /**初始化textview*/
    public void settextview()
    {
        textViews=new TextView[3];
        textViews[0]=(TextView)findViewById(R.id.fratext1);
        textViews[1]=(TextView)findViewById(R.id.fratext2);
        textViews[2]=(TextView)findViewById(R.id.fratext3);
        textViews[0].setTextColor(getResources().getColor(R.color.lightseagreen));
    }

    /**点击底部linerlayout实现切换fragment的效果*/
    public void LayoutOnclick(View v)
    {
        resetlaybg();//每次点击都重置linearLayouts的背景、textViews字体颜色
        switch (v.getId()) {
            case R.id.lay1:
                getSupportFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[2])
                        .show(fragments[0]).commit();
                linearLayouts[0].setBackgroundResource(R.drawable.lay_select_bg);
                textViews[0].setTextColor(getResources().getColor(R.color.lightseagreen));
                MARK=0;
                break;
            case R.id.lay2:
                getSupportFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[2])
                        .show(fragments[1]).commit();
                linearLayouts[1].setBackgroundResource(R.drawable.lay_select_bg);
                textViews[1].setTextColor(getResources().getColor(R.color.lightseagreen));
                MARK=1;
                break;
            case R.id.lay3:
                getSupportFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[2])
                        .show(fragments[2]).commit();
                linearLayouts[2].setBackgroundResource(R.drawable.lay_select_bg);
                textViews[2].setTextColor(getResources().getColor(R.color.lightseagreen));
                MARK=2;
                break;
            default:
                break;
        }
    }
    /**重置linearLayouts、textViews*/
    public void resetlaybg()
    {
        for(int i=0;i<3;i++)
        {
            linearLayouts[i].setBackgroundResource(R.drawable.tabfootbg);
            textViews[i].setTextColor(getResources().getColor(R.color.black));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        //将该Activity上触碰事件交给GestureDetector处理
        return detector.onTouchEvent(event);
    }

    public boolean onDown(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }
    /**滑动切换效果的实现*/

    public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
        // TODO Auto-generated method stub
        resetlaybg();
        //当是Fragment0的时候
        if(MARK==0)
        {
            if(arg1.getX()<arg0.getX()+DISTANT)
            {
                getSupportFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[2])
                        .show(fragments[1]).commit();
                linearLayouts[1].setBackgroundResource(R.drawable.lay_select_bg);
                textViews[1].setTextColor(getResources().getColor(R.color.lightseagreen));
                MARK=1;
            }
            else
            {
                linearLayouts[0].setBackgroundResource(R.drawable.lay_select_bg);
                textViews[0].setTextColor(getResources().getColor(R.color.lightseagreen));
            }
        }
        //当是Fragment1的时候
        else if (MARK==1)
        {
            if(arg1.getX() < arg0.getX()+DISTANT)
            {
                getSupportFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[2])
                        .show(fragments[2]).commit();
                linearLayouts[2].setBackgroundResource(R.drawable.lay_select_bg);
                textViews[2].setTextColor(getResources().getColor(R.color.lightseagreen));
                MARK=2;
            }
            else if(arg0.getX() < arg1.getX()+DISTANT)
            {
                getSupportFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[2])
                        .show(fragments[0]).commit();
                linearLayouts[0].setBackgroundResource(R.drawable.lay_select_bg);
                textViews[0].setTextColor(getResources().getColor(R.color.lightseagreen));
                MARK=0;
            }
            else
            {
                linearLayouts[1].setBackgroundResource(R.drawable.lay_select_bg);
                textViews[1].setTextColor(getResources().getColor(R.color.lightseagreen));
            }
        }
        //当是Fragment2的时候
        else if(MARK==2)
        {
            if(arg0.getX() < arg1.getX()+DISTANT)
            {
                getSupportFragmentManager().beginTransaction().hide(fragments[0]).hide(fragments[1]).hide(fragments[2])
                        .show(fragments[1]).commit();
                linearLayouts[1].setBackgroundResource(R.drawable.lay_select_bg);
                textViews[1].setTextColor(getResources().getColor(R.color.lightseagreen));
                MARK=1;
            }
            else
            {
                linearLayouts[2].setBackgroundResource(R.drawable.lay_select_bg);
                textViews[2].setTextColor(getResources().getColor(R.color.lightseagreen));
            }
        }
        return false;
    }

    public void onLongPress(MotionEvent arg0) {
        // TODO Auto-generated method stub

    }

    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
        // TODO Auto-generated method stub
        return false;
    }

    public void onShowPress(MotionEvent arg0) {
        // TODO Auto-generated method stub
    }

    public boolean onSingleTapUp(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * click listener
     */

}



