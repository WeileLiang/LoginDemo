package com.example.weibo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.loginlibrary.LoginChannel;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

/**
 * Created by gzliangweile on 2017/7/13.
 */

public class WeiboLogin implements LoginChannel {

    private SsoHandler ssoHandler;
    private Oauth2AccessToken token;

    private Context context;

    public WeiboLogin(Context context) {
        this.context = context;
    }

    @Override
    public void login() {
        //微博SDK初始化
        WbSdk.install(context, new AuthInfo(context, WeiboConstants.APP_KEY, WeiboConstants.REDIRECT_URL, WeiboConstants.SCOPE));
        ssoHandler = new SsoHandler((Activity) context);
        ssoHandler.authorize(new SelfWbAuthListener());
    }

    @Override
    public void handleActivityResult(int requestCode, int resultCode, Intent data) {
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    public void showToast(){
        Toast.makeText(context, "weibo", Toast.LENGTH_LONG).show();
    }

    private class SelfWbAuthListener implements WbAuthListener {

        @Override
        public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
            token = oauth2AccessToken;
            if (token.isSessionValid()) {
                //这里只显示授权成功的提示，获取token信息等操作暂时略去
                Toast.makeText(context, "微博授权成功", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void cancel() {
            Toast.makeText(context, "取消微博授权", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            Toast.makeText(context, "微博授权失败", Toast.LENGTH_SHORT).show();
            Log.d("lwl", wbConnectErrorMessage.getErrorCode() + " " + wbConnectErrorMessage.getErrorMessage());
        }
    }

}
