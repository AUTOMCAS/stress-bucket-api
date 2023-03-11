package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.model.Bucket;
import com.stressbucket.stressbucketapi.model.Event;
import com.stressbucket.stressbucketapi.repository.BucketRepository;
import com.stressbucket.stressbucketapi.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    private EventServiceImpl eventServiceUnderTest;
    private AutoCloseable autoCloseable;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private BucketRepository bucketRepository;

    @BeforeEach
    void setUp(){
        eventServiceUnderTest = new EventServiceImpl(eventRepository, bucketRepository);
    }

    @Test
    void shouldInvokeCreateEvent() {
        Integer userId = 1;
        Integer bucketId = 1;
        String stressType = "Stress type";
        String description = "Stress description";
        LocalDateTime dateTime = LocalDateTime.now();
        Integer stressLevelChange = 10;
        Bucket bucket = new Bucket(bucketId,userId,"Test Bucket", 50);
        Event event = new Event(1,userId, bucketId,stressType, description, dateTime, stressLevelChange, 50);

        when(bucketRepository.findById(eq(userId), eq(bucketId))).thenReturn(bucket);
        when(eventRepository.create(eq(userId), eq(bucketId), eq(stressType), eq(description), eq(dateTime), eq(stressLevelChange), anyInt())).thenReturn(1);
        when(eventRepository.findById(eq(userId), eq(bucketId), eq(1))).thenReturn(event);

        Event result = eventServiceUnderTest.createEvent(userId, bucketId, stressType, description, dateTime, stressLevelChange);

        verify(bucketRepository, times(1)).update(eq(userId), eq(bucketId), any(Bucket.class));
        verify(eventRepository, times(1)).create(eq(userId), eq(bucketId), eq(stressType), eq(description), eq(dateTime), eq(stressLevelChange), anyInt());
        verify(eventRepository, times(1)).findById(eq(userId), eq(bucketId), eq(1));
    }


    @Test
    void shouldInvokeFindAllEvents() {
        eventServiceUnderTest.findAllEvents(1, 1);

        verify(eventRepository).findAll(1,1);
    }

    @Test
    void shouldInvokeFindEventById() {
        eventServiceUnderTest.findEventById(1, 1, 1);

        verify(eventRepository).findById(1,1, 1);
    }

    @Test
    void shouldInvokeFindEventByStressType() {
        eventServiceUnderTest.findEventByStressType(1, 1, "Stress Type");

        verify(eventRepository).findByStressType(1,1, "Stress Type");
    }

}