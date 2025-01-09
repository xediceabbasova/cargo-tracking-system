package com.khadija.shippingservice.repository;

import com.khadija.shippingservice.model.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxRepository extends JpaRepository<Outbox, String> {
}
