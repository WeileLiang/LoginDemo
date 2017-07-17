package com.example.gzliangweile.logindemo;

import android.app.Application;

import com.chenenyu.router.Router;

/**
 * Created by gzliangweile on 2017/7/13.
 */

public class LoginApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Router.initialize(this);
    }
}

