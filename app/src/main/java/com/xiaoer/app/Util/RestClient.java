package com.xiaoer.app.Util;



 import com.loopj.android.http.*;
// Markdown supported   Edit in fullscreen




 public class RestClient {
     private static final String BASE_URL = "http://121.40.130.54/xiyouji/index.php/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
     }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
         return BASE_URL + relativeUrl;
   }
 }

