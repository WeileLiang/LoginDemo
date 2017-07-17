package com.example.weibo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chenenyu.router.annotation.Route;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

@Route("Weibo")
public class WeiboLoginActivity extends AppCompatActivity implements View.OnClickListener {

//    private SsoHandler ssoHandler;
//    private Oauth2AccessToken token;

    private WeiboLogin weiboLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_login);

        findViewById(R.id.btn_weibo).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        weiboLogin = new WeiboLogin(this);
        weiboLogin.login();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        weiboLogin.handleActivityResult(requestCode, resultCode, data);
    }

}
