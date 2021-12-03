package com.felece.ticketapi.backend.auth;

import com.felece.ticketapi.backend.entity.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "tokens")
public class Token {

    @Id
    private String token;

    @ManyToOne
    private User user;
}
