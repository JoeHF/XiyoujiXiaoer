package com.xiaoer.app;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class Myapp extends Application{

    private String mylabel ;
    private List mylist1;
    private List mylist2;
    private int  num1=0;
    private int num2=0;










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
    public int getNum1()
    {
        return num1;
    }
    public int getNum2()
    {
        return num2;
    }
    public void setNum1(int number)
    {
       num1=number;

    }
    public void setNum2(int number)
    {
        num2=number;

    }
    public void intialize()
    {
        num1=0;
        num2=0;
        mylist1=new ArrayList();
        mylist2=new ArrayList();
    }
    @Override
    public void onCreate() {
       mylist1=new ArrayList();
        mylist2=new ArrayList();

        // TODO Auto-generated method stub
        super.onCreate();
        setLabel("0"); //初始化全局变量
    }
}

