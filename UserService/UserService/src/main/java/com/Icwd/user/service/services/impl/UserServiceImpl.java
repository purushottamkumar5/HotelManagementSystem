package com.Icwd.user.service.services.impl;

import com.Icwd.user.service.entities.Hotel;
import com.Icwd.user.service.entities.Rating;
import com.Icwd.user.service.entities.User;
import com.Icwd.user.service.excecptions.ResourceNotFoundException;
import com.Icwd.user.service.repositories.UserRepository;
import com.Icwd.user.service.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        String randonUserID = UUID.randomUUID().toString(); //To get auto generated unique user id
        user.setUserId(randonUserID);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> all = userRepository.findAll();
        for (User user : all) {
            ArrayList<Rating> forObject = restTemplate.getForObject("http://RATINGSERVICE/rating/users/" + user.getUserId(), ArrayList.class);
            //localhost:8082/hotels/16737b25-daae-4118-b127-ca6d9a93b2b6
            user.setRatings(forObject);
        }
        return all;
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server " + userId));

        //fetch rating of the above user from rating service
        //localhost:8083/rating/users/63e0ea35-eb2b-47f9-9540-1e108a298837

        Rating[] forObject = restTemplate.getForObject("http://RATINGSERVICE/rating/users/"+userId, Rating[].class);
        log.info("The rating data : ",forObject);
        log.info("{}",forObject);
        List<Rating> list = Arrays.stream(forObject).toList();
        List<Rating> collect = list.stream().map(
                rating -> {
                    //api call to hotel service to get the hotel
                    //set the hotel to rating
                    //return the rating
                    //localhost:8082/hotels/16737b25-daae-4118-b127-ca6d9a93b2b6
                    Hotel forObject1 = restTemplate.getForObject("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                    rating.setHotel(forObject1);
                    return rating;
                }
        ).collect(Collectors.toList());
        user.setRatings(collect);
        return user;
    }
}
