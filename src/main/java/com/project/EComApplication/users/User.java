package com.project.EComApplication.users;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
@AllArgsConstructor
public class User {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String email;

}
