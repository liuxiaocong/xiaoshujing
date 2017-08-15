package com.xiaoshujing.kid.common;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.xiaoshujing.kid.MyApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class LocalImage {
    public static boolean isSDCardMounted() {
        // return Environment.getExternalStorageState().equals("mounted");
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    public static String getAppMediaDir() {
        String appDir = StorageChecker.getInstance().getOutFolder();

        File file = new File(appDir);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return appDir;
            } else {
                return Util.EmptyStr;
            }
        }

        return appDir;
    }

    public static String getSaveFolder() {
        return getAppMediaDir() + File.separatorChar;
//        if (isSDCardMounted()) {
//            return getAppMediaDir() + File.separatorChar + "xiaoshujing" + File.separatorChar;
//        } else {
//            return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + "xiaoshujing" + File.separatorChar;
//        }
    }

    public static String getNewWriteDataFolder() {
        String folder = getSaveFolder();
        new File(folder).mkdirs();
        return folder;
    }

    public static File saveDataToFile(byte[] data, String fileName) {
        if (data != null && data.length > 0 && !Util.isNullOrEmpty(fileName)) {
            File vFile = new File(getNewWriteDataFolder(), fileName);
            //noinspection TryWithIdenticalCatches
            try {
                FileOutputStream outputStream = new FileOutputStream(vFile);
                outputStream.write(data);
                outputStream.close();
                return vFile;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 把文件插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(MyApplication.getInstance().getContentResolver(),
                        vFile.getAbsolutePath(), fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 通知图库更新
            MyApplication.getInstance().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(vFile)));

        }

        return null;
    }
}
