package com.stressbucket.stressbucketapi.repository;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.exceptions.ResourceNotfoundException;
import com.stressbucket.stressbucketapi.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository {
    Integer create(Integer userId, Integer bucketId, String stressType, String description, LocalDateTime dateTime, Integer stressChange, Integer resultingStressLevel) throws BadReqestException;

    Event findById(Integer userId, Integer bucketId, Integer eventId) throws ResourceNotfoundException;

    List<Event> findAll(Integer userId, Integer bucketId) throws ResourceNotfoundException;

    List<Event> findByStressType(Integer userId, Integer bucketId, String stressType) throws ResourceNotfoundException;
}
