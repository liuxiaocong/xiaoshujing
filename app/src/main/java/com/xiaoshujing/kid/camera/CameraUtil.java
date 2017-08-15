package com.xiaoshujing.kid.camera;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import android.view.Surface;

public abstract class CameraUtil {
    private static final String TAG = "CameraUtil";

    public static int getCameraDisplayOrientation(Activity activity, @SuppressWarnings("deprecation") Camera.CameraInfo cameraInfo) {
        int degrees = getCameraDisplayOrientation_common(activity, cameraInfo);
        String MANUFACTURER = Build.MANUFACTURER;
        int GINGERBREAD_MR1 = Build.VERSION_CODES.GINGERBREAD_MR1;
        String MODEL = Build.MODEL;
        String RELEASE = Build.VERSION.RELEASE;
        Log.d(TAG, "MANUFACTURER = " + MANUFACTURER + "; GINGERBREAD_MR1 = " + GINGERBREAD_MR1 + "; MODEL = " + MODEL + "; RELEASE = " + RELEASE + "; Degrees =" + degrees);
        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            if ((MANUFACTURER.toLowerCase().equals("HTC".toLowerCase()) && MODEL.toLowerCase().equals("HTC Salsa C510e".toLowerCase())
            ) || (MANUFACTURER.toLowerCase().equals("samsung".toLowerCase()) && MODEL.toLowerCase().equals("Galaxy Y Duos".toLowerCase())
            ) || (MANUFACTURER.toLowerCase().equals("LGE".toLowerCase()) && MODEL.toLowerCase().equals("LG-P500".toLowerCase())
            )) {
                degrees = (degrees + 90 + 360) % 360;
            }
        } else if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
            if ((MANUFACTURER.toLowerCase().equals("samsung".toLowerCase()) && MODEL.toLowerCase().equals("Galaxy Y".toLowerCase())
            )
                    || (MANUFACTURER.toLowerCase().equals("samsung".toLowerCase()) && MODEL.toLowerCase().equals("Galaxy Y Duos".toLowerCase())
            ) || (MANUFACTURER.toLowerCase().equals("LGE".toLowerCase()) && MODEL.toLowerCase().equals("LG-P500".toLowerCase())
            ) || (MANUFACTURER.toLowerCase().equals("samsung".toLowerCase()) && MODEL.toLowerCase().equals("Galaxy Young Pro".toLowerCase())
            ) || (MANUFACTURER.toLowerCase().equals("samsung".toLowerCase()) && MODEL.toLowerCase().equals("GT-S5360".toLowerCase())
            ) || (MANUFACTURER.toLowerCase().equals("samsung".toLowerCase()) && MODEL.toLowerCase().equals("Galaxy Ace 2".toLowerCase())
            )) {
                degrees = (degrees + 90 + 360) % 360;
            }
        }

        Log.d(TAG, "After Degrees =" + degrees);
        return degrees;
    }

    private static int getCameraDisplayOrientation_common(Activity activity, @SuppressWarnings("deprecation") Camera.CameraInfo cameraInfo) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
            default: {
                Log.e(TAG, "un deal with handler task");
            }
            break;
        }

        int result;
        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (cameraInfo.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (cameraInfo.orientation - degrees + 360) % 360;
        }
        return result;
    }
}
