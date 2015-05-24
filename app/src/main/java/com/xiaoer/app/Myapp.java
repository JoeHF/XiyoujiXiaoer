package com.xiaoer.app;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Myapp extends Application{

    private String mylabel ;
    private List mylist1;
    private List mylist2;
    public String getLabel(){
        return mylabel;
    }
    public void setLabel(String s){
        this.mylabel = s;
    }
    public List getlist1()
    {
        return mylist1;
    }
    public List getlist2()
    {
        return mylist2;
    }
    public void setlist1(List list)
    {
        mylist1=list;

    }
    public void setlist2(List list)
    {
        mylist2=list;

    }
    @Override
    public void onCreate() {
       mylist1=new ArrayList();
        mylist2=new ArrayList();// TODO Auto-generated method stub
        super.onCreate();
        setLabel("0"); //初始化全局变量
    }
}

