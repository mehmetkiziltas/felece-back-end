package com.felece.ticketapi.backend.repository;

import com.felece.ticketapi.backend.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Long> {

    List<Bus> getByBusDepartureTimeAndFromDirectionAndToDirection(String busDepartureTime, String fromDirection, String toDirection);

    List<Bus> findBusByBusDepartureTimeAndFromDirectionAndToDirection(String busDepartureTime, String fromDirection, String toDirection);
}
