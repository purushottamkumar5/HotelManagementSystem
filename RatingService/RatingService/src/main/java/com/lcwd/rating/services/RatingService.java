package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.List;

public interface RatingService {

    Rating create(Rating rating);

    List<Rating> getRatings();

    List<Rating> getRatingsByUserId(String UserId);

    List<Rating> getRatingsByHotelId(String HotelId);
}
