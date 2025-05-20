package org.example.boxes_task.service;

import org.example.boxes_task.entity.FundraisingEvent;
import org.example.boxes_task.entity.Currency;
import org.example.boxes_task.dto.FinancialReportEntry;
import org.example.boxes_task.repository.FundraisingEventRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FundraisingEventService {

    private final FundraisingEventRepository repository;

    public FundraisingEventService(FundraisingEventRepository repository) {
        this.repository = repository;
    }

    public FundraisingEvent createEvent(String name, Currency currency) {
        FundraisingEvent event = new FundraisingEvent(name, currency);
        return repository.save(event);
    }

    public List<FundraisingEvent> getAllEvents() {
        return repository.findAll();
    }

    public void addToEventAccount(FundraisingEvent event, BigDecimal amount) {
        event.setAccountBalance(event.getAccountBalance().add(amount));
        repository.save(event);
    }

    public FundraisingEvent getEventById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public List<FinancialReportEntry> getFinancialReport() {
        return repository.findAll().stream()
                .map(event -> new FinancialReportEntry(
                        event.getName(),
                        event.getAccountBalance(),
                        event.getCurrency()
                ))
                .collect(Collectors.toList());
    }
}
