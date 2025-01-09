package com.khadija.shippingservice.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.Table
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Entity
@Table(name = "outboxes")
data class Outbox @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Column(name = "aggregate_id", nullable = false)
    val aggregateId: String,

    @Column(nullable = false)
    val type: String,

    @Column(nullable = false, length = 2000)
    val payload: String,

    @Column(name = "created_at", nullable = false)
    var createdAt: ZonedDateTime,
) {
    @PrePersist
    fun prePersist() {
        createdAt = ZonedDateTime.now(ZoneOffset.UTC)
    }
}
