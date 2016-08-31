package com.example.grus95.utilslibrary.progress.manager;

import android.content.Context;

import com.example.grus95.utilslibrary.progress.progress_impl.SystemProgressDialog;
import com.example.grus95.utilslibrary.progress.progress_interface.ProgressDialogEngine;


/**
 * Created by grus95 on 16/8/31
 */
public class ProgressManager {

    private static volatile ProgressManager mProgressManager;

    private ProgressManager() {
    }

    public static ProgressManager getInstance() {
        if (null == mProgressManager) {
            synchronized (ProgressManager.class) {
                if (null == mProgressManager) {
                    mProgressManager = new ProgressManager();
                }
            }
        }
        return mProgressManager;
    }

    public ProgressDialogEngine show(Context context, CharSequence title,
                                     CharSequence message, boolean indeterminate, boolean cancelable) {
        return (ProgressDialogEngine) SystemProgressDialog.show(context, title, message, indeterminate, cancelable);
    }
}
