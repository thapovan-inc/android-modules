package com.thapovan.android.commonutils.toast;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.Toast;

import com.thapovan.android.commonutils.text.TextUtil;

public class ToastUtil {

    public static void showToast(Context context, String message) {
        showToast(context, message, Gravity.NO_GRAVITY);
    }

    public static void showToast(Context context, @StringRes int stringId){
        if(context == null ) return;
        showToast(context, context.getString(stringId));
    }

    public static void showToast(Context context, @StringRes int stringId, int gravity){
        if(context == null ) return;
        showToast(context, context.getString(stringId), gravity);
    }

    public static void showCenterToast(Context context, String message){
        showToast(context, message, Gravity.CENTER);
    }

    public static void showCenterToast(Context context, @StringRes int stringId){
        showCenterToast(context,context.getString(stringId));
    }

    public static void showToast(Context context, String message, int gravity) {
        if (!TextUtil.isValidString(message)) return;
        if(gravity == Gravity.NO_GRAVITY){
            Toast.makeText(context,message, Toast.LENGTH_LONG).show();
        }else{
            Toast toast = Toast.makeText(context, message,Toast.LENGTH_LONG);
            toast.setGravity(gravity,0,0);
            toast.show();
        }
    }
}
