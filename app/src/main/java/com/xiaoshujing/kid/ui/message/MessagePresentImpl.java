package com.xiaoshujing.kid.ui.message;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.R;
import com.xiaoshujing.kid.api.RetrofitManager;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.LoadingType;
import com.xiaoshujing.kid.common.SharedPreferencesFactory;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.MessageDataManage;
import com.xiaoshujing.kid.data.MissionManage;
import com.xiaoshujing.kid.data.SeasonVideoManage;
import com.xiaoshujing.kid.event.EventMessageCountUpdate;
import com.xiaoshujing.kid.model.GetMissionBean;
import com.xiaoshujing.kid.model.message.DeleteMessageBean;
import com.xiaoshujing.kid.model.message.GetMessageBean;
import com.xiaoshujing.kid.model.video.GetSeasonBean;
import com.xiaoshujing.kid.model.video.GetSeasonVideoDetailBean;
import com.xiaoshujing.kid.model.video.GetSeasonVideoListBean;
import com.xiaoshujing.kid.ui.dialog.LoadingDialog;
import com.xiaoshujing.kid.ui.mission.MissionActivity;
import com.xiaoshujing.kid.ui.missionDetail.MissionDetailActivity;
import com.xiaoshujing.kid.ui.playVideoView.PlayVideoViewActivity;
import com.xiaoshujing.kid.ui.seasonList.SeasonListActivity;
import com.xiaoshujing.kid.ui.videoList.VideoListActivity;
import com.xiaoshujing.kid.widget.SlideView;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class MessagePresentImpl implements MessageContract.Presenter {
    final String TAG = "MessagePresentImpl";
    Activity mContext;
    MessageContract.View mView;
    MessageItemAdapter mMessageItemAdapter;
    private SlideView mLastSlideViewWithStatusOn;
    private SlideView mFocusedItemView;
    private LoadingType mLoadingType = LoadingType.EIdel;
    LoadingDialog mLoadingDialog;
    int mCurrentCount = 0;

    public MessagePresentImpl(Activity context) {
        mContext = context;
    }


    @Override
    public void bindView(MessageContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mMessageItemAdapter = new MessageItemAdapter(mContext, mOnSlideViewListener);
        if (mView != null) {
            mView.setAdapter(mMessageItemAdapter);
        }
        refreshData();
    }

    @Override
    public boolean handlerTouchEvent(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if (mView != null) {
                    View childViewUnder = mView.findChildViewUnder(x, y);
                    if (childViewUnder != null) {
                        SlideView slideView = (SlideView) childViewUnder.findViewById(R.id.slideView);
                        if (slideView != null) {
                            mFocusedItemView = slideView;
                        }
                    }
                }
            }
            default:
                break;
        }

        if (mFocusedItemView != null) {
            mFocusedItemView.onRequireTouchEvent(motionEvent);
        }

        return false;
    }

    private SlideView.OnSlideViewListener<GetMessageBean.MessageBean> mOnSlideViewListener = new SlideView.OnSlideViewListener<GetMessageBean.MessageBean>() {

        @Override
        public void onSlide(View view, int status) {
            if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
                mLastSlideViewWithStatusOn.shrink();
            }

            if (status == SLIDE_STATUS_ON) {
                mLastSlideViewWithStatusOn = (SlideView) view;
            }
        }

        @Override
        public void onClickItem(GetMessageBean.MessageBean item) {
            if (item.getExtra() != null && !Util.isNullOrEmpty(item.getExtra().getType())) {
                if (item.getExtra().getType().toLowerCase().equals("product")) {
                    if (item.getExtra().getProductType() == 4) {
                        //video
                        mLoadingDialog = LoadingDialog.show(mContext);
                        SeasonVideoManage.getInstance().getSeasonVideoDetail(new SimpleCallback<GetSeasonVideoDetailBean>() {
                            @Override
                            public void onSuccess(GetSeasonVideoDetailBean response) {
                                hideLoading();
                                PlayVideoViewActivity.openActivity(mContext,
                                        new GetSeasonVideoListBean.EpisodesBean.Builder()
                                                .cover_url(response.getCover_url())
                                                .created_at(response.getCreated_at())
                                                .description(response.getDescription())
                                                .isFree(response.isFree())
                                                .name(response.getName())
                                                .object_id(response.getObject_id())
                                                .resource_uri(response.getResource_uri())
                                                .updated_at(response.getUpdated_at())
                                                .video_url(response.getVideo_url())
                                                .build()
                                );
                            }

                            @Override
                            public void onFailure(int responseCode) {
                                hideLoading();
                                SeasonListActivity.openActivity(mContext, SeasonVideoManage.SeasonType.EFun.getValue());
                            }
                        }, item.getExtra().getEpisode_id());
                    } else if (item.getExtra().getProductType() == 3) {
                        //season
                        mLoadingDialog = LoadingDialog.show(mContext);
                        SeasonVideoManage.getInstance().getSeasonVideoList(new SimpleCallback<GetSeasonVideoListBean>() {
                            @Override
                            public void onSuccess(GetSeasonVideoListBean response) {
                                hideLoading();
                                VideoListActivity.openActivity(mContext, response.getObject_id(), response.getName());
                            }

                            @Override
                            public void onFailure(int responseCode) {
                                hideLoading();
                                SeasonListActivity.openActivity(mContext, SeasonVideoManage.SeasonType.EFun.getValue());
                            }
                        }, item.getExtra().getSeason_id());
                    } else {
                        SeasonListActivity.openActivity(mContext, SeasonVideoManage.SeasonType.EFun.getValue());
                    }
                } else if (item.getExtra().getType().toLowerCase().equals("mission")) {
                    mLoadingDialog = LoadingDialog.show(mContext);
                    MissionManage.getInstance().getMissionDetail(new SimpleCallback<GetMissionBean.MissionBean>() {
                        @Override
                        public void onSuccess(GetMissionBean.MissionBean response) {
                            hideLoading();
                            MissionDetailActivity.openActivity(mContext, response);
                        }

                        @Override
                        public void onFailure(int responseCode) {
                            hideLoading();
                            MissionActivity.openActivity(mContext, MissionManage.MissionType.EAll);
                        }
                    }, item.getExtra().getObject_id());
                }
            } else {
                MissionActivity.openActivity(mContext, MissionManage.MissionType.EAll);
            }
            //MissionActivity.openActivity(mContext, MissionManage.MissionType.valueOf(item.getMessageType()));
            //Toast.makeText(mContext, item.getContent(), Toast.LENGTH_SHORT).show();


            // 设置已读
            MessageDataManage.getInstance().updateMessage(new SimpleCallback<GetMessageBean.MessageBean>() {
                @Override
                public void onSuccess(GetMessageBean.MessageBean response) {
                    Util.DLog(TAG, "updateMessage onSuccess");
                    if (mContext != null && !mContext.isFinishing()) {
                        refreshData();
                    }
                }

                @Override
                public void onFailure(int responseCode) {
                    Util.DLog(TAG, "updateMessage onFailure");
                }
            }, item.getObject_id(), 1);
        }

        @Override
        public void onDeleteItem(GetMessageBean.MessageBean item, int position) {
            mLoadingDialog = LoadingDialog.show(mContext);
            MessageDataManage.getInstance().deleteMessage(mDeleteMessageBeanSimpleCallback, item.getObject_id());
            //Toast.makeText(mContext, "Delete:" + item.getContent(), Toast.LENGTH_SHORT).show();
        }
    };

    SimpleCallback<DeleteMessageBean> mDeleteMessageBeanSimpleCallback = new SimpleCallback<DeleteMessageBean>() {
        @Override
        public void onSuccess(DeleteMessageBean response) {
            hideLoading();
            if (response.get_status() == 0) {
                MyApplication.getInstance().showNote("删除成功");
                mCurrentCount--;
                EventBus.getDefault().post(new EventMessageCountUpdate(mCurrentCount));
                if (mMessageItemAdapter != null) {
                    mMessageItemAdapter.delete(response.getObject_id());
                }
            } else {
                MyApplication.getInstance().showNote(response.get_reason());
            }
        }

        @Override
        public void onFailure(int responseCode) {
            hideLoading();
            MyApplication.getInstance().showNote("删除失败，请稍侯重试");
        }
    };

    SimpleCallback<GetMessageBean> mGetMessageBeanSimpleCallback = new SimpleCallback<GetMessageBean>() {
        @Override
        public void onSuccess(GetMessageBean response) {
            mCurrentCount = 0;
            if (response != null && response.getObjects() != null) {
                mCurrentCount = response.getObjects().size();
            }
            EventBus.getDefault().post(new EventMessageCountUpdate(mCurrentCount));
            if (response.getObjects() != null && response.getObjects().size() > 0) {
                SharedPreferencesFactory.setLastestMessageID(mContext, response.getObjects().get(0).getObject_id());
                if (mLoadingType.equals(LoadingType.ERefresh)) {
                    mMessageItemAdapter.addData(response.getObjects(), true);
                } else {
                    mMessageItemAdapter.addData(response.getObjects(), false);
                }
            }
            mLoadingType = LoadingType.EIdel;
        }

        @Override
        public void onFailure(int responseCode) {
            mLoadingType = LoadingType.EIdel;
        }
    };

    private void refreshData() {
//        ArrayList<MessageBean> messageBeanArrayList = new ArrayList<>();
//        String[] urls = new String[]{"http://att.bbs.duowan.com/forum/201311/04/154122t2ba6lbajlp6z62d.jpg", "http://people.chu.edu.tw/~b9204125/images/people.jpg",
//                "http://ast.ainimei.cn/articles/10008242/img/85a55b8ec933954db0f5e55ee24fdd8f.jpg", "https://public.bn1304.livefilestore.com/y3pB9PZi_ze7s_s5WufoMs-fRFtGOgbqPTEpLJUhNeen88RSgtwXrjEiNe4Leyb8Q_8OrrH2LGmFuAJHQccumNLKizQhfEgSlb5JfIucetVXrHUYSDWbATHsj6IygHAQcEgIwRGCNYmhM3m5iArIHO_ag/KSCL-1394.jpg?rdrts=153296708",
//                "http://comic.qq.com/z/gang/gallery/image/adh2004031.jpg"};
//        for (int i = 0; i < 5; i++) {
//            MessageBean messageBean = new MessageBean();
//            messageBean.setId(i);
//            messageBean.setTitle("title tile " + i);
//            messageBeanArrayList.add(messageBean);
//        }
//        mMessageItemAdapter.addData(messageBeanArrayList, true);
        if (mLoadingType.equals(LoadingType.EIdel)) {
            mLoadingType = LoadingType.ERefresh;
            MessageDataManage.getInstance().getMyMessage(mGetMessageBeanSimpleCallback, 0);
        }
    }

    private void loadMore() {
        if (mLoadingType.equals(LoadingType.EIdel) && mMessageItemAdapter != null) {
            mLoadingType = LoadingType.ELoadMore;
            MessageDataManage.getInstance().getMyMessage(mGetMessageBeanSimpleCallback, mMessageItemAdapter.getItemCount());
        }
    }

    private void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }
}
