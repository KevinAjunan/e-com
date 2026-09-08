package com.project.EComApplication.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AddressResponse {
    private String city;
    private String country;
}
