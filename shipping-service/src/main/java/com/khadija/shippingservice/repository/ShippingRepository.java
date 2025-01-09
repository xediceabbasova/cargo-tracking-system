package com.khadija.shippingservice.repository;

import com.khadija.shippingservice.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, String> {
}
