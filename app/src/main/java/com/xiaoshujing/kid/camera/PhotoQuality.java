package com.xiaoshujing.kid.camera;


public class PhotoQuality {
    private String mX264Profile = "superfast";
    private int mVideoWidth = 240;
    private int mVideoHeight = 320;
    private int mFrameRate = 30;

    public PhotoQuality(int videoWidth, int videoHeight, int frameRate) {
        mVideoWidth = videoWidth;
        mVideoHeight = videoHeight;
        mFrameRate = frameRate;
    }


    public int getVideoWidth() {
        return mVideoWidth;
    }

    public int getVideoHeight() {
        return mVideoHeight;
    }

    public int getFrameRate() {
        return mFrameRate;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PhotoQuality)) {
            return false;
        }

        PhotoQuality quality = (PhotoQuality) o;

        return (quality.mX264Profile.equals(this.mX264Profile) && quality.mVideoWidth == this.mVideoWidth && quality.mVideoHeight == this.mVideoHeight && quality.mFrameRate == this.mFrameRate);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + mVideoWidth;
        result = 31 * result + mVideoHeight;
        result = 31 * result + mFrameRate;
        result += mX264Profile.hashCode();
        return result;
    }

    @Override
    public PhotoQuality clone() {
        return new PhotoQuality(mVideoWidth, mVideoHeight, mFrameRate);
    }


    @Override
    public String toString() {
        return "x264Profile = " + mX264Profile
                + "videoWidth = " + mVideoWidth
                + "; videoHeight = " + mVideoHeight
                + "; frameRate = " + mFrameRate
                ;
    }
}
