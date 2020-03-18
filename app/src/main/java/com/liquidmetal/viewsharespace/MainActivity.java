package com.liquidmetal.viewsharespace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

//     View View1_1,View2_1;

//    int[][] arr = new int[5][6];
//    arr[0] = new int[]{0,1,0,1,0,1};

    //送られる最新のデータ
    private int[][] arr = {{0, 1, 0, 1, 0}, {0, 1, 0, 1, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
    //今既存のデータ
    private int[][] arrCache = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};
    //暂存一元数组
    int[] array;

    IntentFilter intentFilter = new IntentFilter();

    ChangeToNobodyReceiver changeToNobodyReceiver = new ChangeToNobodyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //東端１階から５階までのトイレViewを定義する
        View view1_et = (View) findViewById(R.id.east_toilet).findViewById(R.id.floor_1);
        View view2_et = (View) findViewById(R.id.east_toilet).findViewById(R.id.floor_2);
        View view3_et = (View) findViewById(R.id.east_toilet).findViewById(R.id.floor_3);
        View view4_et = (View) findViewById(R.id.east_toilet).findViewById(R.id.floor_4);
        View view5_et = (View) findViewById(R.id.east_toilet).findViewById(R.id.floor_5);

        //東端１階から５階までのお風呂Viewを定義する
        View view1_es = (View) findViewById(R.id.east_shower).findViewById(R.id.floor_1);
        View view2_es = (View) findViewById(R.id.east_shower).findViewById(R.id.floor_2);
        View view3_es = (View) findViewById(R.id.east_shower).findViewById(R.id.floor_3);
        View view4_es = (View) findViewById(R.id.east_shower).findViewById(R.id.floor_4);
        View view5_es = (View) findViewById(R.id.east_shower).findViewById(R.id.floor_5);

        //東端１階から５階までの補食室Viewを定義する
        View view1_ek = (View) findViewById(R.id.east_kitchen).findViewById(R.id.floor_1);
        View view2_ek = (View) findViewById(R.id.east_kitchen).findViewById(R.id.floor_2);
        View view3_ek = (View) findViewById(R.id.east_kitchen).findViewById(R.id.floor_3);
        View view4_ek = (View) findViewById(R.id.east_kitchen).findViewById(R.id.floor_4);
        View view5_ek = (View) findViewById(R.id.east_kitchen).findViewById(R.id.floor_5);

        //西端１階から５階までのトイレViewを定義する
        View view1_wt = (View) findViewById(R.id.west_toilet).findViewById(R.id.floor_1);
        View view2_wt = (View) findViewById(R.id.west_toilet).findViewById(R.id.floor_2);
        View view3_wt = (View) findViewById(R.id.west_toilet).findViewById(R.id.floor_3);
        View view4_wt = (View) findViewById(R.id.west_toilet).findViewById(R.id.floor_4);
        View view5_wt = (View) findViewById(R.id.west_toilet).findViewById(R.id.floor_5);

        //西端１階から５階までのお風呂Viewを定義する
        View view1_ws = (View) findViewById(R.id.west_shower).findViewById(R.id.floor_1);
        View view2_ws = (View) findViewById(R.id.west_shower).findViewById(R.id.floor_2);
        View view3_ws = (View) findViewById(R.id.west_shower).findViewById(R.id.floor_3);
        View view4_ws = (View) findViewById(R.id.west_shower).findViewById(R.id.floor_4);
        View view5_ws = (View) findViewById(R.id.west_shower).findViewById(R.id.floor_5);

        //西端１階から５階までの補食室Viewを定義する
        View view1_wk = (View) findViewById(R.id.west_kitchen).findViewById(R.id.floor_1);
        View view2_wk = (View) findViewById(R.id.west_kitchen).findViewById(R.id.floor_2);
        View view3_wk = (View) findViewById(R.id.west_kitchen).findViewById(R.id.floor_3);
        View view4_wk = (View) findViewById(R.id.west_kitchen).findViewById(R.id.floor_4);
        View view5_wk = (View) findViewById(R.id.west_kitchen).findViewById(R.id.floor_5);

        //mapを定義して、中にViewを入れる
        Map<String, View> map = new LinkedHashMap<>();
        map.put("1_et", view1_et);
        map.put("2_et", view2_et);
        map.put("3_et", view3_et);
        map.put("4_et", view4_et);
        map.put("5_et", view5_et);

        map.put("1_es", view1_es);
        map.put("2_es", view2_es);
        map.put("3_es", view3_es);
        map.put("4_es", view4_es);
        map.put("5_es", view5_es);

        map.put("1_ek", view1_ek);
        map.put("2_ek", view2_ek);
        map.put("3_ek", view3_ek);
        map.put("4_ek", view4_ek);
        map.put("5_ek", view5_ek);

        map.put("1_wt", view1_wt);
        map.put("2_wt", view2_wt);
        map.put("3_wt", view3_wt);
        map.put("4_wt", view4_wt);
        map.put("5_wt", view5_wt);

        map.put("1_ws", view1_ws);
        map.put("2_ws", view2_ws);
        map.put("3_ws", view3_ws);
        map.put("4_ws", view4_ws);
        map.put("5_ws", view5_ws);

        map.put("1_wk", view1_wk);
        map.put("2_wk", view2_wk);
        map.put("3_wk", view3_wk);
        map.put("4_wk", view4_wk);
        map.put("5_wk", view5_wk);


        //送られるデータと既存するデータと比べて異なるところがあればviewに色を付ける
//        if(!arrCache.equals(arr)){
//            arrCache = arr;
//            coloring(arrCache,map);
//        }

        intentFilter.addAction("com.liquidmetal.viewsharespace.MY_BROADCAST");
        //arrCache = implement(arr, arrCache, map);
        function(map);
//        while(true){
//
//            /*
//            取得数据部分
//             */
//            HttpUtil.sendOkHttpRequest("http://192.168.0.137/", new okhttp3.Callback() {
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    // サーバーから具体的なデータを得る
//                    String responseData = response.body().string();
//                    array = HttpUtil.parseJSONWithJSONObject(responseData);
//                }
//
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    // 在这里对异常情况进行处理
//                }
//
//            });
//            //int[] -> int[][]
//            int[][] arr =cutAssemble(array);
//            //実装とデータ更新
//            arrCache = implement(arr, arrCache, map);
//            //休眠1000 毫秒，也就是1秒
//            try {
//                Thread.sleep(1000);
//            } catch(InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
//        }

    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(changeToNobodyReceiver);
    }
    //viewに色を付けると後に提示する方法を定義する
