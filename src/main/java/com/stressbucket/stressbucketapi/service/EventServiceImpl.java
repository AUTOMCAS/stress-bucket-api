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
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private BucketRepository bucketRepository;

    @Override
    public Event createEvent(Integer bucketId, String stressType, String description, LocalDateTime dateTime, Integer stressLevelChange) throws BadReqestException {
        try {
            Bucket thisBucket = bucketRepository.findById(1);
            this.updateBucket(thisBucket, stressLevelChange);
            Integer updatedStressLevel = thisBucket.getStressLevel();
            Integer eventId = eventRepository.create(bucketId, stressType, description, dateTime, stressLevelChange, updatedStressLevel);
            return eventRepository.findById(eventId);
        } catch(Exception e) {
            throw new BadReqestException("Failed to create event. " + e.getMessage());
        }

    }

    @Override
    public List<Event> findAllEvents(Integer bucketId) throws ResourceNotfoundException {
        return eventRepository.findAll(bucketId);
    }

    private void updateBucket(Bucket bucket, Integer stressLevelChange) throws Exception{
        Integer updatedStressLevel = bucket.getStressLevel() + stressLevelChange;
        if (updatedStressLevel > 100) {
            throw new Exception("Resulting stress level exceeds 100");
        } else if (updatedStressLevel < 0) {
            throw new Exception("Resulting stress level less than 0");
        }else {
            bucket.setStressLevel(updatedStressLevel);
            bucketRepository.update(1, bucket);
        }
    }

}
