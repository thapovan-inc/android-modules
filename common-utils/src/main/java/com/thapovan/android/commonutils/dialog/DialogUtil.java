package com.thapovan.android.commonutils.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.thapovan.android.commonutils.R;

public class DialogUtil {
    /**
     * Utility method for displaying a {@link AlertDialog} with an ok button.
     *
     * @param activity     The {@link Activity} showing the dialog.
     * @param titleResId   Resource Id for the title of the dialog.
     * @param messageResId Resource Id for the message of the dialog.
     */
    public static void showAlert(Activity activity, int titleResId, int messageResId) {
        // Use the resource Ids to get the strings
        String title = activity.getString(titleResId);
        String message = activity.getString(messageResId);

        // Show the alert
        showAlert(activity, title, message);
    }

    /**
     * Utility method for displaying a {@link AlertDialog} with an ok button.
     *
     * @param activity The {@link Activity} showing the dialog.
     * @param title    Title of the dialog.
     * @param message  Message of the dialog.
     */
    public static void showAlert(Activity activity, String title, String message) {
        showAlert(activity, title, message, R.string.dialog_ok,     false);
    }

    /**
     * Utility method for displaying a {@link AlertDialog} with an ok button which
     * finishes the provided {@link Activity}.
     *
     * @param activity     The {@link Activity} showing the dialog and to finish.
     * @param titleResId   Resource Resource Id for the title of the dialog.
     * @param messageResId Resource Id for the message of the dialog.
     */
    public static void showAlertAndFinish(Activity activity, int titleResId, int messageResId) {
        // Use the resource Ids to get the strings
        String title = activity.getString(titleResId);
        String message = activity.getString(messageResId);

        // Show the alert
        showAlertAndFinish(activity, title, message);
    }

    /**
     * Utility method for displaying a {@link AlertDialog} with an ok button which
     * finishes the provided {@link Activity}.
     *
     * @param activity The {@link Activity} showing the dialog and to finish.
     * @param title    Title of the dialog.
     * @param message  Message of the dialog.
     */
    public static void showAlertAndFinish(Activity activity, String title, String message) {
        showAlert(activity, title, message, R.string.dialog_ok, true);
    }

    /**
     * Method that will actually build the {@link AlertDialog} and display it.
     *
     * @param activity      The {@link Activity} showing the dialog.
     * @param title         Title of the dialog.
     * @param message       Message of the dialog.
     * @param okButtonResId Resource Id for the positive button.
     * @param finish        Whether clicking the positive button will finish the {@link Activity}
     */
    private static void showAlert(final Activity activity,
                                  String title,
                                  String message,
                                  int okButtonResId,
                                  final boolean finish) {
        // Use the AlertDialog.Builder class to create the dialog and display it
        new AlertDialog.Builder(activity, R.style.MyDialogStyle)
                .setIcon(null)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(okButtonResId, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Finish the activity if requested
                        if (finish) {
                            activity.finish();
                        }
                    }
                })
                .show();
    }

    /**
     * Utility method for showing a {@link AlertDialog} with an ok button that runs
     * the provided {@link Runnable}.
     *
     * @param activity     The {@link Activity} showing the dialog.
     * @param titleResId   Resource Id for the title of the dialog.
     * @param messageResId Resource Id for the message of the dialog.
     * @param runnable     Action to perform on click.
     */
    public static void showDialog(Activity activity,
                                  int titleResId,
                                  int messageResId,
                                  Runnable runnable) {
        // Use the resource Ids to get the strings
        String title = activity.getString(titleResId);
        String message = activity.getString(messageResId);

        // Show the alert
        showDialog(activity, title, message, runnable);
    }

    /**
     * Utility method for showing a {@link AlertDialog} with an ok button that runs
     * the provided {@link Runnable}.
     *
     * @param activity The {@link Activity} showing the dialog.
     * @param title    Title of the dialog.
     * @param message  Message of the dialog.
     * @param runnable Action to perform on click.
     */
    private static void showDialog(final Activity activity,
                                   String title,
                                   String message,
                                   final Runnable runnable) {
        // Use the AlertDialog.Builder class to create the dialog and display it
        new AlertDialog.Builder(activity, R.style.MyDialogStyle)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Run the provided action
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                })
                .show();
    }

    /**
     * Shows a cancel dialog
     *
     * @param okListener listener for confirming cancel action
     * @return created dialog;
     */
    public static AlertDialog showCancelDialog(final Activity activity, DialogInterface.OnClickListener okListener) {
        final AlertDialog.Builder dlgAlert = new AlertDialog
                .Builder(activity,R.style.MyDialogStyle)
                .setMessage(activity.getString(R.string.dialog_are_you_sure_cancel))
                .setPositiveButton(R.string.dialog_yes, okListener)
                .setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog dlg = dlgAlert.create();
        dlg.show();
        return dlg;
    }

    /**
     * Display a yes / no dialog with the specified message
     *
     * @param title       the title of the alert box
     * @param message     the message in the alert box
     * @param yesListener the listener to fire when the user clicks yes
     */
    public static void showYesNoDialog(final Activity activity,
                                       String title,
                                       String message,
                                       DialogInterface.OnClickListener yesListener) {
        final AlertDialog.Builder dlgAlert = new AlertDialog
                .Builder(activity, R.style.MyDialogStyle)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_yes, yesListener)
                .setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        dlgAlert.create().show();
    }

    public static void showYesDialogWithListener(final Activity activity,
                                       String title,
                                       String message,
                                       String okBtn,
                                       DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(activity, R.style.MyDialogStyle)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(okBtn, listener)
                .setCancelable(false)
        .show();
    }

    public static void showOkDialog(final Activity activity,
                                    @StringRes int title,
                                    @StringRes int message) {
        showOkDialog(activity, activity.getString(title), activity.getString(message), null);
    }

    public static void showOkDialog(final Activity activity,
                                    @StringRes int title,
                                    @StringRes int message,
                                    DialogInterface.OnClickListener listener) {
        showOkDialog(activity, activity.getString(title), activity.getString(message), listener);
    }

    public static void showOkDialog(final Activity activity,
                                                 String title,
                                                 String message,
                                                 DialogInterface.OnClickListener listener) {

        if(activity.isDestroyed()) return;

        new AlertDialog.Builder(activity, R.style.MyDialogStyle)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_ok, listener)
                .setCancelable(false)
                .show();
    }

}
