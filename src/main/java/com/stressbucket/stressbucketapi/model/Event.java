package com.stressbucket.stressbucketapi.model;

public class Event {
    private Integer eventId;
    private Integer bucketId;
    private String stressType;
    private String description;
    private Long eventTimeDate;
    private Integer stressLevelChange;
    private Integer resultingStressLevel;

    public Event(Integer eventId, Integer bucketId, String stressType, String description, Long eventTimeDate, Integer stressLevelChange, Integer resultingStressLevel) {
        this.eventId = eventId;
        this.bucketId = bucketId;
        this.stressType = stressType;
        this.description = description;
        this.eventTimeDate = eventTimeDate;
        this.stressLevelChange = stressLevelChange;
        this.resultingStressLevel = resultingStressLevel;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
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

    public Long getEventTimeDate() {
        return eventTimeDate;
    }

    public void setEventTimeDate(Long eventTimeDate) {
        this.eventTimeDate = eventTimeDate;
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



}
