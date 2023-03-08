package com.stressbucket.stressbucketapi.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stressbucket.stressbucketapi.model.Event;
import com.stressbucket.stressbucketapi.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/buckets/{bucketId}/events")
public class EventController {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    @Autowired
    private EventService eventService;

    @PostMapping("")
    public ResponseEntity<Event> createEvent(@RequestBody Map<String, Object> eventMap){
        Integer userId = (Integer) eventMap.get("userId");
        Integer bucketId = (Integer) eventMap.get("bucketId");
        String stressType = (String) eventMap.get("stressType");
        String description = (String) eventMap.get("description");
        String dateTimeString = (String) eventMap.get("dateTime");
        Integer stressLevelChange = (Integer) eventMap.get("stressLevelChange");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

        Event event = eventService.createEvent(userId, bucketId, stressType, description, dateTime, stressLevelChange);

        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @GetMapping("")
    public  ResponseEntity<List<Event>> findAllEvents(HttpServletRequest request, @PathVariable("bucketId") Integer bucketId) {
        Integer userId = 1;

        List<Event> events = eventService.findAllEvents(userId, bucketId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> findEventById(HttpServletRequest request, @PathVariable("bucketId") Integer bucketId, @PathVariable("eventId") Integer eventId) {
        Integer userId = 1;

        Event event = eventService.findEventById(userId, bucketId, eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
}
