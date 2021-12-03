package com.felece.ticketapi.backend.entity.vm;

import com.felece.ticketapi.backend.entity.User;
import com.felece.ticketapi.backend.entity.UserRole;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserListVM {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole roleName;

    public UserListVM(User user) {
        this.userName = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.roleName = user.getRole();
    }
}
