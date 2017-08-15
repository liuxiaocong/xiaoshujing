package com.xiaoshujing.kid.ui.makeWish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.BaseActivity;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.UserInfoManage;
import com.xiaoshujing.kid.model.video.GetSeasonVideoDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class MakeWishActivity extends BaseActivity implements MakeWishContract.View {
    MakeWishContract.Presenter mPresenter;
    static final String EXT_VIDEO_DETAIL_BEAN = "EXT_VIDEO_DETAIL_BEAN";
    GetSeasonVideoDetailBean mGetSeasonVideoDetailBean;
    @BindView(R.id.wish)
    View mWish;

    @BindView(R.id.title)
    EditText mEditTitle;

    @BindView(R.id.detail)
    EditText mEditDetail;

    public static void openActivity(Activity context, GetSeasonVideoDetailBean getSeasonVideoDetailBean) {
        Intent intent = new Intent(context, MakeWishActivity.class);
        intent.putExtra(EXT_VIDEO_DETAIL_BEAN, getSeasonVideoDetailBean);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_wish);
        Intent intent = getIntent();
        if (intent.hasExtra(EXT_VIDEO_DETAIL_BEAN)) {
            mGetSeasonVideoDetailBean = (GetSeasonVideoDetailBean) intent.getSerializableExtra(EXT_VIDEO_DETAIL_BEAN);
        }
        ButterKnife.bind(this);
        if (mGetSeasonVideoDetailBean != null) {
            mEditTitle.setHint(mGetSeasonVideoDetailBean.getName());
            mEditDetail.setHint(mGetSeasonVideoDetailBean.getDescription());
        }
        ButterKnife.bind(this);
        mPresenter = new MakeWishPresentImpl(this, mGetSeasonVideoDetailBean);
        setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(MakeWishContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @OnClick({
            R.id.wish,
            R.id.fanhui
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.wish: {
                if (mEditTitle == null) return;
                if (UserInfoManage.getInstance().getUserBean() == null) return;
                String baby = UserInfoManage.getInstance().getUserBean().getBaby();
                String title = null;
                if (mEditTitle.getText() != null) {
                    title = mEditTitle.getText().toString();
                }
                if (Util.isNullOrEmpty(title) && mGetSeasonVideoDetailBean != null) {
                    title = mGetSeasonVideoDetailBean.getName();
                }

                String detail = null;
                if (mEditDetail.getText() != null) {
                    detail = mEditDetail.getText().toString();
                }
                if (Util.isNullOrEmpty(detail) && mGetSeasonVideoDetailBean != null) {
                    detail = mGetSeasonVideoDetailBean.getDescription();
                }

                String pid = "";
                if (mGetSeasonVideoDetailBean != null) {
                    pid = mGetSeasonVideoDetailBean.getProduct_id();
                }
                mPresenter.makeWish(title, detail, baby, pid);
            }
            break;
            case R.id.fanhui: {
                finish();
            }
            break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

}
