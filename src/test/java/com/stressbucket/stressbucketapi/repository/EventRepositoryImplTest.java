package com.stressbucket.stressbucketapi.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest

@TestPropertySource(properties = "spring.config.name=test-application")
class EventRepositoryImplTest {

    @Autowired
    EventRepositoryImpl eventRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        Integer userId = 1;
        Integer bucketId = 1;
        String stressType = "stressType";
        String description = "description";
        LocalDateTime dateTime = LocalDateTime.now();
        Integer stressLevelChange = 10;
        Integer resultingStressLevel = 60;

       Integer eventId = eventRepository.create(userId, bucketId, stressType, description, dateTime,stressLevelChange, resultingStressLevel);

       assertNotNull(eventId);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByStressType() {
    }
}