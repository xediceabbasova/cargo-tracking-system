package com.khadija.shippingservice.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    val city: String,
    val state: String? = null,
    val street: String,
    @Column(name = "zip_code")
    val zipCode: String
)
