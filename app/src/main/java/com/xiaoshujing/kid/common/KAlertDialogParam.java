/**
 * Copyright (c) 2012 Mozat Pte Ltd (Singapore)
 * http://www.mozat.com
 * <p/>
 * Project: Mozat
 */
package com.xiaoshujing.kid.common;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.View;

/**
 * @author Ken Created data: Jul 16, 2012
 */
public class KAlertDialogParam {
    Context fCtx = null;
    String fTitle = null;
    String fContent = null;
    String fButtonOk = null;
    String fButtonCancel = null;
    boolean fCancelAble = true;
    OnClickListener fOnCancelListener = null;
    OnClickListener fOnOkListener = null;
    View fView = null;
    boolean fAbleToCancelOutside = true;

    public KAlertDialogParam(Context ctx, String title, String content) {
        fCtx = ctx;
        fTitle = title;
        fContent = content;
    }

    public KAlertDialogParam(Context ctx, String title, String content, String ok, String cancel) {
        fCtx = ctx;
        fTitle = title;
        fContent = content;
        fButtonOk = ok;
        fButtonCancel = cancel;
    }

    public KAlertDialogParam(Context ctx, View view, String ok, String cancel) {
        fCtx = ctx;
        fButtonOk = ok;
        fButtonCancel = cancel;
        fView = view;
    }

    public void setAbleToCancelOutside(boolean ableToCancelOutside) {
        fAbleToCancelOutside = ableToCancelOutside;
    }
    public void setCancelAble(boolean cancelAble) {
        fCancelAble = cancelAble;
    }

    public void setOnCancelClickListener(OnClickListener onCancelListener) {
        fOnCancelListener = onCancelListener;
    }

    public void setOnOkClickListener(OnClickListener onOkListener) {
        fOnOkListener = onOkListener;
    }
}
