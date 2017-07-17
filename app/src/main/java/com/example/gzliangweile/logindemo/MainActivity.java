package com.example.gzliangweile.logindemo;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.Toast;

import com.chenenyu.router.Router;
import com.example.loginlibrary.LoginChannel;
import com.example.loginlibrary.PropertiesUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Properties;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Object qqLogin;
    private Object weiboLogin;

    //是否调试模式
    private boolean isDebug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }

    private void init() {
        isDebug = PropertiesUtils.getBooleanProperty(this, "debug.properties", "isDebug");

        findViewById(R.id.btn_qq_login).setOnClickListener(this);
        findViewById(R.id.btn_weibo_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_qq_login:
                if (isDebug) {
                    Router.build("QQ").go(this);
                } else {
                    qqLogin=reflect("com.example.qq.QQLogin");
                }

                break;
            case R.id.btn_weibo_login:
                if (isDebug) {
                    Router.build("Weibo").go(this);
                } else {
                    weiboLogin=reflect("com.example.weibo.WeiboLogin");
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("lwl", "onResult");
        if (qqLogin != null) {
            Log.d("lwl", "notnull");
            invokeHandleActivityResult("com.example.qq.QQLogin", qqLogin, requestCode, resultCode, data);
            qqLogin = null;
        }

        if (weiboLogin != null) {
            invokeHandleActivityResult("com.example.weibo.WeiboLogin", weiboLogin, requestCode, resultCode, data);
            weiboLogin = null;
        }
    }

    //反射调用XXLogin.login()
    private Object reflect(String className){
        Object loginObj=null;
        try {
            Class clazz = Class.forName(className);
            Constructor constructor = clazz.getConstructor(Context.class);
            loginObj = constructor.newInstance(this);
            Method login = clazz.getMethod("login");
            login.invoke(loginObj);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return loginObj;
    }

    //反射调用HandleActivityResult
    private void invokeHandleActivityResult(String className, Object loginObj, int requestCode, int resultCode, Intent data) {
        try {
            Class clazz = Class.forName(className);
            Method handleActivityResult = clazz.getMethod("handleActivityResult", int.class, int.class, Intent.class);
            handleActivityResult.invoke(loginObj, requestCode, resultCode, data);
        } catch (ClassNotFoundException e) {
            Log.d("lwl", e.toString());
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            Log.d("lwl", e.toString());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Log.d("lwl", e.toString());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.d("lwl", e.toString());
            e.printStackTrace();
        }
    }

}
