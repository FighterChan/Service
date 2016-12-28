package com.newland.cw.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by CW on 2016/12/28.
 */

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < 3; i++) {
            Log.d("MyIntentService","IntentService线程的id是："+ Thread.currentThread().getId());
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyIntentService", "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService","onDestroy");
    }
}
