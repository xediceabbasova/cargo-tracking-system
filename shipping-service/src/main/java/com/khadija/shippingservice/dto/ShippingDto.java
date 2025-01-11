package com.khadija.shippingservice.dto;

import com.khadija.shippingservice.model.enums.ShippingStatus;

import java.time.ZonedDateTime;

public record ShippingDto(
        String id,
        String orderId,
        AddressDto address,
        ShippingStatus status,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
}
