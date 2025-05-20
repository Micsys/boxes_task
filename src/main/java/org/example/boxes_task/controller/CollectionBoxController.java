package org.example.boxes_task.controller;

import org.example.boxes_task.dto.AddMoneyRequest;
import org.example.boxes_task.entity.CollectionBox;
import org.example.boxes_task.service.CollectionBoxService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boxes")
public class CollectionBoxController {

    private final CollectionBoxService service;

    public CollectionBoxController(CollectionBoxService service) {
        this.service = service;
    }

    @PostMapping
    public CollectionBox register() {
        return service.registerNewBox();
    }

    @GetMapping
    public List<CollectionBox> list() {
        return service.getAllBoxes();
    }

    @DeleteMapping("/{id}")
    public void unregister(@PathVariable Long id) {
        service.unregisterBox(id);
    }

    @PostMapping("/{id}/assign/{eventId}")
    public void assign(@PathVariable Long id, @PathVariable Long eventId) {
        service.assignToEvent(id, eventId);
    }

    @PostMapping("/{id}/money")
    public void addMoney(@PathVariable Long id, @RequestBody AddMoneyRequest req) {
        service.addMoney(id, req.getCurrency(), req.getAmount());
    }

    @PostMapping("/{id}/empty")
    public void empty(@PathVariable Long id) {
        service.emptyBox(id);
    }
}
