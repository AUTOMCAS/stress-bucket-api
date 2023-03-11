package com.stressbucket.stressbucketapi.service;

import com.stressbucket.stressbucketapi.repository.BucketRepository;
import com.stressbucket.stressbucketapi.repository.EventRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


class EventServiceImplTest {

    private EventServiceImpl eventServiceUnderTest;
    private AutoCloseable autoCloseable;
    @Mock
    private EventRepository eventRepository;
    private BucketRepository bucketRepository;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        eventServiceUnderTest = new EventServiceImpl(eventRepository, bucketRepository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void createEvent() {
    }

    @Test
    void findAllEvents() {
        eventServiceUnderTest.findAllEvents(1, 1);

        verify(eventRepository).findAll(1,1);
    }

    @Test
    void findEventById() {
    }

    @Test
    void findEventByStressType() {
    }
}