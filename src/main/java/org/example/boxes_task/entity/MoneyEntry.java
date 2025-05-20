package org.example.boxes_task.entity;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class MoneyEntry {
    private Currency currency;
    private BigDecimal amount;

    public MoneyEntry() {}

    public MoneyEntry(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() { return currency; }
    public void setCurrency(Currency currency) { this.currency = currency; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoneyEntry)) return false;
        MoneyEntry that = (MoneyEntry) o;
        return currency == that.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency);
    }
}
