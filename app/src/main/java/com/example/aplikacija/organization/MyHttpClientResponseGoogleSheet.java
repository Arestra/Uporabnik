package com.example.aplikacija.organization;

import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Alen on 2.6.2016.
 */
public class MyHttpClientResponseGoogleSheet extends TextHttpResponseHandler {

    String resp;
    MyDataList all;
    boolean ok;
    ArrayList<String[]> list;

    public MyHttpClientResponseGoogleSheet(MyDataList all) {
        super();
        this.all = all;
        resp = "";
        ok=false;
        list=null;
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        resp = responseString;
        ok=false;

    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        resp=responseString;
        boolean change=false;
        list = MyHttpClient.parseCSV(responseString.toString());
        ok=all.setByList(list);
    }

}
