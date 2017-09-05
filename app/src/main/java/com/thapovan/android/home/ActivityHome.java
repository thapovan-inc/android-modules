package com.thapovan.android.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.thapovan.android.app.AppActivity;
import com.thapovan.android.R;
import com.thapovan.android.facebook.FacebookLoginActivity;
import com.thapovan.android.firebase.FirebaseActivity;
import com.thapovan.android.socialnetwork.facebook.FacebookLogin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityHome extends AppActivity {

    ActivityHome mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(mActivity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @OnClick(R.id.btnFirebaseLogin)
    public void onbtnFirebaseLogin() {
        FirebaseActivity.start(mActivity);
    }

    @OnClick(R.id.btnFacebook)
    public void onBtnFacebook() {
        FacebookLoginActivity.start(mActivity);
    }


}
