package com.project.EComApplication.users;

import com.project.EComApplication.address.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    AddressMapper addressMapper;
    public UserResponse toResponse(User user){
        return UserResponse.builder()
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .address(addressMapper.toResponse(user.getAddress()))
                .build();
    }
}
