package com.stressbucket.stressbucketapi.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stressbucket.stressbucketapi.model.Event;
import com.stressbucket.stressbucketapi.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    @Autowired
    private EventService eventService;

    @PostMapping("")
    public ResponseEntity<Event> createEvent(@RequestBody Map<String, Object> eventMap){
        Integer bucketId = (Integer) eventMap.get("bucketId");
        String stressType = (String) eventMap.get("stressType");
        String description = (String) eventMap.get("description");
        String dateTimeString = (String) eventMap.get("dateTime");
        Integer stressLevelChange = (Integer) eventMap.get("stressLevelChange");
        Integer resultingStressLevel = (Integer) eventMap.get("resultingStressLevel");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

        Event event = eventService.createEvent(bucketId, stressType, description, dateTime, stressLevelChange, resultingStressLevel);

        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @GetMapping("")
    public  ResponseEntity<List<Event>> findAllEvents(HttpServletRequest request) {
        Integer bucketId = 1;

        List<Event> events = eventService.findAllEvents(bucketId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
