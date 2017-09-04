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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @OnClick(R.id.btnFirebaseLogin)
    public void onbtnFirebaseLogin() {
        FacebookLogin.getInstance().onLoginClicked(mActivity);
    }

    @OnClick(R.id.btnFacebook)
    public void onBtnFacebook() {
        FacebookLoginActivity.start(mActivity);
    }


}
