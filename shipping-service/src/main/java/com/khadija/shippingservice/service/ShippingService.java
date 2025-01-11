package com.khadija.shippingservice.service;

import com.khadija.shippingservice.dto.CreateShippingDto;
import com.khadija.shippingservice.dto.OutboxConverter;
import com.khadija.shippingservice.dto.ShippingDto;
import com.khadija.shippingservice.dto.converter.ShippingConverter;
import com.khadija.shippingservice.model.Shipping;
import com.khadija.shippingservice.repository.ShippingRepository;
import org.springframework.stereotype.Service;

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

    public ShippingDto createShipping(CreateShippingDto createShippingDto) {
        Shipping saveShipping = shippingRepository.save(shippingConverter.toShipping(createShippingDto));
        outboxService.createOutbox(outboxConverter.convertToOutbox(saveShipping));
        return shippingConverter.fromShipping(saveShipping);
    }

}
