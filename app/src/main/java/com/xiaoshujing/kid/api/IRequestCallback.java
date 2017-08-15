package com.xiaoshujing.kid.api;


import com.xiaoshujing.kid.model.ErrorBean;

public interface IRequestCallback<T> {

    int UNKOWN_ERROR = -999;
    int TIME_OUT = -998;
    int PARSE_ERROR = -997;

    int BAD_REQUEST = 400;

    void onSuccess(T response);
    void onFailure(int responseCode);

    /**
     * @param response
     */
    void onBadRequest(ErrorBean response);
}
