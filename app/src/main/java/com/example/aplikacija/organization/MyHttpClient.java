package com.example.aplikacija.organization;

import java.util.ArrayList;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;


/**
 * Created by Alen on 2.6.2016.
 */
public class MyHttpClient {
    public static String getGoogleSpreadsheets(String googleDocID) {
        return "https://docs.google.com/spreadsheets/d/"+googleDocID+"/export?format=csv";
    }

    public static ArrayList<String[]> parseCSV(String responseString) {
        String[] vrstice = responseString.split("\n");
        //TODO Ctrl+Enter v sheet-u zmeša podatke!
        ArrayList<String[]> list = new ArrayList<String[]>();
        for (String s:vrstice) {
            s = s.trim();
            if (s.length()>0) {
                String[] col = s.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", 99);
                for (int i=0; i<col.length; i++) {
                    if (col[i].startsWith("\"") && col[i].endsWith("\"")) {
                        col[i] = col[i].substring(1, col[i].length()-1);
                    }
                }
                list.add(col);
            }
        }
        //System.out.println(list);
        return list;
    }

    private static SyncHttpClient client = new SyncHttpClient(); //AsyncHttpClient

    public static void get(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void getBinarno(String url, AsyncHttpResponseHandler fh){
        client.get(url,fh);
    }
    public static void post(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        client.post(url, params, responseHandler);
    }
}
