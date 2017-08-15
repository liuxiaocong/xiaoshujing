package com.xiaoshujing.kid.ui.fun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class FunActivity extends BaseActivity implements FunContract.View {
    FunContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);
        ButterKnife.bind(this);
        setPresenter(new FunPresentImpl(this));
    }

    @Override
    public void setPresenter(FunContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @OnClick({
            R.id.watch_video,
            R.id.play_game,
            R.id.back
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                finish();
            }
            break;
            case R.id.watch_video: {
                if (mPresenter != null) {
                    mPresenter.watchVideo();
                }
            }
            break;
            case R.id.play_game: {
                if (mPresenter != null) {
                    mPresenter.playGame();
                }
            }
            break;
        }
    }
}
