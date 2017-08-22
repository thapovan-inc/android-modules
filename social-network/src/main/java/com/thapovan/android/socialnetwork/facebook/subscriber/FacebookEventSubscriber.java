package com.thapovan.android.socialnetwork.facebook.subscriber;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

public interface FacebookEventSubscriber {
    public void onFacebookLoginSuccess(LoginResult loginResult);
    public void onFacebookLoginCancel();
    public void onFacebookLoginError(FacebookException error);
}
