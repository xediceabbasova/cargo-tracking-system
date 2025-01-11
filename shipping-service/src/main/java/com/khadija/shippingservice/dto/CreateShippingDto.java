package com.khadija.shippingservice.dto;

import jakarta.validation.constraints.NotEmpty;

public record CreateShippingDto(
        @NotEmpty(message = "Order ID cannot be empty")
        String orderId,
        @NotEmpty(message = "Address cannot be empty")
        AddressDto addressDto
) {
}
