package com.felece.ticketapi.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="capacity")
    private int capacity;

    @Column(name="seat_price")
    private double seatPrice;

    @Column(name = "departure_time")
    private String busDepartureTime;

    @Column(name = "from_direction")
    private String fromDirection;

    @Column(name = "to_direction")
    private String toDirection;

    @JsonIgnore
    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

}
