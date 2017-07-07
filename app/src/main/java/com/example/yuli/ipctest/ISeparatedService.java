package com.example.yuli.ipctest;

import android.os.RemoteException;

/**
 * Created by YULI on 2017/6/19.
 */
public interface ISeparatedService{
    String getValue() throws RemoteException;
}