package com.xiaoshujing.kid.data;

import com.xiaoshujing.kid.api.RetrofitManager;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.model.BodyBluetoothData;
import com.xiaoshujing.kid.model.PostBluetoothBean;

import retrofit2.Call;

/**
 * Created by mac on 17/1/3.
 */

public class BluetoothDataManage {

    private volatile static BluetoothDataManage gInstance = null;

    private BluetoothDataManage(){

    }

    public static BluetoothDataManage getInstance() {
        if (gInstance == null) {
            synchronized (BluetoothDataManage.class) {
                if (gInstance == null) {
                    gInstance = new BluetoothDataManage();
                }
            }
        }
        return gInstance;
    }

    public void postBluetoothData(SimpleCallback<PostBluetoothBean> simpleCallback, BodyBluetoothData bodyBluetoothData) {
        Call<PostBluetoothBean> call = RetrofitManager.getInstance().getApiService().postBluetooth(bodyBluetoothData);
        simpleCallback.enqueueCall(call);
    }
}
