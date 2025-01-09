package com.khadija.shippingservice.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    val street: String,
    val city: String,
    val state: String? = null,
    @Column(name = "zip_code")
    val zipCode: String
)
