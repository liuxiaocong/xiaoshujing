package com.xiaoshujing.kid.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.util.Log;

import java.io.IOException;

public class BitmapUtil {
    static final String TAG = "BitmapUtil";

    public static Bitmap cutOutCorrectPhoto(Context context, byte[] data, int expectWidth, int expectHeight, boolean isFrontCamera, int degree) {
        int _rotate = degree;
        Bitmap sourceBitmap = loadBitmap(data, Util.getPxFromDp(context, 720));
        if (sourceBitmap == null) {
            return null;
        }
        int pictureWidth = sourceBitmap.getWidth();
        int pictureHeight = sourceBitmap.getHeight();
        int x;
        int y;
        int height;
        int width;
        if (_rotate == 90 || _rotate == 270) {
            double scaleWidth = (double) pictureWidth / (double) expectHeight;
            double scaleHeight = (double) pictureHeight / (double) expectWidth;
            double targetProportion = (double) expectHeight / (double) expectWidth;
            if (scaleWidth > scaleHeight) {
                height = pictureHeight;
                width = (int) ((double) pictureHeight * targetProportion);
                y = 0;
                x = (pictureWidth - width) / 2;
            } else {
                width = pictureWidth;
                height = (int) ((double) pictureWidth / targetProportion);
                x = 0;
                y = (pictureHeight - height) / 2;
            }

            Log.d(TAG, "x = " + x + "; y = " + y + "; width = " + width + "; height = " + height +
                    "; pictureWidth = " + pictureWidth + "; pictureHeight = " + pictureHeight + "; containerWidth = " + expectWidth + "; containerHeight = " + expectHeight);
        } else {
            double scaleWidth = (double) pictureWidth / (double) expectWidth;
            double scaleHeight = (double) pictureHeight / (double) expectHeight;
            double targetProportion = (double) expectWidth / (double) expectHeight;
            if (scaleWidth > scaleHeight) {
                height = pictureHeight;
                width = (int) ((double) pictureHeight * targetProportion);
                y = 0;
                x = (pictureWidth - width) / 2;
            } else {
                width = pictureWidth;
                height = (int) ((double) pictureWidth / targetProportion);

                x = 0;
                y = (pictureHeight - height) / 2;
            }
            Log.d(TAG, "x = " + x + "; y = " + y + "; width = " + width + "; height = " + height +
                    "; pictureWidth = " + pictureWidth + "; pictureHeight = " + pictureHeight + "; containerWidth = " + expectWidth + "; containerHeight = " + expectHeight);
        }

        Matrix matrix;
        if (isFrontCamera) {
            matrix = new Matrix();
            matrix.setRotate(_rotate + 180);
        } else {
            if (_rotate != 0) {
                matrix = new Matrix();
                matrix.setRotate(_rotate);
            } else {
                matrix = null;
            }
        }
        Bitmap targetBitmap = Bitmap.createBitmap(sourceBitmap, x, y, width, height, matrix, false);
        return targetBitmap;
//        sourceBitmap.recycle();
//        return BitmapUtil.compressBitmap(targetBitmap, 100);
    }

    public static Bitmap loadBitmap(byte[] data, final int maxOutWidth) {
        if (data == null || maxOutWidth < 1) {
            return null;
        }
        Options opts = new Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, opts);
        int inSampleWidth = opts.outWidth / maxOutWidth;
        if (inSampleWidth < 1) {
            inSampleWidth = 1;
        }
        opts.inJustDecodeBounds = false;
        opts.inPurgeable = true;
        opts.inSampleSize = inSampleWidth;
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
        if (bmp != null && bmp.getWidth() <= maxOutWidth) {
            return bmp;
        }
        Bitmap bitmapTmp = bmp;
        bmp = createScaledBitmap(bitmapTmp, maxOutWidth, false);
        if (bitmapTmp != null) {
            bitmapTmp.recycle();
        }
        return bmp;
    }

    public static Bitmap createScaledBitmap(final Bitmap org, final int outWidth, boolean filter) {
        if (org == null || outWidth < 1)
            return null;
        final int outHeight = (int) ((double) org.getHeight() * (double) outWidth / (double) org.getWidth());
        return createScaledBitmap(org, outWidth, outHeight, filter);
    }

    private static Bitmap createScaledBitmap(final Bitmap org, final int outWidth, final int outHeight, boolean filter) {
        if (org == null || outWidth < 1 || outHeight < 1)
            return null;
        return Bitmap.createScaledBitmap(org, outWidth, outHeight, filter);
    }

    public static byte[] compressBitmap(final Bitmap bmp, final int quality) {
        return compressBitmap(bmp, quality, TFileContentType.EImage_jpeg);
    }

    public static byte[] compressBitmap(final Bitmap bmp, final int quality, TFileContentType fileContentType) {
        if (bmp == null)
            return null;

        byte[] buffer;
        BytesWriter bytesWriter = new BytesWriter();

        if (fileContentType == TFileContentType.EImage_png) {
            bmp.compress(Bitmap.CompressFormat.PNG, quality, bytesWriter);
        } else {
            bmp.compress(Bitmap.CompressFormat.JPEG, quality, bytesWriter);
        }

        buffer = bytesWriter.toByteArray();

        try {
            bytesWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer;
    }
}
