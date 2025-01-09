package com.khadija.shippingservice.model

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Entity
@Table(name = "shippings")
data class Shipping @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Column(name = "order_id", nullable = false)
    var orderId: String,

    @Embedded
    val address: Address,

    @Column(name = "shipping_status", nullable = false)
    @Enumerated(EnumType.STRING)
    val shippingStatus: ShippingStatus = ShippingStatus.PENDING,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: ZonedDateTime,

    @Column(name = "updated_at", insertable = false)
    var updatedAt: ZonedDateTime? = null
) {
    @PrePersist
    fun prePersist() {
        createdAt = ZonedDateTime.now(ZoneOffset.UTC)
    }
    @PreUpdate
    fun preUpdate() {
        updatedAt = ZonedDateTime.now(ZoneOffset.UTC)
    }
}
