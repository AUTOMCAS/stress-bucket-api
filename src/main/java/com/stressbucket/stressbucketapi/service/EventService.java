package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.model.Event;

public interface EventService {
    public Event createEvent(Integer bucketId, String stressType, String description, Long eventTimeDate, Integer stressLevelChange, Integer resultingStressLevel) throws BadReqestException;

}
