package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.exceptions.ResourceNotfoundException;
import com.stressbucket.stressbucketapi.model.Bucket;
import com.stressbucket.stressbucketapi.model.Event;
import com.stressbucket.stressbucketapi.repository.BucketRepository;
import com.stressbucket.stressbucketapi.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    private final BucketRepository bucketRepository;
    @Autowired
    public EventServiceImpl(EventRepository eventRepository, BucketRepository bucketRepository) {
        this.eventRepository = eventRepository;
        this.bucketRepository = bucketRepository;
    }

    @Override
    public Event createEvent(Integer userId, Integer bucketId, String stressType, String description, LocalDateTime dateTime, Integer stressLevelChange) throws BadReqestException {
        try {
            Bucket bucket = bucketRepository.findById(userId, bucketId);
            this.updateBucketStressLevel(userId, bucket, stressLevelChange);
            Integer updatedStressLevel = bucket.getStressLevel();

            Integer eventId = eventRepository.create(userId, bucketId, stressType, description, dateTime, stressLevelChange, updatedStressLevel);
            return eventRepository.findById(userId, bucketId, eventId);
        } catch(Exception e) {
            throw new BadReqestException("Failed to create event. " + e.getMessage());
        }

    }

    @Override
    public List<Event> findAllEvents(Integer userId, Integer bucketId) throws ResourceNotfoundException {
        return eventRepository.findAll(userId, bucketId);
    }

    @Override
    public Event findEventById(Integer userId, Integer bucketId, Integer eventId) throws ResourceNotfoundException {
        return eventRepository.findById(userId, bucketId, eventId);
    }

    @Override
    public List<Event> findEventByStressType(Integer userId, Integer bucketId, String stressType) throws ResourceNotfoundException {
        return eventRepository.findByStressType(userId, bucketId, stressType);
    }

    private void updateBucketStressLevel(Integer userId, Bucket bucket, Integer stressLevelChange) throws Exception{
        Integer updatedStressLevel = bucket.getStressLevel() + stressLevelChange;
        if (updatedStressLevel > 100) {
            throw new Exception("Resulting stress level exceeds 100");
        } else if (updatedStressLevel < 0) {
            throw new Exception("Resulting stress level less than 0");
        } else {
            bucket.setStressLevel(updatedStressLevel);
            bucketRepository.update(userId, bucket.getBucketId(), bucket);
        }
    }

}
