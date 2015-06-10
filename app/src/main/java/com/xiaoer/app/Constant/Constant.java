package com.xiaoer.app.Constant;

/**
 * Created by Administrator on 2015/5/29.
 */
public class Constant {
    //StartActivityForResult constant number
    public static final int START_LBS = 10;
    public static final int START_REGISTER = 11;


    public static final int START_LBS_BACK = 100;
    public static final int START_REGISTER_BACK = 101;


    //rest http request
    public static final String GET_WEATHER_BY_CITY = "my/getWeatherByCity";
    public static final String REGISTER = "my/addUserByAccount";
    public static final String LOGIN = "waiter/waiterLoginByAccount";
    public static final String UpdateLocation="waiter/updateLocationById";
    public static final String getOrder="waiter/getOrderByWaiterid";
    public static final String getOrderDetail="order/getOrderDetail";
    public static  final String getOrderListByWaiterid="waiter/getOrderListByWaiterid";
    public static final String getCarDetailByid="my/getCarDetailByid";
    public static final  String  getVersionDetailByid="my/getVersionDetailByid";
    public static final  String  getSiteDetailByid="my/getSiteDetailByid";
    public static final  String  uploadpic="image/uploadpic";
  public static final String   waiterUploadImageBeforeWash="image/waiterUploadImageBeforeWash";
    public static final String acceptOrderByWaiterid="waiter/acceptOrderByWaiterid";
    public static final String startCleanCarByAccount="waiter/startCleanCarByAccount";
    public static final String endCleanCarByAccount="waiter/endCleanCarByAccount";
    public static final String waiterUploadImageAfterWash="image/waiterUploadImageAfterWash";
    public static  final String getUserIcon="image/getWaiterIcon";
    public static final String userUploadIcon="image/waiterUploadIcon";
}