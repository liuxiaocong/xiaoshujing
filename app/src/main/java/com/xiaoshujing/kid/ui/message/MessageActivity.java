package com.xiaoshujing.kid.ui.message;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class MessageActivity extends BaseActivity implements MessageContract.View {
    MessageContract.Presenter mPresenter;
    @BindView(R.id.message_item_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.back)
    View mBack;
    LinearLayoutManager mLinearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mPresenter = new MessagePresentImpl(this);
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mPresenter != null) {
                    return mPresenter.handlerTouchEvent(view, motionEvent);
                }
                return false;
            }
        });
        setPresenter(mPresenter);
        mPresenter.bindView(this);
        mPresenter.start();
    }

    @Override
    public void setPresenter(MessageContract.Presenter presenter) {
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

    @OnClick({R.id.back})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.back: {
                finish();
            }
            break;
        }
    }

    @Override
    public View findChildViewUnder(int x, int y) {
        if (mRecyclerView != null) {
            return mRecyclerView.findChildViewUnder(x, y);
        }
        return null;
    }
}
