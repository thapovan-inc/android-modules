package com.thapovan.android.commonutils.keyboard;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtil {

    private static final String TAG = "KeyboardUtil";

    public static boolean hideKeyboard(Context context, View view) {
        if (view == null) return false;
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            return imm.isAcceptingText() && imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean showKeyboard(final Context context, final View view){
        if(view==null  || context==null) return false;
        try{
            if(view.hasFocus() || view.requestFocus()){
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                return true;
            }
        }catch (Exception e){
            Log.e(TAG, "showKeyboard: ", e);
        }
        return false;
    }

    public static void hideSoftKeyboard1(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        final View focusView = activity.getCurrentFocus();
        if (focusView == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(
                focusView.getWindowToken(), 0);
    }

    public static void hideSoftKeyboard2(Activity activity) {
        try{
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }catch (Exception e){
            Log.e(TAG, "hideSoftKeyboard2: ", e);
        }
    }
}
