package com.xiaoshujing.kid.ui.seasonList;

import android.content.Context;

import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.data.SeasonVideoManage;
import com.xiaoshujing.kid.model.video.GetSeasonBean;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class SeasonListPresentImpl implements SeasonListContract.Presenter {
    Context mContext;
    SeasonListContract.View mView;
    SeasonItemAdapter mSeasonItemAdapter;
    int mSeasonType = SeasonVideoManage.SeasonType.EStudy.getValue();

    public SeasonListPresentImpl(Context context, int seasonType) {
        mContext = context;
        mSeasonType = seasonType;
    }


    @Override
    public void bindView(SeasonListContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mSeasonItemAdapter = new SeasonItemAdapter(mContext);
        if (mView != null) {
            mView.setAdapter(mSeasonItemAdapter);
        }
        loadData();
    }

    SimpleCallback<GetSeasonBean> mGetSeasonBeanSimpleCallback = new SimpleCallback<GetSeasonBean>() {
        @Override
        public void onSuccess(GetSeasonBean response) {
            GetSeasonBean getSeasonBean = response;
            if (getSeasonBean != null && getSeasonBean.getObjects() != null) {
                mSeasonItemAdapter.addData(getSeasonBean.getObjects(), true);
            }
        }

        @Override
        public void onFailure(int responseCode) {
            int ret = responseCode;
        }
    };

    private void loadData() {
        SeasonVideoManage.getInstance().getSeason(mGetSeasonBeanSimpleCallback, mSeasonType);

//        ArrayList<StudyItemBean> studyItemBeanArrayList = new ArrayList<>();
//        String[] urls = new String[]{"http://att.bbs.duowan.com/forum/201311/04/154122t2ba6lbajlp6z62d.jpg", "http://people.chu.edu.tw/~b9204125/images/people.jpg",
//                "http://ast.ainimei.cn/articles/10008242/img/85a55b8ec933954db0f5e55ee24fdd8f.jpg", "https://public.bn1304.livefilestore.com/y3pB9PZi_ze7s_s5WufoMs-fRFtGOgbqPTEpLJUhNeen88RSgtwXrjEiNe4Leyb8Q_8OrrH2LGmFuAJHQccumNLKizQhfEgSlb5JfIucetVXrHUYSDWbATHsj6IygHAQcEgIwRGCNYmhM3m5iArIHO_ag/KSCL-1394.jpg?rdrts=153296708",
//                "http://comic.qq.com/z/gang/gallery/image/adh2004031.jpg"};
//        for (int i = 0; i < 5; i++) {
//            StudyItemBean studyItemBean = new StudyItemBean();
//            studyItemBean.setId(i);
//            if (i == 2) {
//                studyItemBean.setFree(true);
//            }
//            studyItemBean.setTitle("看书的正确姿势" + i);
//            studyItemBean.setDescription("让孩子在正确的姿势下学习写字让孩子在正确的姿势下学习写字" + i);
//            studyItemBean.setCover(urls[i]);
//            studyItemBeanArrayList.add(studyItemBean);
//        }
//        mStudyItemAdapter.addData(studyItemBeanArrayList, true);
    }
}
