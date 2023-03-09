package com.stressbucket.stressbucketapi.model;

public class Bucket {
    private Integer bucketId;
    private Integer userId;
    private String name;
    private Integer stressLevel;

    public Bucket(Integer bucketId, Integer userId, String name, Integer stressLevel) {
        this.bucketId = bucketId;
        this.userId = userId;
        this.name = name;
        this.stressLevel = stressLevel;
    }

    public Integer getBucketId() {
        return bucketId;
    }

    public void setBucketId(Integer bucketId) {
        this.bucketId = bucketId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStressLevel() {
        return stressLevel;
    }

    public void setStressLevel(Integer stressLevel) {
        this.stressLevel = stressLevel;
    }

}
