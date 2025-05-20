package org.example.boxes_task.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class FundraisingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private BigDecimal accountBalance;

    public FundraisingEvent() {}

    public FundraisingEvent(String name, Currency currency) {
        this.name = name;
        this.currency = currency;
        this.accountBalance = BigDecimal.ZERO;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

}
