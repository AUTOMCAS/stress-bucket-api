package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.exceptions.ResourceNotfoundException;
import com.stressbucket.stressbucketapi.model.Event;

import java.util.List;

public interface EventService {
    Event createEvent(Integer bucketId, String stressType, String description, Long eventTimeDate, Integer stressLevelChange, Integer resultingStressLevel) throws BadReqestException;

    List<Event> findAllEvents(Integer bucketId) throws ResourceNotfoundException;
}
