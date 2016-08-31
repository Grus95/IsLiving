package com.example.grus95.utilslibrary.progress;

import android.app.Activity;
import android.app.ProgressDialog;

import com.example.grus95.utilslibrary.progress.manager.ProgressManager;
import com.example.grus95.utilslibrary.progress.progress_interface.ProgressDialogEngine;


/**
 * Created by grus95 on 16/8/31
 */
public class ProgressHold {
    public static ProgressDialogEngine progressDialog;

    public static void showLoading(Activity activity) {
        showLoading(activity, "正在处理...");
    }

    public static void showLoading(Activity activity, int resId) {
        showLoading(activity, activity.getString(resId));
    }

    public static void showLoading(Activity activity, String msg) {
        showLoading(activity, msg, true);
    }

    public static void showLoading(Activity activity, String msg, boolean cancelable) {
        try {
            if (progressDialog != null
                    && progressDialog.getContext().equals(activity)) {
                if (!progressDialog.isShowing()) {
                    progressDialog.show();
                    return;
                }
            }
            if (progressDialog != null) {
                progressDialog.dismiss();
                progressDialog = null;
            }
            progressDialog = ProgressManager.getInstance().show(activity, "", msg, false, cancelable);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCanceledOnTouchOutside(false);
        } catch (Exception e) {

        }
    }


    public static void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
