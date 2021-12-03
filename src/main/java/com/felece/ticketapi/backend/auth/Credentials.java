package com.felece.ticketapi.backend.auth;

import lombok.Data;

@Data
public class Credentials {

    private String email;

    private String password;
}
