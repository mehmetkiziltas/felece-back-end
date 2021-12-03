package com.felece.ticketapi.backend.entity.vm;

import lombok.Data;

@Data
public class BusVm {

    private String departureTime;
    private String fromDirection;
    private String toDirection;
}
