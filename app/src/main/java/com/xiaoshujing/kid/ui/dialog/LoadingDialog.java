package com.xiaoshujing.kid.ui.dialog;

import android.app.Dialog;
import android.content.Context;

import com.xiaoshujing.kid.R;


/**
 * Created by Xingbo.Jie on 9/12/16.
 */

public class LoadingDialog extends Dialog {
    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    public static LoadingDialog show(Context context) {
        return LoadingDialog.show(context, false, null);
    }

    public static LoadingDialog show(Context context, boolean cancelable, OnCancelListener cancelListener) {
        LoadingDialog dialog = new LoadingDialog(context, R.style.CustomProgressDialog);
        dialog.setContentView(R.layout.loading_bar_layout);
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        dialog.show();
        return dialog;
    }
}
