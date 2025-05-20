package org.example.boxes_task.service;

import org.example.boxes_task.entity.*;
import org.example.boxes_task.repository.CollectionBoxRepository;
import org.springframework.stereotype.Service;
import org.example.boxes_task.util.CurrencyConverter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class CollectionBoxService {

    private final CollectionBoxRepository repository;
    private final FundraisingEventService eventService;

    public CollectionBoxService(CollectionBoxRepository repository, FundraisingEventService eventService) {
        this.repository = repository;
        this.eventService = eventService;
    }

    public CollectionBox registerNewBox() {
        return repository.save(new CollectionBox());
    }

    public List<CollectionBox> getAllBoxes() {
        return repository.findAll();
    }

    public void assignToEvent(Long boxId, Long eventId) {
        CollectionBox box = getBox(boxId);
        if (!box.isEmpty()) throw new RuntimeException("Box must be empty");
        if (box.isAssigned()) throw new RuntimeException("Box already assigned");
        FundraisingEvent event = eventService.getEventById(eventId);
        box.setAssigned(true);
        box.setAssignedEvent(event);
        repository.save(box);
    }

    public void unregisterBox(Long boxId) {
        CollectionBox box = getBox(boxId);
        box.getMoneyContents().clear();
        repository.delete(box);
    }

    public void addMoney(Long boxId, Currency currency, BigDecimal amount) {
        CollectionBox box = getBox(boxId);
        Optional<MoneyEntry> existing = box.getMoneyContents().stream()
                .filter(e -> e.getCurrency().equals(currency))
                .findFirst();

        if (existing.isPresent()) {
            existing.get().setAmount(existing.get().getAmount().add(amount));
        } else {
            box.getMoneyContents().add(new MoneyEntry(currency, amount));
        }
        repository.save(box);
    }

    public void emptyBox(Long boxId) {
        CollectionBox box = getBox(boxId);
        if (!box.isAssigned()) throw new RuntimeException("Box not assigned");

        FundraisingEvent event = box.getAssignedEvent();
        Currency eventCurrency = event.getCurrency();
        BigDecimal totalConverted = box.getMoneyContents().stream()
                .map(e -> CurrencyConverter.convert(e.getCurrency(), eventCurrency, e.getAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        eventService.addToEventAccount(event, totalConverted);
        box.getMoneyContents().clear();
        repository.save(box);
    }

    public CollectionBox getBox(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Box not found"));
    }
}
