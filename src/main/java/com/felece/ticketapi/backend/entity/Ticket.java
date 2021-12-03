package com.felece.ticketapi.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

    @Id
    private String id;

    @Column(name = "ticket_number")
    private int ticketNumber;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TicketStatus status;

    @Column(name = "ticket_price")
    private Double ticketPrice;

    @Column(name = "ticket_date")
    private String ticketDate;

    @Column(name = "ticket_seat")
    private int ticketSeat;

    @Column(name = "bus_departure_time")
    private String busDepartureTime;

    @Column(name = "from_direction")
    private String fromDirection;

    @Column(name = "to_direction")
    private String toDirection;

}
