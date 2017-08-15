/**
 * Copyright (c) 2011 Mozat Pte Ltd (Singapore)
 * http://www.mozat.com
 * <p/>
 * Project: Mozat
 */
package com.xiaoshujing.kid.common;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * @author liuxiaocong Created data: 11 18, 2011
 */

//use for target version > 23 androidM(6.0)
public class PermissionRequestUtil {
    private static final String TAG = "PermissionRequestUtil";
    public static final int REQUEST_MULTIPLE_PERMISSION = 0x8082;
    public static final int REQUEST_READ_PHONE_STATE_CODE = REQUEST_MULTIPLE_PERMISSION + 1;
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE_CODE = REQUEST_READ_PHONE_STATE_CODE + 1;
    public static final int REQUEST_GET_ACCOUNT_CODE = REQUEST_WRITE_EXTERNAL_STORAGE_CODE + 1;
    public static final int REQUEST_CAMERA_CODE = REQUEST_GET_ACCOUNT_CODE + 1;
    public static final int REQUEST_RECORD_AUDIO_CODE = REQUEST_CAMERA_CODE + 1;
    public static void requestMultiplePermission(Activity activity, String[] permissionArr) {
        ActivityCompat.requestPermissions(activity, permissionArr,
                REQUEST_MULTIPLE_PERMISSION);
    }


    public static boolean isReadPhoneStatePermissionActive(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public static void requestReadPhoneStatePermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE},
                REQUEST_READ_PHONE_STATE_CODE);
    }

    public static boolean isWriteExternalStoragePermissionActive(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public static void requestWriteExternalStoragePermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_WRITE_EXTERNAL_STORAGE_CODE);
    }

    public static boolean isGetAccountPermissionActive(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.GET_ACCOUNTS)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public static void requestGetAccountPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.GET_ACCOUNTS},
                REQUEST_GET_ACCOUNT_CODE);
    }

    public static boolean isCameraPermissionActive(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public static void requestCameraPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
                REQUEST_CAMERA_CODE);
    }
    public static boolean isRecordAudioPermissionActive(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public static void requestRecordAudioPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO},
                REQUEST_RECORD_AUDIO_CODE);
    }

}
