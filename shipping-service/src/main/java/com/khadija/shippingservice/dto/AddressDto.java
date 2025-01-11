package com.khadija.shippingservice.dto;

import jakarta.validation.constraints.NotEmpty;

public record AddressDto(
        @NotEmpty(message = "City cannot be empty")
        String city,
        @NotEmpty(message = "Street cannot be empty")
        String street,
        @NotEmpty(message = "Zip code cannot be empty")
        String zipCode,
        String state
) {
}
