package com.xiaoshujing.kid.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.umeng.message.PushAgent;

/**
 * Created by LiuXiaocong on 12/19/2016.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PushAgent.getInstance(this).onAppStart();
    }
}
