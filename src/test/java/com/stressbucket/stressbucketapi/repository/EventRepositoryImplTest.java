package com.stressbucket.stressbucketapi.repository;

import com.stressbucket.stressbucketapi.model.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;


import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest

@TestPropertySource(properties = "spring.config.name=test-application")
class EventRepositoryImplTest {

    @Autowired
    EventRepositoryImpl eventRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Clock CLOCK = Clock.fixed(Instant.parse("2023-10-03T12:00:00.00Z"), ZoneId.of("GMT"));
    private final Event testEvent = new Event(1, 1,1, "Stress Type", "Description", LocalDateTime.now(CLOCK), 10, 60);

    @BeforeEach
    public void beforeEach(){
        jdbcTemplate.execute("INSERT INTO users (username, password) VALUES ('testuser', 'password');");
        jdbcTemplate.execute("INSERT INTO buckets (user_id, name, stress_level) VALUES (1, 'Test bucket', 50);");
    }

    @AfterEach
    public void afterEach() {
        jdbcTemplate.execute("TRUNCATE users RESTART IDENTITY CASCADE;");
    }


    @Test
//    @Sql("classpath:test-data.sql")
    void shouldCreateEvent() {
       Integer eventId = eventRepository.create(
               testEvent.getUserId(),
               testEvent.getBucketId(),
               testEvent.getStressType(),
               testEvent.getDescription(),
               testEvent.getDateTime(),
               testEvent.getStressLevelChange(),
               testEvent.getResultingStressLevel());

       assertNotNull(eventId);
    }

    @Test
    void shouldFindEventById() {
        Integer eventId = eventRepository.create(
                testEvent.getUserId(),
                testEvent.getBucketId(),
                testEvent.getStressType(),
                testEvent.getDescription(),
                testEvent.getDateTime(),
                testEvent.getStressLevelChange(),
                testEvent.getResultingStressLevel());

        Integer expectedEventId = 1;
        Event result = eventRepository.findById(testEvent.getUserId(), testEvent.getBucketId(), eventId);

        assertNotNull(result);
        assertThat(result.getEventId()).isEqualTo(expectedEventId);
        assertThat(result.getBucketId()).isEqualTo(testEvent.getUserId());
        assertThat(result.getBucketId()).isEqualTo(testEvent.getBucketId());
        assertThat(result.getStressType()).isEqualTo(testEvent.getStressType());
        assertThat(result.getDescription()).isEqualTo(testEvent.getDescription());
        assertThat(result.getDateTime()).isEqualTo(testEvent.getDateTime());
        assertThat(result.getStressLevelChange()).isEqualTo(testEvent.getStressLevelChange());
        assertThat(result.getResultingStressLevel()).isEqualTo(testEvent.getResultingStressLevel());
    }

    @Test
    void shouldFindAllEvents() {
        eventRepository.create(
                testEvent.getUserId(),
                testEvent.getBucketId(),
                testEvent.getStressType(),
                testEvent.getDescription(),
                testEvent.getDateTime(),
                testEvent.getStressLevelChange(),
                testEvent.getResultingStressLevel());
        eventRepository.create(
                testEvent.getUserId(),
                testEvent.getBucketId(),
                testEvent.getStressType(),
                testEvent.getDescription(),
                testEvent.getDateTime(),
                testEvent.getStressLevelChange(),
                testEvent.getResultingStressLevel());

        Integer userId = 1;
        Integer bucketId = 1;

        List<Event> events = eventRepository.findAll(userId, bucketId);

        assertNotNull(events);
        assertThat(events.size()).isEqualTo(2);
    }

    @Test
    void findByStressType() {
        eventRepository.create(
                testEvent.getUserId(),
                testEvent.getBucketId(),
                testEvent.getStressType(),
                testEvent.getDescription(),
                testEvent.getDateTime(),
                testEvent.getStressLevelChange(),
                testEvent.getResultingStressLevel());
        Integer userId = 1;
        Integer bucketId = 1;
        String stressType = testEvent.getStressType();

        List<Event> events = eventRepository.findByStressType(userId, bucketId, stressType);

        assertNotNull(events);
        assertThat(events.get(0).getStressType()).isEqualTo(stressType);
    }
}