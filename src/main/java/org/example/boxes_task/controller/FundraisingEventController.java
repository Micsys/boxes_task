package org.example.boxes_task.controller;

import org.example.boxes_task.dto.CreateEventRequest;
import org.example.boxes_task.entity.FundraisingEvent;
import org.example.boxes_task.service.FundraisingEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class FundraisingEventController {

    private final FundraisingEventService service;

    public FundraisingEventController(FundraisingEventService service) {
        this.service = service;
    }

    @PostMapping
    public FundraisingEvent create(@RequestBody CreateEventRequest req) {
        return service.createEvent(req.getName(), req.getCurrency());
    }

    @GetMapping
    public List<FundraisingEvent> list() {
        return service.getAllEvents();
    }
}
