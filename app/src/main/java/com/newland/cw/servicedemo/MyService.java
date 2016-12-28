package com.newland.cw.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by CW on 2016/12/28.
 */

public class MyService extends Service {

    private static String TAG = MyService.class.getName().toString();
    private MyBinder myBinder = new MyBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        new Thread(new Runnable() {
            @Override
            public void run() {
                stopSelf();
                Log.d(TAG,"stopSelf");
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    class MyBinder extends Binder{
        public void startDownload(){
            Log.d(TAG,"startDownload");
        }
        public int getProgress(){
            Log.d(TAG,"getProgress");
            return 0;
        }
    }
}
