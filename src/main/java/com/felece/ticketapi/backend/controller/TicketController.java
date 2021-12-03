package com.felece.ticketapi.backend.controller;

import com.felece.ticketapi.backend.entity.Ticket;
import com.felece.ticketapi.backend.entity.vm.BuyTicketVM;
import com.felece.ticketapi.backend.entity.vm.TicketDelay;
import com.felece.ticketapi.backend.entity.vm.UserEmail;
import com.felece.ticketapi.backend.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/create/{busId}")
    public void createTicket(@PathVariable final long busId) {
        ticketService.save(busId);
    }

    @PostMapping("/{getByBusId}")
    public ResponseEntity<List<Ticket>> getTicketsByBusId(@PathVariable final long getByBusId) {
        return ResponseEntity.ok(ticketService.getTicketsByBusId(getByBusId));
    }

    @PostMapping("/buy")
    public ResponseEntity<String> updateTicket(@RequestBody BuyTicketVM buyTicketVM) {
        return ResponseEntity.ok(ticketService.buyTicket(buyTicketVM));
    }

    @PostMapping("/useremail")
    public ResponseEntity<List<Ticket>> getTicketByUserEmail(@RequestBody UserEmail userEmail) {
        return ResponseEntity.ok(ticketService.getTicketByUserEmail(userEmail.getEmail()));
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelTicket(@RequestBody String ticketId) {
        return ResponseEntity.ok(ticketService.cancelTicket(ticketId));
    }

    @PostMapping("/delay")
    public ResponseEntity<String> delayTicket(@RequestBody TicketDelay ticketDelay) {
        return ResponseEntity.ok(ticketService.delayTicket(ticketDelay));
    }

    @PostMapping("/useridandfromdirections")
    public ResponseEntity<List<Ticket>> getTicketByUserIdAndFromDirections(@RequestBody UserEmail userEmail) {
        return ResponseEntity.ok(ticketService.getTicketByUserIdAndFromDirections(userEmail));
    }

}