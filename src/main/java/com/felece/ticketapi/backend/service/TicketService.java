package com.felece.ticketapi.backend.service;

import com.felece.ticketapi.backend.entity.Bus;
import com.felece.ticketapi.backend.entity.Ticket;
import com.felece.ticketapi.backend.entity.TicketStatus;
import com.felece.ticketapi.backend.entity.User;
import com.felece.ticketapi.backend.entity.vm.BuyTicketVM;
import com.felece.ticketapi.backend.entity.vm.TicketDelay;
import com.felece.ticketapi.backend.entity.vm.TicketVM;
import com.felece.ticketapi.backend.entity.vm.UserEmail;
import com.felece.ticketapi.backend.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final BusService busService;
    private final UserService userService;

    @Transactional
    public void save(long busId) {
        Bus inDB = busService.getById(busId);
        ArrayList<Ticket> tickets = new ArrayList<>();
        System.out.println(inDB.getCapacity());
        for (int i = 1; i <= inDB.getCapacity(); i++) {
            Ticket ticket = new Ticket();
            UUID uuid = UUID.randomUUID();
            ticket.setId(uuid.toString());
            ticket.setStatus(TicketStatus.FREE);
            ticket.setTicketDate(new Date().toString());
            ticket.setTicketPrice(inDB.getSeatPrice());
            ticket.setToDirection(inDB.getToDirection());
            ticket.setFromDirection(inDB.getFromDirection());
            ticket.setTicketNumber(i);
            ticket.setBusDepartureTime(inDB.getBusDepartureTime());
            ticket.setBus(inDB);
            ticket.setUser(null);
            ticket.setTicketSeat(i);
            tickets.add(ticket);
        }
        ticketRepository.saveAllAndFlush(tickets);
    }

    public List<Ticket> getTicketsByBusId(final long busId) {
        return ticketRepository.getAllByBusIdOrderByTicketSeat(busId);
    }

    @Transactional
    public String buyTicket(final BuyTicketVM buyTicketVM) {
        User inDBUser = userService.findByEmail(buyTicketVM.getUserEmail());
        if (inDBUser == null) {
            return "User not found";
        }
        Ticket inDBTicket = ticketRepository.getById(buyTicketVM.getSeatId());
        if (inDBTicket.getStatus() != TicketStatus.FREE) {
            return "Ticket is not free";
        }
        inDBTicket.setStatus(TicketStatus.SOLD);
        inDBTicket.setUser(inDBUser);
        ticketRepository.save(inDBTicket);
        return "Ticket bought";
    }

    public List<Ticket> getTicketByUserEmail(final String email) {
        User inDBUser = userService.findByEmail(email);
        if (inDBUser == null) {
            return new ArrayList<>();
        }
        return  ticketRepository.findByUser(inDBUser);
    }

    public String cancelTicket(final String ticketId) {
        Ticket inDBTicket = ticketRepository.getById(ticketId);
        if (inDBTicket.getStatus() != TicketStatus.SOLD) {
            return "Ticket is not sold";
        }
        if (inDBTicket.getTicketDate().compareTo(new Date().toString()) < 0) {
            return "Ticket is expired";
        }
        inDBTicket.setStatus(TicketStatus.FREE);
        inDBTicket.setUser(null);
        ticketRepository.save(inDBTicket);
        return "Ticket canceled";
    }

    public String delayTicket(final TicketDelay ticketDelay) {
        Ticket inDBTicket = ticketRepository.getById(ticketDelay.getTicketId());

        if (inDBTicket.getStatus() != TicketStatus.SOLD) {
            return "Ticket is not sold";
        }
        if (inDBTicket.getTicketDate().compareTo(new Date().toString()) < 0) {
            return "Ticket is expired";
        }
        inDBTicket.setTicketDate(ticketDelay.getDate());
        inDBTicket.setStatus(TicketStatus.POSTPONED);
        System.out.println(ticketDelay.getDate() + " " + ticketDelay.getTicketId());
        ticketRepository.save(inDBTicket);
        return "Ticket delayed";
    }

    public List<Ticket> getTicketByUserIdAndFromDirections(final UserEmail userEmail) {
        User inDBUser = userService.findByEmail(userEmail.getEmail());
        if (inDBUser == null) {
            return new ArrayList<>();
        }
        final List<Ticket> byUserAndFromDirection = ticketRepository.findByUserAndFromDirection(inDBUser, userEmail.getFromDirection());
        if (byUserAndFromDirection.isEmpty()) {
            return new ArrayList<>();
        }
        return byUserAndFromDirection;
    }

    public void deleteByBusId(final long busId) {
        ticketRepository.deleteAllByBusId(busId);
    }
}
