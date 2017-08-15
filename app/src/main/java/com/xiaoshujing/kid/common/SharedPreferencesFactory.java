package com.xiaoshujing.kid.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by LiuXiaocong on 11/18/2016.
 */
public class SharedPreferencesFactory {
    private static final String KEY_DENIED_PERMISSION_ASK = "KEY_DENIED_PERMISSION_ASK";
    private static final String KEY_NEWS_DATA = "KEY_NEWS_DATA";
    private static final String KEY_CONFIG_DATA = "KEY_CONFIG_DATA";
    private static final String KEY_UUID = "KEY_UUID";
    private static final String KEY_USER_BEAN = "KEY_USER_BEAN";
    private static final String KEY_GLOBE_SETTING_BEAN = "KEY_GLOBE_SETTING_BEAN";
    private static final String KEY_YOUMENG_TOKEN = "KEY_YOUMENG_TOKEN";
    private static final String KEY_CURRENT_PRODUCTID = "KEY_CURRENT_PRODUCTID";
    private static final String KEY_BABY_NAME = "KEY_BABY_NAME";
    private static final String KEY_BABY_CREDIT = "KEY_BABY_CREDIT";
    private static final String KEY_LATEST_MESSAGE_ID = "KEY_LATEST_MESSAGE_ID";

    public static String getLastestMessageID(Context context) {
        return get(context, KEY_LATEST_MESSAGE_ID, "");
    }

    public static void setLastestMessageID(Context context, String messageId) {
        set(context, KEY_LATEST_MESSAGE_ID, messageId);
    }

    public static String getBabyCredit(Context context) {
        return get(context, KEY_BABY_CREDIT, "");
    }

    public static void setBabyCredit(Context context, String credit) {
        set(context, KEY_BABY_CREDIT, credit);
    }


    public static String getBabyName(Context context) {
        return get(context, KEY_BABY_NAME, "");
    }

    public static void setBabyName(Context context, String name) {
        set(context, KEY_BABY_NAME, name);
    }

    public static String getKeyCurrentProductId(Context context) {
        return get(context, KEY_CURRENT_PRODUCTID, "");
    }

    public static void setKeyCurrentProductId(Context context, String prductId) {
        set(context, KEY_CURRENT_PRODUCTID, prductId);
    }

    public static String getKeyYoumengToken(Context context) {
        return get(context, KEY_YOUMENG_TOKEN, "");
    }

    public static void setKeyYoumengToken(Context context, String token) {
        set(context, KEY_YOUMENG_TOKEN, token);
    }


    public static String getKeyGlobeSettingBean(Context context) {
        return get(context, KEY_GLOBE_SETTING_BEAN, "");
    }

    public static void setKeyGlobeSettingBean(Context context, String userbean) {
        set(context, KEY_GLOBE_SETTING_BEAN, userbean);
    }

    public static String getKeyUserBean(Context context) {
        return get(context, KEY_USER_BEAN, "");
    }

    public static void setKeyUserBean(Context context, String userbean) {
        set(context, KEY_USER_BEAN, userbean);
    }

    public static String getKeyUUID(Context context) {
        return get(context, KEY_UUID, "");
    }

    public static void setKeyUUID(Context context, String uuid) {
        set(context, KEY_UUID, uuid);
    }

    public static boolean getKeyDeniedPermissionAsk(Context context) {
        return get(context, KEY_DENIED_PERMISSION_ASK, false);
    }

    public static void setKeyDeniedPermissionAsk(Context context, boolean value) {
        set(context, KEY_DENIED_PERMISSION_ASK, value);
    }


    private static String get(Context mContext, String _key, String _defaultValue) {
        if (null != mContext) {
            SharedPreferences spf = mContext.getSharedPreferences(_key, Context.MODE_PRIVATE);
            if (null != spf) {
                return spf.getString(_key, _defaultValue);
            }
        }

        return _defaultValue;
    }

    private static void set(Context mContext, String _key, String _value) {
        if (null != mContext) {
            SharedPreferences.Editor dateEditor = mContext.getSharedPreferences(_key, Context.MODE_PRIVATE).edit();
            dateEditor.putString(_key, _value);
            dateEditor.apply();
        }
    }

    private static boolean get(Context mContext, String _key, boolean _defaultValue) {
        if (null != mContext) {
            SharedPreferences spf = mContext.getSharedPreferences(_key, Context.MODE_PRIVATE);
            if (null != spf) {
                return spf.getBoolean(_key, _defaultValue);
            }
        }

        return _defaultValue;
    }

    private static void set(Context mContext, String _key, boolean _value) {
        if (null != mContext) {
            SharedPreferences.Editor dateEditor = mContext.getSharedPreferences(_key, Context.MODE_PRIVATE).edit();
            dateEditor.putBoolean(_key, _value);
            dateEditor.apply();
        }
    }
}
