package com.user.service.userservice.services.impl;

import com.user.service.userservice.entity.Hotel;
import com.user.service.userservice.entity.Rating;
import com.user.service.userservice.entity.User;
import com.user.service.userservice.exceptions.ResourceNotFoundException;
import com.user.service.userservice.external.services.HotelService;
import com.user.service.userservice.respositories.UserRepository;
import com.user.service.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
//import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User saveUser(User user) {

        String randomUserId= UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given is is not found on server !!: "+userId));


        //http://localhost:8084/rating/user/76699135-8407-41bd-8903-6e4c71304fed

        Rating[] ratingsOfUser= restTemplate.getForObject("http://RATINGSERVICE/rating/user/"+user.getUserId(), Rating[].class);
       logger.info("{} ",ratingsOfUser);

        List<Rating> ratings=Arrays.stream(ratingsOfUser).toList();

       List<Rating> ratingList = ratings.stream().map( rating -> {

           //api call to hotel seervice to get hotel
           //set the hotel to the rating
           //return rating

           //http://localhost:8083/hotels/311bc12c-e061-49ad-82b3-40076551e3bc

          //// ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);

           Hotel hotel =hotelService.getHotel(rating.getHotelId());
           ////logger.info("response status code {} ",forEntity.getStatusCode());

           //set the hotel to the rating
           rating.setHotel(hotel);
           return  rating;

       }).collect(Collectors.toList());


       user.setRatings(ratingList);
       return  user;
    }
}
