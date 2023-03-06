package com.stressbucket.stressbucketapi.repository;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.exceptions.ResourceNotfoundException;
import com.stressbucket.stressbucketapi.model.Event;

public interface EventRepository {
    public Integer create(Integer bucketId, String stressType, String description, Long eventTimeDate, Integer stressChange, Integer resultingStressLevel) throws BadReqestException;

    public Event findById(Integer event_id) throws ResourceNotfoundException;
}
