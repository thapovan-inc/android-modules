package com.thapovan.android.commonutils.log;

import android.util.Log;

import com.thapovan.android.commonutils.app.APILevel;

public abstract class LogPrinter {

    public void v(String tag, String message) {
        Log.v(tag, message);
    }

    public void d(String tag, String message) {
        Log.d(tag, message);
    }

    public void i(String tag, String message) {
        Log.i(tag, message);
    }

    public void w(String tag, String message) {
        Log.w(tag, message);
    }

    public void e(String tag, String message) {
        Log.e(tag, message);
    }

    public void wtf(String tag, String message) {
        if (APILevel.require(8))
            Log.wtf(tag, message);
        else
            Log.e(tag, message);
    }
}
