package com.xiaoshujing.kid.data;

import com.xiaoshujing.kid.api.RetrofitManager;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.model.BodyUpdateMessageStatus;
import com.xiaoshujing.kid.model.message.DeleteMessageBean;
import com.xiaoshujing.kid.model.message.GetMessageBean;

import retrofit2.Call;

/**
 * Created by LiuXiaocong on 12/5/2016.
 */

public class MessageDataManage {
    private volatile static MessageDataManage gInstance = null;

    private MessageDataManage() {

    }

    public static MessageDataManage getInstance() {
        if (gInstance == null) {
            synchronized (MessageDataManage.class) {
                if (gInstance == null) {
                    gInstance = new MessageDataManage();
                }
            }
        }
        return gInstance;
    }


    public void getMyMessage(SimpleCallback<GetMessageBean> simpleCallback, int offset) {
        Call<GetMessageBean> call = RetrofitManager.getInstance().getApiService().getUserMessage(2, offset, 100);
        simpleCallback.enqueueCall(call);
    }

    public void getMyUnReadMessage(SimpleCallback<GetMessageBean> simpleCallback, int offset) {
        Call<GetMessageBean> call = RetrofitManager.getInstance().getApiService().getUserUnReadMessage(2, offset, 20);
        simpleCallback.enqueueCall(call);
    }

    public void updateMessage(SimpleCallback<GetMessageBean.MessageBean> simpleCallback, String mid,int isRead) {
        Call<GetMessageBean.MessageBean> call = RetrofitManager.getInstance().getApiService()
                .updateUserMessageReadStatus(mid, new BodyUpdateMessageStatus.Builder().isRead(isRead).build());
        simpleCallback.enqueueCall(call);
    }

    public void deleteMessage(SimpleCallback<DeleteMessageBean> simpleCallback, String messageId) {
        Call<DeleteMessageBean> call = RetrofitManager.getInstance().getApiService().deleteMessage(messageId);
        simpleCallback.enqueueCall(call);
    }

}
