package com.felece.ticketapi.backend.entity.vm;

import com.felece.ticketapi.backend.entity.Ticket;
import lombok.Data;

@Data
public class TicketVM {
    private String fromDirection;
    private String toDirection;
    private String departureTime;

    public TicketVM(Ticket ticket) {
        this.fromDirection = ticket.getFromDirection();
        this.toDirection = ticket.getToDirection();
        this.departureTime = ticket.getBusDepartureTime();
    }
}
