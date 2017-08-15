package com.xiaoshujing.kid.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

import com.xiaoshujing.kid.R;


/**
 * Created by liuxiaocong on 11/11/15.
 */
public class PlayVideoTipDialog extends Dialog implements View.OnClickListener {

    public interface IPlayVideoTipDialogListener {
        void onWishClicked(Dialog dialog);

        void onCloseClicked(Dialog dialog);
    }

    private IPlayVideoTipDialogListener mIPlayVideoTipDialogListener;
    private Context mContext;
    View mWish;
    View mClose;
    TextView mContent;

    public static PlayVideoTipDialog createInstance(Context context,
                                                    IPlayVideoTipDialogListener mIPlayVideoTipDialogListener, boolean closeAble) {
        return new PlayVideoTipDialog(context, mIPlayVideoTipDialogListener, closeAble);
    }

    public PlayVideoTipDialog(Context context, IPlayVideoTipDialogListener iPlayVideoTipDialogListener, boolean closeAble) {
        super(context, R.style.SimpleDialog);
        mIPlayVideoTipDialogListener = iPlayVideoTipDialogListener;
        mContext = context;
        setContentView(R.layout.dialog_play_tip);
        mContent = (TextView) findViewById(R.id.content);
        if (closeAble) {
            mContent.setText("你可以免费观看前3分钟");
        } else {
            mContent.setText("你已经免费观看前3分钟");
        }
        mWish = findViewById(R.id.wish);
        mWish.setOnClickListener(this);
        mClose = findViewById(R.id.close);
        mClose.setOnClickListener(this);
        mWish.setOnClickListener(this);
        setCanceledOnTouchOutside(closeAble);
        setCancelable(closeAble);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    @Override
    public void show() {
        super.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wish: {
                if (mIPlayVideoTipDialogListener != null) {
                    mIPlayVideoTipDialogListener.onWishClicked(this);
                }
            }
            break;
            case R.id.close: {
                if (mIPlayVideoTipDialogListener != null) {
                    mIPlayVideoTipDialogListener.onCloseClicked(this);
                }
                dismiss();
            }
            break;
        }
    }

}
