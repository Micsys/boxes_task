package org.example.boxes_task.dto;

import org.example.boxes_task.entity.Currency;

public class CreateEventRequest {
    private String name;
    private Currency currency;

    public String getName() { return name; }
    public Currency getCurrency() { return currency; }
}
