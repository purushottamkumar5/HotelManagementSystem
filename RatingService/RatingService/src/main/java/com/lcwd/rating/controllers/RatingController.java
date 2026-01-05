package com.lcwd.rating.controllers;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    RatingService service;

    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAll()
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getRatings());
    }

    @GetMapping("/users/{Userid}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String Userid)
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getRatingsByUserId(Userid));
    }

    @GetMapping("/hotels/{Hotelid}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String Hotelid)
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getRatingsByHotelId(Hotelid));
    }
}
