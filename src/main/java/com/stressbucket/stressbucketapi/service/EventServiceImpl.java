package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.exceptions.BadReqestException;
import com.stressbucket.stressbucketapi.exceptions.ResourceNotfoundException;
import com.stressbucket.stressbucketapi.model.Event;
import com.stressbucket.stressbucketapi.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService{
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event createEvent(Integer bucketId, String stressType, String description, Long eventTimeDate, Integer stressLevelChange, Integer resultingStressLevel) throws BadReqestException {
        Integer eventId = eventRepository.create(bucketId, stressType, description, eventTimeDate, stressLevelChange, resultingStressLevel);

        return eventRepository.findById(eventId);
    }

    @Override
    public List<Event> findAllEvents(Integer bucketId) throws ResourceNotfoundException {
        return eventRepository.findAll(bucketId);
    }

}
