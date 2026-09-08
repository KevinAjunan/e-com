package com.project.EComApplication.users;

import com.project.EComApplication.address.Address;
import com.project.EComApplication.address.AddressResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String name;
    private String email;
    private String phoneNumber;
    private AddressResponse address;
}
