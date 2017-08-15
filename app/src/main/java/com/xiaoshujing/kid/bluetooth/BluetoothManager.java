package com.xiaoshujing.kid.bluetooth;

import android.os.Handler;
import android.os.Message;

import com.inuker.bluetooth.library.connect.listener.BleConnectStatusListener;
import com.inuker.bluetooth.library.connect.options.BleConnectOptions;
import com.inuker.bluetooth.library.connect.response.BleConnectResponse;
import com.inuker.bluetooth.library.connect.response.BleNotifyResponse;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.inuker.bluetooth.library.utils.ByteUtils;
import com.xiaoshujing.kid.api.SimpleCallback;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.BluetoothDataManage;
import com.xiaoshujing.kid.model.BodyBluetoothData;
import com.xiaoshujing.kid.model.PostBluetoothBean;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import static com.inuker.bluetooth.library.Constants.REQUEST_SUCCESS;
import static com.inuker.bluetooth.library.Constants.STATUS_CONNECTED;

/**
 * Created by mac on 17/1/4.
 */

public class BluetoothManager {

    public static final String TAG = "BluetoothManager";

    private Timer timer = null;
    private TimerTask timerTask = null;

    private volatile static BluetoothManager gInstance = null;

    private BluetoothManager(){

    }

    public static BluetoothManager getInstance() {
        if (gInstance == null) {
            synchronized (BluetoothDataManage.class) {
                if (gInstance == null) {
                    gInstance = new BluetoothManager();
                }
            }
        }
        return gInstance;
    }

    //上传蓝牙数据callback
    private SimpleCallback<PostBluetoothBean> mPostBluetoothBeanSimpleCallback = new SimpleCallback<PostBluetoothBean>() {
        @Override
        public void onSuccess(PostBluetoothBean postBluetoothBean) {

            Util.DLog(TAG, "Post Success!");

            if (postBluetoothBean != null && postBluetoothBean.get_status() == 0) {

                Util.DLog(TAG, postBluetoothBean.getDataSource());
            }
        }

        @Override
        public void onFailure(int responseCode) {

            Util.DLog(TAG, "Post Failed!");
            Util.DLog(TAG, String.valueOf(responseCode));
        }
    };

    //连接蓝牙
    public void connectDevice(final String address){

        BleConnectOptions options = new BleConnectOptions.Builder()
                .setConnectRetry(3)
                .setConnectTimeout(15000)
                .setServiceDiscoverRetry(3)
                .setServiceDiscoverTimeout(10000)
                .build();

        BluetoothClient.getBluetoothClient().connect(address, options, new BleConnectResponse() {
            @Override
            public void onResponse(int code, BleGattProfile data) {

                if(code == REQUEST_SUCCESS){

                    openNotify(address);
                }
            }
        });
    }

    //断开蓝牙
    public void disconnectDevice(String address){

        BluetoothClient.getBluetoothClient().disconnect(address);
    }

    //注册蓝牙监听
    public void registerConnectionListener(String address){

        BluetoothClient.getBluetoothClient().registerConnectStatusListener(address, new BleConnectStatusListener() {
            @Override
            public void onConnectStatusChanged(String mac, int status) {

                Util.DLog(TAG, "Bluetooth has" + (status == STATUS_CONNECTED ? "connected" : "disconnected"));
            }
        });
    }

    //注销蓝牙监听
    public void unregisterConnectionListener(String address){

        BluetoothClient.getBluetoothClient().unregisterConnectStatusListener(address, new BleConnectStatusListener() {
            @Override
            public void onConnectStatusChanged(String mac, int status) {

                Util.DLog(TAG, "Bluetooth has" + (status == STATUS_CONNECTED ? "connected" : "disconnected"));
            }
        });
    }

    //开始数据写入
    private void sendDataTimer(final String address, final int dataCategory, int sendCategory, long delay, long period){

        switch (sendCategory){

            default:
            break;

            case 0 : {

                final int what = 101;

                final Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case what:
                                sendData(address, dataCategory);
                                break;
                        }
                    }
                };

                timerTask = new TimerTask() {
                    @Override
                    public void run() {

                        Message message = new Message();
                        message.what = what;
                        handler.sendMessage(message);
                    }
                };

                timer = new Timer();
                timer.schedule(timerTask, delay, period);
            }
            break;

            case 1 : {

                sendData(address, dataCategory);
            }
            break;
        }
    }

    //暂停数据写入
    public void stopDataTimer(){

        timer.cancel();
        timerTask.cancel();
    }

    //写入数据
    private void sendData(String address, int dataCategory){

        String data = "";

        switch (dataCategory){

            default:
            break;

            /*
            * 请求获取光线强度*/
            case 0 : {

                data = "EECCE100000001FF";
            }
            break;

            /*
            * 请求获取坐姿状态*/
            case 1 : {

                data = "EECCE300000001FF";
            }
            break;

            /*
            * 通过手机请求暂停*/
            case 2 : {

                data = "EECCE400000003FF";
            }
            break;

            /*
            * 通过手机请求暂停（因坐姿异常）*/
            case 3 : {

                data = "EECCE400000004FF";
            }
            break;

            /*
            * 通过手机请求恢复*/
            case 4 : {

                data = "EECCE500000002FF";
            }
            break;

            /*
            * 通过手机请求恢复（自动机制）*/
            case 5 : {

                data = "EECCE500000003FF";
            }
            break;
        }

        BluetoothClient.getBluetoothClient().write(address,
                BluetoothClient.serviceId,
                BluetoothClient.characteristicId,
                ByteUtils.stringToBytes(data),
                mSendDataRsp);
    }


    //写入数据监听
    private final BleWriteResponse mSendDataRsp = new BleWriteResponse() {
        @Override
        public void onResponse(int code) {
            if (code == REQUEST_SUCCESS) {

                Util.DLog(TAG, "Data has been send!");
            } else {

                Util.DLog(TAG, "Data has not been send!");
            }
        }
    };

    //notify打开
    private void openNotify(String address){

        BluetoothClient.getBluetoothClient().notify(address,
                BluetoothClient.serviceId,
                BluetoothClient.characteristicId,
                new BleNotifyResponse() {
                    @Override
                    public void onNotify(UUID service, UUID character, byte[] value) {

                        if (service.equals(BluetoothClient.serviceId) && character.equals(BluetoothClient.characteristicId)) {

                            Util.DLog(TAG, "Notify Data:" + ByteUtils.byteToString(value));

                            String hexString = ByteUtils.byteToString(value);
                            String header = hexString.substring(0, 3);
                            String footer = hexString.substring(hexString.length() - 2);

                            if(hexString.length() == 16 |
                                    ("EECC").equals(header) &&
                                    ("FF").equals(footer)){

                                BluetoothDataManage.getInstance().postBluetoothData(mPostBluetoothBeanSimpleCallback, new BodyBluetoothData(hexString));

                            }else{

                                Util.DLog(TAG, "Notify Data:Error!");
                            }
                        }
                    }

                    @Override
                    public void onResponse(int code) {

                        if (code == REQUEST_SUCCESS) {

                            Util.DLog(TAG, "Notify Status:Open Success!");

                        } else {

                            Util.DLog(TAG, "Notify Status:Open Failed!");
                        }
                    }
                });
    }
}
