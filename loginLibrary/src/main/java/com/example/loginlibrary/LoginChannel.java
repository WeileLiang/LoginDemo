package com.example.loginlibrary;

import android.content.Intent;


/**
 * Created by gzliangweile on 2017/7/13.
 */

public interface LoginChannel {

    void login();

    void handleActivityResult(int requestCode, int resultCode, Intent data);
}
