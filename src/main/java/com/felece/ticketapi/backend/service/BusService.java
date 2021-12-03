package com.felece.ticketapi.backend.service;

import com.felece.ticketapi.backend.entity.Bus;
import com.felece.ticketapi.backend.entity.vm.BusVm;
import com.felece.ticketapi.backend.repository.BusRepository;
import com.felece.ticketapi.backend.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;
    private final TicketRepository ticketRepository;

    public Bus save(Bus bus) {
        if (bus.getToDirection() != null && bus.getFromDirection() != null && bus.getBusDepartureTime() != null) {
            return busRepository.save(bus);
        }else {
            return null;
        }
    }

    public List<Bus> getByDirectionsAndDepartureTime(BusVm busVm) {
        return busRepository.findBusByBusDepartureTimeAndFromDirectionAndToDirection(busVm.getDepartureTime(), busVm.getFromDirection(), busVm.getToDirection());
    }

    public List<Bus> getAll() {
        return busRepository.findAll();
    }

    public Bus getById(Long id) {
        return busRepository.findById(id).orElse(null);
    }

    public String updateBusById(final Bus bus, final Long id) {
        Bus inDb = busRepository.findById(id).orElse(null);
        if (inDb != null) {
            ticketRepository.deleteAllByBusId(id);
            inDb.setBusDepartureTime(bus.getBusDepartureTime());
            inDb.setFromDirection(bus.getFromDirection());
            inDb.setToDirection(bus.getToDirection());
            busRepository.save(inDb);
            return "Bus updated successfully";
        }
        return "Bus not found";
    }

    public String deleteBusById(final Long id) {
        Bus inDb = busRepository.findById(id).orElse(null);
        if (inDb != null) {
            ticketRepository.deleteAllByBusId(id);
            busRepository.deleteById(id);
            return "Bus deleted successfully";
        }
        return "Bus not found";
    }
}
