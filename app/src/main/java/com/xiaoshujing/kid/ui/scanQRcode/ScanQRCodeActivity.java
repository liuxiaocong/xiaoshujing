package com.xiaoshujing.kid.ui.scanQRcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class ScanQRCodeActivity extends BaseActivity implements ScanQRCodeContract.View {
    ScanQRCodeContract.Presenter mPresenter;
    @BindView(R.id.qr_code_img)
    ImageView mQRCodeImage;
    @BindView(R.id.qr_code)
    TextView mQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code);
        ButterKnife.bind(this);
        mPresenter = new ScanQRCodePresentImpl(this);
        setPresenter(mPresenter);
        mPresenter.bindView(this);
        initUI();
        mPresenter.start();
    }

    private void initUI() {

    }

    @Override
    public void setPresenter(ScanQRCodeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @OnClick({

    })
    public void onViewClick(View view) {
        switch (view.getId()) {

        }
    }

    @Override
    public void setQRCodeImage(Bitmap bitmap) {
        if (mQRCodeImage != null) {
            mQRCodeImage.setImageBitmap(bitmap);
        }
    }

    @Override
    public void setQRCode(String qrCode) {
        if (mQRCode != null) {
            mQRCode.setText(qrCode);
        }
    }
}
