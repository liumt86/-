package com.liquidmetal.viewsharespace;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static int[] parseJSONWithJSONObject(String jsonData) {
        //定义ArrayList<String>型Collection，命名为statemt
        Collection<String> statemt = new ArrayList<>();
        //初始化int型数组j，用以储存statement中的值
        int[] intArray = {0};
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String floor = jsonObject.getString("floor");
                String direction = jsonObject.getString("direction");
                String category = jsonObject.getString("category");
                String statement = jsonObject.getString("statement");
                //向Collection中添加元素（String类型）
                statemt.add(statement);
            }
            //取得数组Object[]
            Object[] array = statemt.toArray();
            //重新指向，j重定位
            intArray = new int[array.length];
            //循环，String型转换成int型
            for (int k = 0; k < array.length; k++) {
                intArray[k] = Integer.parseInt(array[k].toString());
                // System.out.println(array[k]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("hoge", "int array size: " + intArray.length);

        return intArray;
    }

}

