package com.thapovan.android.facebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.thapovan.android.R;
import com.thapovan.android.app.AppActivity;
import com.thapovan.android.commonutils.toast.ToastUtil;
import com.thapovan.android.home.ActivityHome;
import com.thapovan.android.imageutils.ImageUtil;
import com.thapovan.android.socialnetwork.facebook.FacebookLogin;
import com.thapovan.android.socialnetwork.facebook.model.FacebookProfile;
import com.thapovan.android.socialnetwork.facebook.subscriber.FacebookEventSubscriber;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FacebookLoginActivity extends AppActivity implements FacebookEventSubscriber {

    @BindView(R.id.buttonFBLogin)           Button buttonFBLogin;
    @BindView(R.id.buttonFBLogout)          Button buttonFBLogout;
    @BindView(R.id.textName)                TextView textName;
    @BindView(R.id.textEmail)               TextView textEmail;
    @BindView(R.id.imageProfile)            ImageView imageProfile;

    FacebookProfile profile = null;
    FacebookLoginActivity mActivity;

    public static void start(final Activity activity) {
        final Intent intent = new Intent(activity, FacebookLoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_login);
        ButterKnife.bind(mActivity);
        FacebookLogin.getInstance().initFacebookLogin(mActivity);

        refreshUI();
    }

    private void refreshUI(){
        if(FacebookLogin.getInstance().isUserSignedIn()){
            buttonFBLogin.setVisibility(View.GONE);
            buttonFBLogout.setVisibility(View.VISIBLE);
            if(profile == null){
                FacebookLogin.getInstance().getUserProfile(mActivity);
            }
        }else {
            buttonFBLogin.setVisibility(View.VISIBLE);
            buttonFBLogout.setVisibility(View.GONE);
        }

        if(profile != null){
            textName.setText(profile.getName());
            textEmail.setText(profile.getEmail());
            ImageUtil.loadImage(mActivity, profile.getImageUrl(), imageProfile, R.drawable.loading);
        }else {
            textName.setText(getString(R.string.label_welcome_guest));
            textEmail.setText("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FacebookLogin.getInstance().passLoginResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.buttonFBLogin)
    public void onButtonFBLogin() {
        FacebookLogin.getInstance().onLoginClicked(mActivity);
    }

    @OnClick(R.id.buttonFBLogout)
    public void onButtonFBLogout() {
        FacebookLogin.getInstance().onLogoutClicked();
        profile = null;
        refreshUI();
    }

    @Override
    public void onFacebookLoginSuccess(LoginResult loginResult) {
        ToastUtil.showToast(mActivity, getString(R.string.msg_fetching_facebook_profile));
        showProgress();
        FacebookLogin.getInstance().getUserProfile(mActivity);
    }

    @Override
    public void onFacebookLoginCancel() {

    }

    @Override
    public void onFacebookLoginError(FacebookException error) {

    }

    @Override
    public void onFacebookProfileGet(FacebookProfile profile) {
        this.profile = profile;
        hideProgress();
        refreshUI();
    }
}
