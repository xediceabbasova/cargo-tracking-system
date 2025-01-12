package com.khadija.shippingservice.service;

import com.khadija.shippingservice.dto.CreateShippingDto;
import com.khadija.shippingservice.dto.OutboxConverter;
import com.khadija.shippingservice.dto.ShippingDto;
import com.khadija.shippingservice.dto.converter.ShippingConverter;
import com.khadija.shippingservice.exception.ShippingNotFoundException;
import com.khadija.shippingservice.model.Shipping;
import com.khadija.shippingservice.model.enums.ShippingStatus;
import com.khadija.shippingservice.repository.ShippingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingService {

    private final ShippingRepository shippingRepository;
    private final OutboxService outboxService;
    private final ShippingConverter shippingConverter;
    private final OutboxConverter outboxConverter;

    public ShippingService(ShippingRepository shippingRepository, OutboxService outboxService, ShippingConverter shippingConverter, OutboxConverter outboxConverter) {
        this.shippingRepository = shippingRepository;
        this.outboxService = outboxService;
        this.shippingConverter = shippingConverter;
        this.outboxConverter = outboxConverter;
    }

    @Transactional
    public ShippingDto createShipping(CreateShippingDto createShippingDto) {
        Shipping saveShipping = shippingRepository.save(shippingConverter.toShipping(createShippingDto));
        outboxService.createOutbox(outboxConverter.convertToOutbox(saveShipping, "Shipping Created"));
        return shippingConverter.fromShipping(saveShipping);
    }

    @Transactional
    public ShippingDto updateShippingStatus(String shippingId, ShippingStatus newStatus) {
        Shipping shipping = findShippingById(shippingId);
        Shipping updatedShipping = new Shipping(
                shipping.getId(),
                shipping.getOrderId(),
                shipping.getAddress(),
                newStatus,
                shipping.getCreatedAt()
        );
        Shipping savedShipping = shippingRepository.save(updatedShipping);
        outboxService.createOutbox(outboxConverter.convertToOutbox(savedShipping, "Shipping Updated"));
        return shippingConverter.fromShipping(savedShipping);
    }

    public List<ShippingDto> findAll() {
        return shippingConverter.convert(shippingRepository.findAll());
    }

    private Shipping findShippingById(String id) {
        return shippingRepository.findById(id)
                .orElseThrow(() -> new ShippingNotFoundException("Shipping not found: " + id));
    }

}
