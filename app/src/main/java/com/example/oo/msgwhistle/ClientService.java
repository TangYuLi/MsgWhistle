package com.example.oo.msgwhistle;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.yuli.ipctest.aidl.IMyService;


/**
 * Created by YULI on 2017/6/30.
 */
public class ClientService extends Service implements IClientService{

    private IMyService myService;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("ClientService","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("ClientService","onCreate");
        ServiceConnection sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myService = IMyService.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    @Override
    public String getValue() {
        return "服务器远程调用客户端方法成功";
    }
}
