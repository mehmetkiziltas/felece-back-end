package com.felece.ticketapi.backend.entity.vm;

import com.felece.ticketapi.backend.entity.User;
import lombok.Data;

@Data
public class UserVM {
    private String userName;
    private String password;

    public UserVM(User user) {
        this.userName = user.getUsername();
        this.password = user.getPassword();
    }
}
