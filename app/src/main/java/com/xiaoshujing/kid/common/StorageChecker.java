package com.xiaoshujing.kid.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;

import com.xiaoshujing.kid.MyApplication;

import java.io.File;


public class StorageChecker {

    private static final String TAG = "[StorageChecker]";

    private static StorageChecker _ins;

    static volatile boolean gHasSDCard;
    private String mExternalCachePath;
    private final String mSystemCachePath;

    private String mExternalPath;
    private final String mSystemPath;


	public static synchronized StorageChecker getInstance() {
		if (_ins == null) {
			_ins = (new StorageChecker());
		}

		return _ins;
	}

    private StorageChecker() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        intentFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
        intentFilter.addDataScheme("file");
        MyApplication.getInstance().registerReceiver(sdCardReceiver, intentFilter);

        gHasSDCard = isHasSDCard();

        mSystemCachePath = (MyApplication.getInstance().getCacheDir().getAbsolutePath() + File.separatorChar);
        File file = new File(mSystemCachePath);
        //noinspection ResultOfMethodCallIgnored
        file.mkdirs();

        mSystemPath = MyApplication.getInstance().getApplicationContext().getDir(MyApplication.getInstance().GetAppName(), Context.MODE_PRIVATE).getAbsolutePath() + File.separator;
        file = new File(mSystemPath);
        //noinspection ResultOfMethodCallIgnored
        file.mkdirs();

        if (gHasSDCard) {
            initExternalCachePath();
        }
    }

    @SuppressWarnings("FieldCanBeLocal")
    private final BroadcastReceiver sdCardReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String name = intent.getAction();

            if (Intent.ACTION_MEDIA_UNMOUNTED.equals(name)) {
                gHasSDCard = false;

            } else if (Intent.ACTION_MEDIA_MOUNTED.equals(name)) {
                try {
                    initExternalCachePath();

                    gHasSDCard = true;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private void initExternalCachePath() {
        //noinspection ConstantConditions
        mExternalCachePath = (MyApplication.getInstance().getExternalCacheDir().getAbsolutePath() + File.separatorChar);
        File file = new File(mExternalCachePath);
        //noinspection ResultOfMethodCallIgnored
        file.mkdirs();

        mExternalPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + MyApplication.getInstance().GetAppName() + File.separatorChar;
        file = new File(mExternalPath);
        //noinspection ResultOfMethodCallIgnored
        file.mkdirs();
    }

    private boolean isHasSDCard() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File fileExternalStorageDirectory = Environment.getExternalStorageDirectory();
            File fileExternalCacheDir = MyApplication.getInstance().getExternalCacheDir();
            if (null == fileExternalStorageDirectory || fileExternalCacheDir == null) {
                return false;
            }

            if (fileExternalStorageDirectory.exists() && fileExternalCacheDir.exists()) {
                return !Util.isNullOrEmpty(fileExternalStorageDirectory.getAbsolutePath()) && !Util.isNullOrEmpty(fileExternalCacheDir.getAbsolutePath());
            }
        }
        return false;
    }

    public static boolean hasExternalStorage() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public String getExternalCachePath() {
        return mExternalCachePath;
    }

    public String getSystemCachePath() {
        return mSystemCachePath;
    }

    public String getExternalPath() {
        return mExternalPath;
    }

    public String getSystemPath() {
        return mSystemPath;
    }

    /**
     * folder to store data. End with "/"
     *
     * @return path
     */
    public String getOutFolder() {
        return gHasSDCard ? mExternalPath : mSystemPath;
    }

    /**
     * folder to store data. End with "/"
     *
     * @return path
     */
    public String getOutCacheFolder() {
        return gHasSDCard ? mExternalCachePath : mSystemCachePath;
    }
}
