package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.exceptions.ResourceNotfoundException;
import com.stressbucket.stressbucketapi.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    Event createEvent(Integer bucketId, String stressType, String description, LocalDateTime timeDate, Integer stressLevelChange, Integer resultingStressLevel) throws BadReqestException;

    List<Event> findAllEvents(Integer bucketId) throws ResourceNotfoundException;
}
