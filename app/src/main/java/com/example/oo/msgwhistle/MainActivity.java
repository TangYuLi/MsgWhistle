package com.example.oo.msgwhistle;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yuli.ipctest.ISeparatedService;
import com.example.yuli.ipctest.MyBinder;
import com.example.yuli.ipctest.aidl.IMyService;

public class MainActivity extends Activity implements View.OnTouchListener,View.OnClickListener{
    private LinearLayout tab_1;
    private LinearLayout tab_2;
    private LinearLayout tab_3;
    private LinearLayout tab_4;
    private ImageButton tabBtn_1;
    private ImageButton tabBtn_2;
    private ImageButton tabBtn_3;
    private ImageButton tabBtn_4;
    private Button turn;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn_start_service;
    private Button btn_stop_service;
    private Button btn_bind_service;
    private Button btn_unbind_service;
    private Button btn_getData;

    private Fragment01 fram_1;
    private Fragment02 fram_2;
    private Fragment03 fram_3;
    private Fragment04 fram_4;

    private FragmentManager fm;
    private FragmentTransaction transaction;

    private boolean tabBtn01IsSelected = false;
    private boolean tabBtn02IsSelected = false;
    private boolean tabBtn03IsSelected = false;
    private boolean tabBtn04IsSelected = false;

    private ServiceConnection aidlConn;
    private ServiceConnection ordinConn;
    private ServiceConnection listenConn;

    private ListeningBinder listenBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if (aidlConn == null){
            aidlConn = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    IMyService iMyService = IMyService.Stub.asInterface(service);
                    try {
                        String value = iMyService.getValue();
                        Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
                        Log.e("aidlconn","onServiceConnected");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Toast.makeText(MainActivity.this,"远程获取数据不成功",Toast.LENGTH_SHORT).show();
                    Log.e("aidlconn","onServiceDisconnected");
                }
            };
        }
        if (ordinConn == null){
            ordinConn = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    ISeparatedService iss = MyBinder.asInterface(service);
                    try {
                        String value = iss.getValue();
                        Toast.makeText(MainActivity.this,value,Toast.LENGTH_SHORT).show();
                        Log.e("ordinconn","onServiceConnected");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Toast.makeText(MainActivity.this, "又不成功", Toast.LENGTH_SHORT).show();
                    Log.e("ordinconn","onServiceDisconnected");
                }
            };
        }
        tab_1 = (LinearLayout)findViewById(R.id.linear_1);
        tab_2 = (LinearLayout)findViewById(R.id.linear_2);
        tab_3 = (LinearLayout)findViewById(R.id.linear_3);
        tab_4 = (LinearLayout)findViewById(R.id.linear_4);
        tabBtn_1 = (ImageButton)findViewById(R.id.tabBtn_1);
        tabBtn_2 = (ImageButton)findViewById(R.id.tabBtn_2);
        tabBtn_3 = (ImageButton)findViewById(R.id.tabBtn_3);
        tabBtn_4 = (ImageButton)findViewById(R.id.tabBtn_4);

        btn_start_service = (Button)findViewById(R.id.btn_start_service);
        btn_stop_service = (Button)findViewById(R.id.btn_stop_service);
        btn_bind_service = (Button)findViewById(R.id.btn_bind_service);
        btn_unbind_service = (Button)findViewById(R.id.btn_unbind_service);
        btn_getData = (Button)findViewById(R.id.btn_getData);
        btn_getData.setOnClickListener(this);
        btn_start_service.setOnClickListener(this);
        btn_stop_service.setOnClickListener(this);
        btn_bind_service.setOnClickListener(this);
        btn_unbind_service.setOnClickListener(this);

        //跨进程启动AIDL服务
        btn1 = (Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.yuli.ipctest.aidl.IMyService");
                intent.setPackage("com.example.yuli.ipctest");
//                intent.setComponent(new ComponentName
//                        ("com.example.yuli.ipctest",
//                                "com.example.yuli.ipctest.aidl.IMyService"));
                startService(intent);
            }
        });
        //跨进程停止AIDL服务
        btn2 = (Button)findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.yuli.ipctest.aidl.IMyService");
                intent.setPackage("com.example.yuli.ipctest");
//                intent.setComponent(new ComponentName
//                        ("com.example.yuli.ipctest",
//                                "com.example.yuli.ipctest.aidl.IMyService"));
                stopService(intent);
            }
        });
        //跨进程绑定AIDL服务
        btn3 = (Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.yuli.ipctest.aidl.IMyService");
                intent.setPackage("com.example.yuli.ipctest");//在这里添加这行,表示兼容5.0之后
                bindService(intent,aidlConn, Service.BIND_AUTO_CREATE);
            }
        });
        //跨进程解绑AIDL服务
        btn4 = (Button)findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    unbindService(aidlConn);
                } catch (IllegalArgumentException e) {
                    Log.e("unbindService发生异常","已经多次解绑了service");
                }
            }
        });
        //跨进程启动普通服务
        btn5 = (Button)findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.yuli.ipctest.ISeparatedService");
                intent.setPackage("com.example.yuli.ipctest");
//                intent.setComponent(new ComponentName(
//                        "com.example.yuli.ipctest",
//                        "com.example.yuli.ipctest.ISeparatedService"
//                ));
                startService(intent);
            }
        });
        //跨进程停止普通服务
        btn6 = (Button)findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.yuli.ipctest.ISeparatedService");
                intent.setPackage("com.example.yuli.ipctest");
