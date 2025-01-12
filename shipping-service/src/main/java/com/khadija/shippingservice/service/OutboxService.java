package com.khadija.shippingservice.service;

import com.khadija.shippingservice.model.Outbox;
import com.khadija.shippingservice.repository.OutboxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboxService {

    private static final Logger log = LoggerFactory.getLogger(OutboxService.class);
    private final OutboxRepository outboxRepository;

    public OutboxService(OutboxRepository outboxRepository) {
        this.outboxRepository = outboxRepository;
    }

    public void createOutbox(Outbox outbox) {
        outboxRepository.save(outbox);
    }

    public List<Outbox> findAll() {
        return outboxRepository.findAll();
    }

    public void deleteById(String id) {
    }
}
