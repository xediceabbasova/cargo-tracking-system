package com.khadija.shippingservice.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khadija.shippingservice.model.Outbox;
import com.khadija.shippingservice.model.Shipping;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OutboxConverter {

    private final ObjectMapper objectMapper;

    public OutboxConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Outbox convertToOutbox(Shipping shipping, String eventType) {
        try {
            String payload = objectMapper.writeValueAsString(shipping);
            return new Outbox(Objects.requireNonNull(shipping.getId()), eventType, payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while converting Shipping to Outbox", e);
        }
    }
}
