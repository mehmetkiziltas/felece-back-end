package com.felece.ticketapi.backend.auth;

import com.felece.ticketapi.backend.entity.UserRole;
import com.felece.ticketapi.backend.entity.vm.UserVM;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class AuthResponse {

    private String token;

    private UserVM user;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
