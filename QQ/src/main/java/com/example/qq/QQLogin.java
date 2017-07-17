package com.example.qq;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.loginlibrary.LoginChannel;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * Created by gzliangweile on 2017/7/13.
 */

public class QQLogin implements LoginChannel {

    private Context context;
    private Tencent mTencent;
    private BaseUIListener uiListener;

    public QQLogin(Context context) {
        this.context = context;
    }

    @Override
    public void login() {
        //SDK初始化
        mTencent = Tencent.createInstance(TencentConstants.APP_ID, context.getApplicationContext());
        uiListener = new BaseUIListener();
        mTencent.login((Activity) context, "all", uiListener);
        Log.d("lwl","login");
    }

    @Override
    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("lwl","result");
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, uiListener);
        }
    }

    public String forTest(){
        return "from test";
    }

    public void showToast(){
        Toast.makeText(context, "lwllwllwl", Toast.LENGTH_LONG).show();
    }

    private class BaseUIListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            Toast.makeText(context, "QQ授权成功", Toast.LENGTH_SHORT).show();
            Log.d("lwl","success");
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(context, "QQ授权失败", Toast.LENGTH_SHORT).show();
            Log.d("lwl", uiError.errorMessage);
        }

        @Override
        public void onCancel() {
            Toast.makeText(context, "取消QQ授权", Toast.LENGTH_SHORT).show();
            Log.d("lwl","success");
        }
    }

}
