package com.stressbucket.stressbucketapi.model;

public class Bucket {
    private Integer id;
    private Integer userId;
    private String name;
    private Integer stressLevel;

    public Bucket(Integer id, Integer userId, String name, Integer stressLevel) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.stressLevel = stressLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