//    private void coloring(int[][] arr, Map<String,View> map){
//        Set<String> keySet = map.keySet();
//        Iterator<String> it = keySet.iterator();
//
//        //  while (it.hasNext()){
//        for(int i=0;i<arr.length;i++){
//            for(int j=0;j<arr[i].length;j++){
//                int v = arr[i][j];
//                String key = it.next();
//                System.out.println(key);
//                if(v==1) {
//                    View view = map.get(key);
//                    view.setBackgroundColor(Color.rgb(139,0,139));
//                    view.setOnClickListener(new View.OnClickListener() {
//                        int i = 0;
//                        public void onClick(View v) {
//                            i++;
//                            if(i%2==1){
//                                v.setBackgroundColor(Color.rgb(0,0,139));
//                            }else{
//                                v.setBackgroundColor(Color.rgb(139,0,139));
//                            }
//
//                        }
//
//                    });
//                }
//            }
//        }
//        //   }
//    }

    //実装する方法を定義する
    private int[][] implement(int[][] arr, int[][] arrCache, Map<String, View> map) {
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                String key = it.next();//keyを次に移動する
                View view = map.get(key);
                //送られるデータと既存するデータは異なったら、行動を起こす
                if (!(arr[i][j] == arrCache[i][j])) {
                    if (arr[i][j] == 1) {//人いるになったら、色を付ける
                        view.setBackgroundColor(Color.rgb(139, 0, 139));
                        view.setOnClickListener(new View.OnClickListener() {//ブロードキャストイベントの設定と取り消し
                            int i = 0;

                            public void onClick(View v) {
                                i++;
                                if (i % 2 == 1) {//ブロードキャストイベントを設定する
                                    registerReceiver(changeToNobodyReceiver, intentFilter);
                                    v.setBackgroundColor(Color.rgb(0, 0, 139));
                                } else {//ブロードキャストイベントを取り消し
                                    unregisterReceiver(changeToNobodyReceiver);
                                    v.setBackgroundColor(Color.rgb(139, 0, 139));
                                }
                            }
                        });
                    } else {//人いないになったら、ブロードキャストを発送する
                        view.setBackgroundColor(Color.rgb(218, 218, 218));
                        Intent intent = new
                                Intent("com.liquidmetal.viewsharespace.MY_BROADCAST");
                        sendBroadcast(intent);
                    }
                    arrCache[i][j] = arr[i][j];//データを更新する
                }
            }
        }
        return arrCache;
    }

    public static int[][] cutAssemble(int[] array) {
        int[] a = array;
        if (a == null) {
            Log.d("hoge", "null in");
        }
        int size = 5;
        int[][] b = new int[6][5];
        int j;//代表第几组，因为每组元素有5个（size），所以这里就被分成了6组，j = i/size
        int k;//代表每组内的索引，因为每组内有5个元素，所以k = i%size
        for (int i = 0; i < 30; i++) {
            j = i / size;
            switch (j) {
                case (0):
                    k = i % size;
                    b[0][k] = a[i];
                    continue;
                case (1):
                    k = i % size;
                    b[1][k] = a[i];
                    continue;
                case (2):
                    k = i % size;
                    b[2][k] = a[i];
                    continue;
                case (3):
                    k = i % size;
                    b[3][k] = a[i];
                    continue;
                case (4):
                    k = i % size;
                    b[4][k] = a[i];
                    continue;
                case (5):
                    k = i % size;
                    b[5][k] = a[i];
                    continue;

            }

        }
        //System.out.println(b[1][1]);
        return b;
    }

    public void function(Map map) {
        //一秒ごとでwebsiteからデータを取る
        //while (true) {
            final Map finalMap = map;

            HttpUtil.sendOkHttpRequest("http://192.168.1.129/demo1.php", new okhttp3.Callback() {

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    // サーバーから具体的なデータを得る
                    String responseData = response.body().string();
                    //int[]型のarrayを取る
                    Log.d("hoge", "response json str: " + responseData);
                    array = HttpUtil.parseJSONWithJSONObject(responseData);

                    //int[]型をint[][]型に変換する
                    System.out.println(array);

                    if (array == null) {
                        Log.d("hoge", "null out");
                        Log.d("hoge", "array size : " + array.length);
                    }
                    int[][] arr = cutAssemble(array);
                    //実装とデータ更新
                    arrCache = implement(arr, arrCache, finalMap);
//                    //sleep 1000 毫秒，也就是1秒
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException ex) {
//                        Thread.currentThread().interrupt();
//                    }
                }


                @Override
                public void onFailure(Call call, IOException e) {
                    // 在这里对异常情况进行处理
                }

            });

        }

    //}
}
