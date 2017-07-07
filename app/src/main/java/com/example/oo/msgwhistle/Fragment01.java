package com.example.oo.msgwhistle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by o(^▽^)o on 2016/6/2.
 */
public class Fragment01 extends Fragment implements View.OnFocusChangeListener{
    private EditText numEdit;
    private EditText contentEdit;
    private TextView leftNum;
    private boolean numIsFirst = true;
    private boolean contentIsFirst = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment01,container,false);
        numEdit = (EditText) view.findViewById(R.id.numEdit);
        contentEdit = (EditText)view.findViewById(R.id.contentEdit);
        leftNum = (TextView)view.findViewById(R.id.leftNum);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        numEdit.setOnFocusChangeListener(this);
        numEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals("请复制粘贴或手动输入举报号码")){
                    numEdit.setTextColor(getResources().getColor(R.color.gray));
                }
                else if(s.equals("")){
                    numEdit.setText("请复制粘贴或手动输入举报号码");
                    numEdit.setTextColor(getResources().getColor(R.color.gray));
                }
                else numEdit.setTextColor(getResources().getColor(R.color.black));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        contentEdit.setOnFocusChangeListener(this);
        contentEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals("请复制粘贴或手动输入举报内容")){
                    contentEdit.setTextColor(getResources().getColor(R.color.gray));
                    leftNum.setText("300");
                }
                else if(s.equals("")){
                    contentEdit.setText("请复制粘贴或手动输入举报内容");
                    contentEdit.setTextColor(getResources().getColor(R.color.gray));
                    leftNum.setText("300");
                }
                else{
                    contentEdit.setTextColor(getResources().getColor(R.color.black));
                    leftNum.setText(Integer.toString(300-s.length()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    //EditText获得焦点的同时会触发键盘
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.numEdit:
                if(hasFocus){
                    if(numEdit.getText().toString().equals("请复制粘贴或手动输入举报号码")){
                        numEdit.setText("");
                        numEdit.setTextColor(getResources().getColor(R.color.gray));
                    }
                }
                else{
                    if(numEdit.getText().toString().equals("")){
                        numEdit.setText("请复制粘贴或手动输入举报号码");
                        numEdit.setTextColor(getResources().getColor(R.color.gray));
                    }
                }
                break;
            case R.id.contentEdit:
                if(hasFocus){
                    if(contentEdit.getText().toString().equals("请复制粘贴或手动输入举报内容")){
                        contentEdit.setText("");
                    }
                }else{
                    if(contentEdit.getText().toString().equals("")){
                        contentEdit.setText("请复制粘贴或手动输入举报内容");
                        contentEdit.setTextColor(getResources().getColor(R.color.gray));
                    }
                }
                break;
        }
    }
}
