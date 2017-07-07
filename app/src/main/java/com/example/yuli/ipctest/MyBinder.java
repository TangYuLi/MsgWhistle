package com.example.yuli.ipctest;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by YULI on 2017/6/19.
 */
public class MyBinder extends Binder implements ISeparatedService{

    public static ISeparatedService asInterface(IBinder binder){
        MyBinder myBinder = (MyBinder)binder;
        return myBinder;
    }

    @Override
    public String getValue() throws RemoteException {
        return "普通服务访问数据成功";
    }
}
