package com.example.oo.msgwhistle;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ListeningService extends Service {
    private Context context;

    public ListeningService(){
        super();
        Log.e("ListeningService","ListeningService()");
    }

    public ListeningService(Context context) {
        this.context = context;
        Log.e("ListeningService","ListeningService(context)");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(ListeningService.this, "创建服务", Toast.LENGTH_SHORT).show();
        Log.e("ListeningService","onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(ListeningService.this,"销毁服务",Toast.LENGTH_SHORT).show();
        Log.e("ListeningService","onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(ListeningService.this, "开始服务", Toast.LENGTH_SHORT).show();
        Log.e("ListeningService","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(ListeningService.this,"解绑服务",Toast.LENGTH_SHORT).show();
        Log.e("ListeningService","onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Toast.makeText(ListeningService.this, "重新绑定服务", Toast.LENGTH_SHORT).show();
        Log.e("ListeningService","onRebind");
        super.onRebind(intent);
    }

    @Override
    public IBinder onBind(Intent intent){
        // TODO: Return the communication channel to the service.
        Toast.makeText(ListeningService.this,"绑定服务",Toast.LENGTH_SHORT).show();
        Log.e("ListeningSerivce","onBind");
        return new ListeningBinder();
    }
}
