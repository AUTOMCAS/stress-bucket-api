package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.exceptions.ResourceNotfoundException;
import com.stressbucket.stressbucketapi.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    Event createEvent(Integer userId, Integer bucketId, String stressType, String description, LocalDateTime timeDate, Integer stressLevelChange) throws BadReqestException;

    List<Event> findAllEvents(Integer userId, Integer bucketId) throws ResourceNotfoundException;

    Event findEventById(Integer userId, Integer bucketId, Integer eventId) throws ResourceNotfoundException;

    List<Event> findEventByStressType(Integer userId, Integer bucketId, String stressType) throws ResourceNotfoundException;
}
