package com.thapovan.android.socialnetwork.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.thapovan.android.socialnetwork.facebook.model.FacebookProfile;
import com.thapovan.android.socialnetwork.facebook.subscriber.FacebookEventSubscriber;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class FacebookLogin {

    private FacebookProfile facebookProfile = null;
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

    private AccessToken getSignedInToken() {
        final AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null && !accessToken.isExpired()) {
            Log.d(LOG_TAG, "Facebook Access Token is OK. Token hashcode = " + accessToken.hashCode());
            return accessToken;
        }
        Log.d(LOG_TAG,"Facebook Access Token is null or expired.");
        return null;
    }

    public boolean isUserSignedIn() {
        return getSignedInToken() != null;
    }

    public void getUserProfile(final Activity activity){
//        clearUserInfo();

        if (!isUserSignedIn()) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Bundle parameters = new Bundle();
                parameters.putString("fields", "name,email,picture.type(large)");
                final GraphRequest graphRequest = new GraphRequest(AccessToken.getCurrentAccessToken(), "me");
                graphRequest.setParameters(parameters);
                GraphResponse response = graphRequest.executeAndWait();

                JSONObject json = response.getJSONObject();
                try {

                    String userName = json.getString("name");
                    String userImageUrl = json.getJSONObject("picture")
                            .getJSONObject("data")
                            .getString("url");
                    String userEmail = json.getString("email");
                            facebookProfile = new FacebookProfile(userName, userEmail, userImageUrl);

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            facebookSubscriber.onFacebookProfileGen(facebookProfile);
                        }
                    });
                } catch (final JSONException jsonException) {
                    Log.e(LOG_TAG,
                            "Unable to get Facebook user info. " + jsonException.getMessage() + "\n" + response,
                            jsonException);
                }
            }
        }).start();

        new Runnable() {
            @Override
            public void run() {

            }
        }.run();
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
