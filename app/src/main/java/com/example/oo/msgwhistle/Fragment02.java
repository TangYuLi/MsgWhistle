package com.example.oo.msgwhistle;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by o(^▽^)o on 2016/6/2.
 */
public class Fragment02 extends Fragment {
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Fragment02","回调了onAttach(Context context)");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Fragment02","回调了onCreate(Bundle savedInstanceState)");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("Fragment02","回调了onCreateView(....)");
        return inflater.inflate(R.layout.fragment02,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Fragment02","回调了onActivityCreated(Bundle savedInstanceState)");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Fragment02","回调了onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Fragment02","回调了onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Fragment02","回调了onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Fragment02","回调了onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Fragment02","回调了onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Fragment02","回调了onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Fragment02","回调了onDetach()");
    }
}
