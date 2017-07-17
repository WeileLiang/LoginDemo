package com.example.qq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chenenyu.router.annotation.Route;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

@Route("QQ")
public class QQLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private QQLogin qqLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqlogin);

        findViewById(R.id.btn_qq).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_qq) {
            qqLogin = new QQLogin(this);
            qqLogin.login();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (qqLogin != null) qqLogin.handleActivityResult(requestCode, resultCode, data);

    }

}
