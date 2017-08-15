package com.xiaoshujing.kid.model;

/**
 * Created by LiuXiaocong on 12/7/2016.
 */

public class BodyPractiseUpdate {

    /**
     * sitScore : 98.2
     * practiceMinutes : 23
     */

    private double sitScore;
    private int practiceMinutes;

    private BodyPractiseUpdate(Builder builder) {
        setSitScore(builder.sitScore);
        setPracticeMinutes(builder.practiceMinutes);
    }


    public double getSitScore() {
        return sitScore;
    }

    public void setSitScore(double sitScore) {
        this.sitScore = sitScore;
    }

    public int getPracticeMinutes() {
        return practiceMinutes;
    }

    public void setPracticeMinutes(int practiceMinutes) {
        this.practiceMinutes = practiceMinutes;
    }

    public static final class Builder {
        private double sitScore;
        private int practiceMinutes;

        public Builder() {
        }

        public Builder sitScore(double val) {
            sitScore = val;
            return this;
        }

        public Builder practiceMinutes(int val) {
            practiceMinutes = val;
            return this;
        }

        public BodyPractiseUpdate build() {
            return new BodyPractiseUpdate(this);
        }
    }
}
