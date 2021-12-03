package com.felece.ticketapi.backend.controller;

import com.felece.ticketapi.backend.entity.Bus;
import com.felece.ticketapi.backend.entity.vm.BusVm;
import com.felece.ticketapi.backend.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/1.0/buses")
public class BusController {

    private final BusService busService;

    @PostMapping("/getbus")
    public ResponseEntity<List<Bus>> getByDepartureAndDirections(@RequestBody BusVm busVm) {
        return ResponseEntity.ok(busService.getByDirectionsAndDepartureTime(busVm));
    }

    @PostMapping("/create")
    public ResponseEntity<Bus> save(@RequestBody Bus bus) {
        return ResponseEntity.ok(busService.save(bus));
    }

    @GetMapping("/getAll")
    public List<Bus> getAll(){
        return busService.getAll();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateBusById(@RequestBody Bus bus, @PathVariable("id") Long id) {
        return ResponseEntity.ok(busService.updateBusById(bus, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBusById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(busService.deleteBusById(id));
    }
}