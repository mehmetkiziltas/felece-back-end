package com.felece.ticketapi.backend.repository;

import com.felece.ticketapi.backend.entity.Ticket;
import com.felece.ticketapi.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> , JpaSpecificationExecutor<Ticket> {

    List<Ticket> getAllByBusIdOrderByTicketSeat(Long busId);

    List<Ticket> findByUser(User user);

    List<Ticket> findByUserAndFromDirection(User user, String fromDirection);

    void deleteAllByBusId(Long busId);

}
