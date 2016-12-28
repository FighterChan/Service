package com.newland.cw.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button start_but;
    private Button stop_but;
    private Button hand_but;
    private Button bind_but;
    private Button unbind_but;
    private MyService.MyBinder myBinder;
    private boolean bindflg = false;
    //服务连接对象
    private ServiceConnection serviceconnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
            myBinder.getProgress();
            bindflg = true;
        }
        //当服务异常终止时会调用。注意，解除绑定服务时不会调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_Data();
        init_View();
    }

    private void init_View() {
        start_but = (Button) findViewById(R.id.start);
        stop_but = (Button) findViewById(R.id.stop);
        hand_but = (Button) findViewById(R.id.but3);
        bind_but = (Button) findViewById(R.id.but4);
        unbind_but = (Button) findViewById(R.id.but5);
        start_but.setOnClickListener(this);
        stop_but.setOnClickListener(this);
        hand_but.setOnClickListener(this);
        bind_but.setOnClickListener(this);
        unbind_but.setOnClickListener(this);
    }

    private void init_Data() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                Intent startIntent = new Intent(this,MyService.class);
                startService(startIntent);
                break;
            case R.id.stop:
                Intent stopIntent = new Intent(this,MyService.class);
                stopService(stopIntent);
                break;
            case R.id.but3:
                Log.d("MainActivity","主线程的id是："+Thread.currentThread().getId());
                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);
                break;
            case R.id.but4:
                Intent bindIntent = new Intent(this,MyService.class);
                bindService(bindIntent,serviceconnection,BIND_AUTO_CREATE);
                break;
            case R.id.but5:
                if (bindflg == true) {
                    bindflg = false;
                    unbindService(serviceconnection);
                }
                break;
            default:
                break;
        }
    }
}
