package com.khadija.shippingservice.dto.converter;

import com.khadija.shippingservice.dto.AddressDto;
import com.khadija.shippingservice.dto.CreateShippingDto;
import com.khadija.shippingservice.dto.ShippingDto;
import com.khadija.shippingservice.model.Address;
import com.khadija.shippingservice.model.Shipping;
import org.springframework.stereotype.Component;

@Component
public class ShippingConverter {

    public Shipping toShipping(CreateShippingDto dto) {
        return new Shipping(
                dto.orderId(),
                new Address(
                        dto.addressDto().city(),
                        dto.addressDto().state(),
                        dto.addressDto().street(),
                        dto.addressDto().zipCode()
                )
        );
    }


    public ShippingDto fromShipping(Shipping shipping) {
        AddressDto addressDto = new AddressDto(
                shipping.getAddress().getCity(),
                shipping.getAddress().getStreet(),
                shipping.getAddress().getZipCode(),
                shipping.getAddress().getState()
        );
        return new ShippingDto(
                shipping.getId(),
                shipping.getOrderId(),
                addressDto,
                shipping.getShippingStatus(),
                shipping.getCreatedAt(),
                shipping.getUpdatedAt()
        );
    }
}
