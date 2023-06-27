package com.beyondtrust.eventforwarder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody EventDTO eventDTO) {
        Event createdEvent = eventService.createEvent(eventDTO);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    // Outros métodos do controlador...
}
