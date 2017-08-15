package com.xiaoshujing.kid.common;

/**
 * Created by LiuXiaocong on 11/15/2016.
 */
public enum TFileContentType {
    EUnknown(-1), EImage_jpeg(0), EImage_png(1), EImage_bmp(2), EApplication_pdf(3), EText_html(4), EVideo_mp4(5), EVideo_3gp(6), EAudio_amr(7), EMozat_chat(8), EMozat_avatar(9), EMozat_dejasmiley(
            10), EMozat_http_data(11), EMozat_log(12), EMozat_gif(13);

    private final int mIntValue;

    TFileContentType(int intValue) {
        mIntValue = intValue;
    }

    int getIntValue() {
        return mIntValue;
    }

    public String toFileContentTypeStr() {
        switch (this) {
            case EImage_jpeg:
                return "image/jpeg";
            case EImage_png:
                return "image/png";
            case EImage_bmp:
                return "image/bmp";
            case EApplication_pdf:
                return "application/pdf";
            case EText_html:
                return "text/html";
            case EVideo_mp4:
                return "video/mp4";
            case EVideo_3gp:
                return "video/3gpp";
            case EAudio_amr:
                return "audio/amr";
            case EMozat_chat:
                return "mozat/chat";
            case EMozat_avatar:
                return "mozat/avatar";
            case EMozat_dejasmiley:
                return "mozat/dejasmiley";
            case EMozat_http_data:
                return "mozat/httpdata";
            case EMozat_log:
                return "mozat/log";
            case EMozat_gif:
                return "mozat/gif";
            case EUnknown:
            default:
                return Util.EmptyStr;
        }
    }

    public String toFileSuffixStr() {
        switch (this) {
            case EImage_jpeg:
                return ".jpg";
            case EImage_png:
                return ".png";
            case EImage_bmp:
                return ".bmp";
            case EApplication_pdf:
                return ".pdf";
            case EText_html:
                return ".htm";
            case EVideo_mp4:
                return ".mp4";
            case EVideo_3gp:
                return ".3gp";
            case EAudio_amr:
                return ".amr";
            case EMozat_chat:
                return ".chh";
            case EMozat_avatar:
                return ".avt";
            case EMozat_dejasmiley:
                return ".ds";
            case EMozat_http_data:
                return ".bin";
            case EMozat_log:
                return ".log";
            case EMozat_gif:
                return ".mozatgif";
            case EUnknown:
            default:
                return Util.EmptyStr;
        }
    }

    public static TFileContentType parseFileContentTypeStr(String strValue) {
        if (!Util.isNullOrEmpty(strValue)) {
            for (TFileContentType value : TFileContentType.values()) {
                if (strValue.equals(value.toFileContentTypeStr())) {
                    return value;
                }
            }
        }

        return EUnknown;
    }

    public static TFileContentType parseFileSuffix(String strValue) {
        if (!Util.isNullOrEmpty(strValue)) {
            for (TFileContentType value : TFileContentType.values()) {
                if (strValue.equals(value.toFileSuffixStr())) {
                    return value;
                }
            }
        }

        return EUnknown;
    }
}