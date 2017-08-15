package com.xiaoshujing.kid.ui.record;

import android.content.Context;

import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.data.MomentDataManage;
import com.xiaoshujing.kid.model.StudyMomentsBean;

/**
 * Created by LiuXiaocong on 11/8/2016.
 */
public class RecordPresentImpl implements RecordContract.Presenter {
    Context mContext;
    RecordContract.View mView;
    RecordItemAdapter mRecordItemAdapter;

    public RecordPresentImpl(Context context) {
        mContext = context;
    }


    @Override
    public void bindView(RecordContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mRecordItemAdapter = new RecordItemAdapter(mContext);
        if (mView != null) {
            mView.setAdapter(mRecordItemAdapter);
        }
        loadData();
    }

    private void loadData() {
//        ArrayList<DailyRecordItemBean> dailyRecordItemBeanArrayList = new ArrayList<>();
//        String[] urls = new String[]{"http://att.bbs.duowan.com/forum/201311/04/154122t2ba6lbajlp6z62d.jpg", "http://people.chu.edu.tw/~b9204125/images/people.jpg",
//                "http://ast.ainimei.cn/articles/10008242/img/85a55b8ec933954db0f5e55ee24fdd8f.jpg", "https://public.bn1304.livefilestore.com/y3pB9PZi_ze7s_s5WufoMs-fRFtGOgbqPTEpLJUhNeen88RSgtwXrjEiNe4Leyb8Q_8OrrH2LGmFuAJHQccumNLKizQhfEgSlb5JfIucetVXrHUYSDWbATHsj6IygHAQcEgIwRGCNYmhM3m5iArIHO_ag/KSCL-1394.jpg?rdrts=153296708",
//                "http://comic.qq.com/z/gang/gallery/image/adh2004031.jpg"};
//        for (int i = 0; i < 5; i++) {
//            DailyRecordItemBean dailyRecordItemBean = new DailyRecordItemBean();
//            dailyRecordItemBean.setTime(i);
//            ArrayList<DailyRecordItemBean.PhotosBean> photosBeanArrayList = new ArrayList<>();
//            for (int j = -2; j < i && j < 2; j++) {
//                DailyRecordItemBean.PhotosBean photosBean = new DailyRecordItemBean.PhotosBean();
//                photosBean.setId(j);
//                photosBean.setUrl(urls[j + 2]);
//                photosBeanArrayList.add(photosBean);
//            }
//            dailyRecordItemBean.setPhotos(photosBeanArrayList);
//            dailyRecordItemBeanArrayList.add(dailyRecordItemBean);
//        }
//        mRecordItemAdapter.addData(dailyRecordItemBeanArrayList, true);
        MomentDataManage.getInstance().getStudyMoments(mStudyMomentsBeanSimpleCallback);
    }

    SimpleCallback<StudyMomentsBean> mStudyMomentsBeanSimpleCallback = new SimpleCallback<StudyMomentsBean>() {
        @Override
        public void onSuccess(StudyMomentsBean response) {
            if (response != null && response.getObjects() != null) {
                mRecordItemAdapter.addData(response.getObjects(), true);
            }
        }

        @Override
        public void onFailure(int responseCode) {
            int ret = responseCode;
        }
    };
}
