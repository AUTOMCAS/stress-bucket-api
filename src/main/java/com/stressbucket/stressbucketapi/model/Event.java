package com.stressbucket.stressbucketapi.model;

import java.time.LocalDateTime;

public class Event {
    private Integer id;
    private Integer userId;
    private Integer bucketId;
    private String stressType;
    private String description;
    private LocalDateTime timeDate;
    private Integer stressLevelChange;
    private Integer resultingStressLevel;

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

    public Integer getBucketId() {
        return bucketId;
    }

    public void setBucketId(Integer bucketId) {
        this.bucketId = bucketId;
    }

    public String getStressType() {
        return stressType;
    }

    public void setStressType(String stressType) {
        this.stressType = stressType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(LocalDateTime timeDate) {
        this.timeDate = timeDate;
    }

    public Integer getStressLevelChange() {
        return stressLevelChange;
    }

    public void setStressLevelChange(Integer stressLevelChange) {
        this.stressLevelChange = stressLevelChange;
    }

    public Integer getResultingStressLevel() {
        return resultingStressLevel;
    }

    public void setResultingStressLevel(Integer resultingStressLevel) {
        this.resultingStressLevel = resultingStressLevel;
    }

    public Event(Integer id, Integer userId, Integer bucketId, String stressType, String description, LocalDateTime timeDate, Integer stressLevelChange, Integer resultingStressLevel) {
        this.id = id;
        this.userId = userId;
        this.bucketId = bucketId;
        this.stressType = stressType;
        this.description = description;
        this.timeDate = timeDate;
        this.stressLevelChange = stressLevelChange;
        this.resultingStressLevel = resultingStressLevel;
    }



}
