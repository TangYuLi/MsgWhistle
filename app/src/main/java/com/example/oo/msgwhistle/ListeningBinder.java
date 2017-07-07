package com.example.oo.msgwhistle;

import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by YULI on 2017/6/19.
 */
public class ListeningBinder extends Binder {
    @Override
    public void linkToDeath(DeathRecipient recipient, int flags) {
        super.linkToDeath(recipient, flags);
        Log.e("ListeningBinder","linkToDeath");
    }

    @Override
    public boolean unlinkToDeath(DeathRecipient recipient, int flags) {
        Log.e("ListeningBinder","unlinkToDeath");
        return super.unlinkToDeath(recipient, flags);
    }

    public String getValue(){
        Log.e("ListeningBinder","getValue");
        return "听歌";
    }
}
