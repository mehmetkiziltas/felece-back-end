package com.felece.ticketapi.backend.entity.vm;

import com.felece.ticketapi.backend.entity.UserRole;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UpdateUserVM {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
