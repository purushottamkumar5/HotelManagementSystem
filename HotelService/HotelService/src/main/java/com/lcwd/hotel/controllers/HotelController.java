package com.lcwd.hotel.controllers;


import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAll()
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(hotelService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> createHotel(@PathVariable String id)
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(hotelService.get(id));
    }


}
