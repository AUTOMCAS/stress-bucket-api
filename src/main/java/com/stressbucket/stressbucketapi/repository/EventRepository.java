package com.stressbucket.stressbucketapi.repository;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.exceptions.ResourceNotfoundException;
import com.stressbucket.stressbucketapi.model.Event;

import java.util.List;

public interface EventRepository {
    Integer create(Integer bucketId, String stressType, String description, Long eventTimeDate, Integer stressChange, Integer resultingStressLevel) throws BadReqestException;

    Event findById(Integer eventId) throws ResourceNotfoundException;

    List<Event> findAll(Integer bucketId) throws ResourceNotfoundException;
}
