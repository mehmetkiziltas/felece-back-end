package com.felece.ticketapi.backend.city;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> getCityList() {
        return cityRepository.findAll();
    }
}

