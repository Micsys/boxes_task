package org.example.boxes_task.dto;

import org.example.boxes_task.entity.Currency;

import java.math.BigDecimal;

public class AddMoneyRequest {
    private Currency currency;
    private BigDecimal amount;

    public Currency getCurrency() { return currency; }
    public BigDecimal getAmount() { return amount; }
}
