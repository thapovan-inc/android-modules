package com.thapovan.android.socialnetwork.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.thapovan.android.socialnetwork.facebook.subscriber.FacebookEventSubscriber;

import java.util.Arrays;

public class FacebookLogin {

    private static FacebookLogin sInstance = getInstance();
    private static final String LOG_TAG = FacebookLogin.class.getSimpleName();
    private CallbackManager mFBCallbackManager;
    private FacebookEventSubscriber facebookSubscriber;

    public static FacebookLogin getInstance(){
        if(sInstance == null) {
            synchronized(FacebookLogin.class){
                sInstance = new FacebookLogin();
            }
        }
        return sInstance;
    }

    public void onFBClicked(Activity activity) {
        facebookSubscriber = (FacebookEventSubscriber) activity;
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile"));
    }

    public void passLoginResult(int requestCode, int resultCode, Intent data){
        if(mFBCallbackManager != null){
            mFBCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void initFacebookLogin(Context applicationContext) {
        // register fb callback manager & login manager
        FacebookSdk.setApplicationId("1796885913927913");
        FacebookSdk.sdkInitialize(applicationContext);
        mFBCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mFBCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                facebookSubscriber.onFacebookLoginSuccess(loginResult);
            }

            @Override
            public void onCancel() {
                facebookSubscriber.onFacebookLoginCancel();
            }

            @Override
            public void onError(FacebookException error) {
                facebookSubscriber.onFacebookLoginError(error);
            }
        });
    }
}
