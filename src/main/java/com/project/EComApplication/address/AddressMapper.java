package com.project.EComApplication.address;

import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public AddressResponse toResponse(Address address) {
            return AddressResponse.builder()
                    .city(address.getCity())
                    .country(address.getCountry())
                    .build();
        }
    }
