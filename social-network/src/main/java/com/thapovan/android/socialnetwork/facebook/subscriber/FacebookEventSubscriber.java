package com.thapovan.android.socialnetwork.facebook.subscriber;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.thapovan.android.socialnetwork.facebook.model.FacebookProfile;

public interface FacebookEventSubscriber {
    public void onFacebookLoginSuccess(LoginResult loginResult);
    public void onFacebookLoginCancel();
    public void onFacebookLoginError(FacebookException error);
    public void onFacebookProfileGet(FacebookProfile profile);
}
