package com.stressbucket.stressbucketapi.controller;

import com.stressbucket.stressbucketapi.model.Event;
import com.stressbucket.stressbucketapi.service.EventService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("")
    public ResponseEntity<Event> createEvent(@RequestBody Map<String, Object> eventMap) {
        Integer bucketId = (Integer) eventMap.get("bucketId");
        String stressType = (String) eventMap.get("stressType");
        String description = (String) eventMap.get("description");
        Long eventTimeDate = (Long) eventMap.get("eventTimeDate");
        Integer stressLevelChange = (Integer) eventMap.get("stressLevelChange");
        Integer resultingStressLevel = (Integer) eventMap.get("resultingStressLevel");

        Event event = eventService.createEvent(bucketId, stressType, description, eventTimeDate, stressLevelChange, resultingStressLevel);

        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @GetMapping("")
    public  ResponseEntity<List<Event>> findAllEvents(HttpServletRequest request) {
        Integer bucketId = 1;

        List<Event> events = eventService.findAllEvents(bucketId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
