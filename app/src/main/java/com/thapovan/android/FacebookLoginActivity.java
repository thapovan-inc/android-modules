package com.thapovan.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.thapovan.android.commonutils.toast.ToastUtil;
import com.thapovan.android.socialnetwork.facebook.FacebookLogin;
import com.thapovan.android.socialnetwork.facebook.subscriber.FacebookEventSubscriber;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FacebookLoginActivity extends AppCompatActivity implements FacebookEventSubscriber {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        ButterKnife.bind(this);
        FacebookLogin.getInstance().initFacebookLogin(getApplicationContext());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FacebookLogin.getInstance().passLoginResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.buttonFBLogin)
    public void onButtonFBLogin() {
        ToastUtil.showToast(this,"fkjghj");
        FacebookLogin.getInstance().onFBClicked(this);
    }

    @Override
    public void onFacebookLoginSuccess(LoginResult loginResult) {
        ToastUtil.showToast(this,"loginResult "+loginResult.getAccessToken().getToken() );
    }

    @Override
    public void onFacebookLoginCancel() {
        ToastUtil.showToast(this,"onFacebookLoginCancel ");
    }

    @Override
    public void onFacebookLoginError(FacebookException error) {
        ToastUtil.showToast(this,"onFacebookLoginError ");
    }
}
