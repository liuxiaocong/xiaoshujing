package com.xiaoshujing.kid.ui.seasonList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.BaseActivity;
import com.xiaoshujing.kid.data.SeasonVideoManage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class SeasonListActivity extends BaseActivity implements SeasonListContract.View {
    public static final String EXT_SEASON_TYPE = "EXT_SEASON_TYPE";
    SeasonListContract.Presenter mPresenter;
    @BindView(R.id.study_item_list)
    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    int mSeasonType = SeasonVideoManage.SeasonType.EStudy.getValue();
    @BindView(R.id.title)
    TextView mTitle;

    String testUrl = "https://webapi-rings-aws.shabikplus.mozat.com/images/2016/10/28/aa/0/aa39debf46639a51247d72635$3d2a2c20161028.jpeg";

    public static void openActivity(Activity context, int seasonType) {
        Intent intent = new Intent(context, SeasonListActivity.class);
        intent.putExtra(EXT_SEASON_TYPE, seasonType);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent.hasExtra(EXT_SEASON_TYPE)) {
            mSeasonType = intent.getIntExtra(EXT_SEASON_TYPE, SeasonVideoManage.SeasonType.EStudy.getValue());
        }
        setContentView(R.layout.activity_season);
        ButterKnife.bind(this);
        if (mSeasonType == SeasonVideoManage.SeasonType.EStudy.getValue()) {
            mTitle.setText("学习专区");
        } else {
            mTitle.setText("视频专区");
        }

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mPresenter = new SeasonListPresentImpl(this, mSeasonType);
        setPresenter(mPresenter);
        mPresenter.bindView(this);
        mPresenter.start();
    }

    @OnClick({
            R.id.back
    })
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                finish();
            }
            break;
        }
    }

    @Override
    public void setPresenter(SeasonListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }
}
