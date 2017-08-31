package com.thapovan.android;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import com.thapovan.android.commonutils.dialog.DialogUtil;

public class AppActivity extends AppCompatActivity {

    public static final String CLASS_TAG    = AppActivity.class.getSimpleName();
    public static final String LOG_TAG = AppActivity.class.getSimpleName();

    private ProgressDialog mProgress;
    private AppActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
    }


    public void showProgress(){
        showProgress(R.string.label_loading);
    }

    public void showProgress(@StringRes int resId){
        showProgress(getString(resId));
    }

    public void showProgress(String message){
        if(mProgress!=null){
            mProgress.setMessage(message);
            return;
        }
        mProgress = DialogUtil.showProgressDialog(this,message);
    }

    public void hideProgress(){
        DialogUtil.hideProgressDialog(mProgress);
        mProgress=null;
    }
}