//                intent.setComponent(new ComponentName(
//                        "com.example.yuli.ipctest",
//                        "com.example.yuli.ipctest.ISeparatedService"
//                ));
                stopService(intent);
            }
        });
        //跨进程绑定普通服务
        btn7 = (Button)findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.yuli.ipctest.ISeparatedService");
                intent.setPackage("com.example.yuli.ipctest");
                bindService(intent,ordinConn,Service.BIND_AUTO_CREATE);
            }
        });
        //跨进程解绑普通服务
        btn8 = (Button)findViewById(R.id.btn8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(ordinConn);
            }
        });


        turn = (Button)findViewById(R.id.turn);
        turn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        tabBtn_1.setOnTouchListener(this);
        tabBtn_2.setOnTouchListener(this);
        tabBtn_3.setOnTouchListener(this);
        tabBtn_4.setOnTouchListener(this);
        fram_1 = new Fragment01();
        Log.e("fragment创建实例","fram_2 = new Fragment02();");
        fram_2 = new Fragment02();
        fram_3 = new Fragment03();
        fram_4 = new Fragment04();
//        fm = getFragmentManager();
//        transaction = fm.beginTransaction();
//        transaction.replace(R.id.frame,fram_1);
//        transaction.commit();
//        select(tabBtn_1);
    }

    public void select(View v){
        switch(v.getId()){
            case R.id.tabBtn_1:
                tab_1.setSelected(true);
                tabBtn_1.setImageResource(R.drawable.active1);
                tabBtn01IsSelected = true;
                break;
            case R.id.tabBtn_2:
                tab_2.setSelected(true);
                tabBtn_2.setImageResource(R.drawable.active2);
                tabBtn02IsSelected = true;
                break;
            case R.id.tabBtn_3:
                tab_3.setSelected(true);
                tabBtn_3.setImageResource(R.drawable.active3);
                tabBtn03IsSelected = true;
                break;
            case R.id.tabBtn_4:
                tab_4.setSelected(true);
                tabBtn_4.setImageResource(R.drawable.active4);
                tabBtn04IsSelected = true;
                break;
        }
    }

    public void unSelect(View v){
        switch (v.getId()){
            case R.id.tabBtn_1:
                tab_1.setSelected(false);
                tabBtn_1.setImageResource(R.drawable.origin1);
                tabBtn01IsSelected = false;
                break;
            case R.id.tabBtn_2:
                tab_2.setSelected(false);
                tabBtn_2.setImageResource(R.drawable.origin2);
                tabBtn02IsSelected = false;
                break;
            case R.id.tabBtn_3:
                tab_3.setSelected(false);
                tabBtn_3.setImageResource(R.drawable.origin3);
                tabBtn03IsSelected = false;
                break;
            case R.id.tabBtn_4:
                tab_4.setSelected(false);
                tabBtn_4.setImageResource(R.drawable.origin4);
                tabBtn04IsSelected = false;
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        unSelect(tabBtn_1);
        unSelect(tabBtn_2);
        unSelect(tabBtn_3);
        unSelect(tabBtn_4);
        fm = getFragmentManager();
        transaction = fm.beginTransaction();
        switch(v.getId()){
            case R.id.tabBtn_1:
                if(fram_1 == null){
                    fram_1 = new Fragment01();
                }
                if(tabBtn01IsSelected==false){
                    transaction.replace(R.id.frame,fram_1);
                    select(tabBtn_1);
                    transaction.commit();
                }
                else;
                break;
            case R.id.tabBtn_2:
                if(fram_2 == null){
                    fram_2 = new Fragment02();
                }
                if(tabBtn02IsSelected==false){
                    transaction.replace(R.id.frame,fram_2);
                    select(tabBtn_2);
                    transaction.commit();
                }
                else;
                break;
            case R.id.tabBtn_3:
                if(fram_3 == null){
                    fram_3 = new Fragment03();
                }
                if(tabBtn03IsSelected==false){
                    transaction.replace(R.id.frame,fram_3);
                    select(tabBtn_3);
                    transaction.commit();
                }
                else;
                break;
            case R.id.tabBtn_4:
                if(fram_4 == null){
                    fram_4 = new Fragment04();
                }
                if(tabBtn04IsSelected==false){
                    transaction.replace(R.id.frame,fram_4);
                    select(tabBtn_4);
                    transaction.commit();
                }
                else;
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (listenBinder == null){
            listenBinder = new ListeningBinder();
        }
        if (listenConn == null){
            listenConn = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    listenBinder = (ListeningBinder)service;
                    Toast.makeText(MainActivity.this,"成功与服务连接",Toast.LENGTH_SHORT).show();
                    Log.e("ListenConn","onServiceConnected");
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    Toast.makeText(MainActivity.this, "与服务断开了", Toast.LENGTH_SHORT).show();
                    Log.e("ListenConn","onServiceDisconnected");
                }
            };
        }
        switch (v.getId()){
            case R.id.btn_start_service:
                Intent intent = new Intent(getApplicationContext()
                        ,ListeningService.class);
                startService(intent);
                break;
            case R.id.btn_stop_service:
                Intent intent1 = new Intent(getApplicationContext()
                        ,ListeningService.class);
                stopService(intent1);
                break;
            case R.id.btn_bind_service:
                Intent intent2 = new Intent(getApplicationContext()
                        ,ListeningService.class);
                bindService(intent2,listenConn,BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                unbindService(listenConn);
                break;
            case R.id.btn_getData:
                String value = listenBinder.getValue();
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }
}
