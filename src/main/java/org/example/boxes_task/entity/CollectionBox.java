package org.example.boxes_task.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
public class CollectionBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean assigned;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<MoneyEntry> moneyContents = new ArrayList<>();

    @ManyToOne
    private FundraisingEvent assignedEvent;

    public CollectionBox() {}

    public boolean isEmpty() {
        return moneyContents.stream().allMatch(entry -> entry.getAmount().compareTo(BigDecimal.ZERO) <= 0);
    }


    public Long getId() {
        return id;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public List<MoneyEntry> getMoneyContents() {
        return moneyContents;
    }

    public void setMoneyContents(List<MoneyEntry> moneyContents) {
        this.moneyContents = moneyContents;
    }

    public FundraisingEvent getAssignedEvent() {
        return assignedEvent;
    }

    public void setAssignedEvent(FundraisingEvent assignedEvent) {
        this.assignedEvent = assignedEvent;
    }
}
