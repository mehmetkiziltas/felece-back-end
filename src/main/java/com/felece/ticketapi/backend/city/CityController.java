package com.felece.ticketapi.backend.city;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/api/1.0/cities")
    public List<City> getCities() {
        return cityService.getCityList();
    }
}
