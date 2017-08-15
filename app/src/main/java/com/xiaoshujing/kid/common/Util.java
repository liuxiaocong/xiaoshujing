package com.xiaoshujing.kid.common;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.R;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by LiuXiaocong on 7/29/2016.
 */
public class Util {
    public static String SERVER = "http://59.110.23.25";

    static String language = null;
    public static final String EmptyStr = "";

    public static boolean isZhSetting() {
        if (language == null) {
            Locale l = Locale.getDefault();
            language = l.getLanguage();
            String country = l.getCountry().toLowerCase();
            if ("zh".equals(language)) {
                if ("cn".equals(country)) {
                    language = "zh-CN";
                } else if ("tw".equals(country)) {
                    language = "zh-TW";
                }
            }
        }
        if (language != null
                && (language.toLowerCase().trim().equals("zh-cn")))
            return true;
        else
            return false;
    }

    public static byte[] toBytes(String str) {
        return toBytes(str, "UTF-8");
    }

    private static byte[] toBytes(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return new byte[0];
        }
        try {
            return str.getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static int getPxFromDp(Context context, int dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) (dp * metrics.density);
    }

    public static int getPxFromDp(int dp) {
        DisplayMetrics metrics = MyApplication.getInstance().getResources().getDisplayMetrics();
        return (int) (dp * metrics.density);
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static void DLog(String tag, String content) {
        Log.d(tag, content);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }


    public static Bitmap doBlur(Bitmap sentBitmap, int radius) {
        Bitmap bitmap;
        bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }

    public static String getIntervalFormatTime(long time) {
        long hour = time / (60 * 60 * 1000);
        long minute = (time - hour * 60 * 60 * 1000) / (60 * 1000);
        long second = (time - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000;
        if (second >= 60) {
            second = second % 60;
            minute += second / 60;
        }
        if (minute >= 60) {
            minute = minute % 60;
            hour += minute / 60;
        }
        String sh;
        String sm;
        String ss;
        if (hour < 10) {
            sh = "0" + String.valueOf(hour);
        } else {
            sh = String.valueOf(hour);
        }
        if (minute < 10) {
            sm = "0" + String.valueOf(minute);
        } else {
            sm = String.valueOf(minute);
        }
        if (second < 10) {
            ss = "0" + String.valueOf(second);
        } else {
            ss = String.valueOf(second);
        }
        if (hour > 0) {
            return sh + ":" + sm + ":" + ss;
        } else if (minute > 0) {
            return sm + ":" + ss;
        } else {
            return sm + ":" + ss;
        }
    }

    public static void showMessageAlert(Context context, String content) {
        ShowAlert(context, "", content, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }, null, "确定", "");
    }

    public static void showMessageAlert(Context ctx, String title, String content, DialogInterface.OnClickListener onOK, boolean cancelAble) {
        KAlertDialogParam p = new KAlertDialogParam(ctx, title, content);
        p.setCancelAble(cancelAble);
        p.setOnOkClickListener(onOK);
        show(p);
    }

    public static void ShowAlert(Context ctx, String title, String content, DialogInterface.OnClickListener onOK, DialogInterface.OnClickListener onCancel, String ok, String cancel) {
        KAlertDialogParam p = new KAlertDialogParam(ctx, title, content, ok, cancel);
        p.setOnOkClickListener(onOK);
        p.setOnCancelClickListener(onCancel);
        show(p);
    }

    public static void ShowAlert(Context ctx, View view, DialogInterface.OnClickListener onOK, DialogInterface.OnClickListener onCancel, String ok, String cancel) {
        KAlertDialogParam p = new KAlertDialogParam(ctx, view, ok, cancel);
        p.setOnOkClickListener(onOK);
        p.setOnCancelClickListener(onCancel);
        show(p);
    }


    public static void ShowAlert(Context ctx, String title, String content, DialogInterface.OnClickListener onOK, DialogInterface.OnClickListener onCancel, String ok, String cancel, boolean cancelAble) {
        if (ctx instanceof Activity) {
            if (((Activity) ctx).isFinishing()) {
                return;
            }
        }
        KAlertDialogParam p = new KAlertDialogParam(ctx, title, content, ok, cancel);
        p.setOnOkClickListener(onOK);
        p.setOnCancelClickListener(onCancel);
        p.setCancelAble(cancelAble);
        show(p);
    }


    public static void show(final KAlertDialogParam p) {
        AlertDialog.Builder bd = new AlertDialog.Builder(p.fCtx, R.style.AlertDialog);
        bd.setCancelable(p.fCancelAble);
        View v = LayoutInflater.from(p.fCtx).inflate(R.layout.dialog_common, null);
        bd.setView(v);
        TextView contentView = (TextView) v.findViewById(R.id.content);
        TextView okView = (TextView) v.findViewById(R.id.ok);
        TextView cancelView = (TextView) v.findViewById(R.id.cancel);
        View close = v.findViewById(R.id.close);
        if (!Util.isNullOrEmpty(p.fButtonCancel)) {
            cancelView.setText(p.fButtonCancel);
            cancelView.setVisibility(View.VISIBLE);
        } else {
            cancelView.setVisibility(View.GONE);
        }

        if (!Util.isNullOrEmpty(p.fButtonOk)) {
            okView.setText(p.fButtonOk);
        } else {
            okView.setText("确定");
        }
        if (p.fCancelAble) {
            close.setVisibility(View.VISIBLE);
        } else {
            close.setVisibility(View.GONE);
        }
        if (!Util.isNullOrEmpty(p.fContent)) {
            contentView.setText(p.fContent);
            contentView.setVisibility(View.VISIBLE);
        } else {
            contentView.setVisibility(View.GONE);
        }
        final AlertDialog dialog = bd.create();
        dialog.setCanceledOnTouchOutside(p.fAbleToCancelOutside);
        if (p.fOnCancelListener != null) {
            cancelView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p.fOnCancelListener.onClick(dialog, v.getId());
                }
            });
        }
        if (p.fOnOkListener != null) {
            okView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p.fOnOkListener.onClick(dialog, v.getId());
                }
            });
        } else {
            okView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
        if (close.getVisibility() == View.VISIBLE) {
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (android.os.Build.VERSION.SDK_INT > 20) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } else {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
//        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
//        wmlp.gravity = Gravity.TOP;
//        wmlp.y = (int)(Configs.GetScreenHeight() * 0.2);
        dialog.show();
    }

    public static String getDate(long timespan) {
        Timestamp timestamp = new Timestamp((long) timespan * 1000);
        Date date = new Date(timestamp.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        return simpleDateFormat.format(date);
    }

    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
    public static void disappearWithAlpha(int duration, View view, int delay, Animator.AnimatorListener listener) {
        if (view == null)
            return;

        view.setVisibility(View.VISIBLE);
        view.setAlpha(1.0f);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", (float) 1.0, (float) 0.0);
        animator.setDuration(duration);
        animator.setStartDelay(delay);
        if (listener != null) {
            animator.addListener(listener);
        }
        animator.start();
    }

    public static void disappearWithAlpha(int duration, final View view, Animator.AnimatorListener listener) {
        if (view == null)
            return;

        view.setVisibility(View.VISIBLE);
        view.setAlpha(1.0f);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", (float) 1.0, (float) 0.0);
        animator.setDuration(duration);
        if (listener != null) {
            animator.addListener(listener);
        }
        animator.start();
    }

    public static void showWithAlpha(int duration, final View view, Animator.AnimatorListener listener) {
        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", (float) 0.0, (float) 1.0);
        animator.setDuration(duration);
        animator.setStartDelay(0);
        if (listener != null) {
            animator.addListener(listener);
        }
        animator.start();
    }
}
