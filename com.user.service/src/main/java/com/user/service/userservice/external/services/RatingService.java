package com.user.service.userservice.external.services;

import com.user.service.userservice.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;
import java.util.Objects;

@Service
@FeignClient(name = "RATINGSERVICE")
public interface RatingService {


    //get


    //post
    @PostMapping("/rating")
   public  Rating createRating(Rating values);

    //put
    @PutMapping("/rating/{ratingId}")
    public Rating updateRating(@PathVariable String ratingId, Rating rating);

    //delete
    @DeleteMapping("/rating/{ratingId}")
    public  Rating deleteRating(@PathVariable String ratingId);
}
