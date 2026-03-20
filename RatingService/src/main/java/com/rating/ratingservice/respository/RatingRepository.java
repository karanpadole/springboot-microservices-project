package com.rating.ratingservice.respository;

import com.rating.ratingservice.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,String> {

    //Custom findder method
    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);
}
