package com.felece.ticketapi.backend.entity.vm;

import lombok.Data;

@Data
public class BuyTicketVM {
    private String userEmail;
    private String seatId;
    private String busId;
}
