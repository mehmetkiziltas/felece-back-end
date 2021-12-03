package com.felece.ticketapi.backend.entity.vm;

import lombok.Data;

@Data
public class TicketDelay {
    /**
     * ticketId: ticketId.id,
     * date: newDate
     */
    private String ticketId;
    private String date;
}
