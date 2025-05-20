package org.example.boxes_task.dto;

import org.example.boxes_task.entity.Currency;

import java.math.BigDecimal;

public class FinancialReportEntry {
    private String name;
    private BigDecimal amount;
    private Currency currency;

    public FinancialReportEntry(String name, BigDecimal amount, Currency currency) {
        this.name = name;
        this.amount = amount;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
