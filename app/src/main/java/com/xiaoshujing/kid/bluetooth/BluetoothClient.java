package com.xiaoshujing.kid.bluetooth;

import com.xiaoshujing.kid.MyApplication;

import java.util.UUID;

/**
 * Created by mac on 17/1/3.
 */

class BluetoothClient {

    //单例
    private static com.inuker.bluetooth.library.BluetoothClient bluetoothClient;

    static UUID serviceId = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
    static UUID characteristicId = UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb");

    static com.inuker.bluetooth.library.BluetoothClient getBluetoothClient() {

        if (bluetoothClient == null) {

            synchronized (BluetoothClient.class) {

                if (bluetoothClient == null) {

                    bluetoothClient = new com.inuker.bluetooth.library.BluetoothClient(MyApplication.getInstance());
                }
            }
        }

        return bluetoothClient;
    }
}
