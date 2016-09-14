package com.example.administrator.shopmall.http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/9/11.
 */
public class IoUnits {
    public static String getData(String path){
        try {
            URL url=new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int code=connection.getResponseCode();
            Log.e("==","code=="+code);
            String str="";
            if (code==200){
                InputStream inputStream = connection.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer sb=new StringBuffer();
                while ((str=br.readLine())!=null){
                   // Log.e("===","=="+str);
                        sb.append(str);
                }
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
