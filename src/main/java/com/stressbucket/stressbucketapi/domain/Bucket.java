package com.stressbucket.stressbucketapi.domain;

public class Bucket {
    private Integer bucketId;
    private String bucketName;
    private Integer stressLevel;

    public Bucket(Integer bucketId, String bucketName, Integer stressLevel) {
        this.bucketId = bucketId;
        this.bucketName = bucketName;
        this.stressLevel = stressLevel;
    }

    public Integer getBucketId() {
        return bucketId;
    }

    public void setBucketId(Integer bucketId) {
        this.bucketId = bucketId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public Integer getStressLevel() {
        return stressLevel;
    }

    public void setStressLevel(Integer stressLevel) {
        this.stressLevel = stressLevel;
    }
}
