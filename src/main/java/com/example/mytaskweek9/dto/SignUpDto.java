package com.example.mytaskweek9.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SignUpDto {
    @NotNull
    private String fullName;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String password;
}
