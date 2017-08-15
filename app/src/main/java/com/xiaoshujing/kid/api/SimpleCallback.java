package com.xiaoshujing.kid.api;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.model.ErrorBean;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LiuXiaocong on 10/24/2016.
 */
public abstract class SimpleCallback<T> implements IRequestCallback<T> {
    private Call request;

    public SimpleCallback enqueueCall(Call<T> call) {
        this.request = call;
        request.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                handleResponse(call, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                handleFailure(call, t);
            }
        });

        return this;
    }

    public void cancel() {
        if (request != null && !request.isCanceled()) {
            request.cancel();
        }
    }

    public void handleResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(response.body());
        } else if (response.code() == BAD_REQUEST) {
            try {
                onBadRequest(GsonImpl.get().toObject(response.errorBody().string(), ErrorBean.class));
            } catch (IOException e) {
                e.printStackTrace();
                onFailure(PARSE_ERROR);
            }
        } else {
            onFailure(response.code());
        }
    }

    public void handleFailure(Call<T> call, Throwable t) {
        if (t instanceof SocketTimeoutException) {
            onFailure(TIME_OUT);
        } else {
            onFailure(UNKOWN_ERROR);
        }
    }

    @Override
    public void onBadRequest(ErrorBean msg) {
        if (msg != null && !Util.isNullOrEmpty(msg.getError())) {
            MyApplication.getInstance().showNote(msg.getError());
        }
    }
}
