package com.user.service.userservice;

import com.user.service.userservice.entity.Rating;
import com.user.service.userservice.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private RatingService ratingService;

//    @Test
//    void  createRating(){
//
//        Rating rating=Rating.builder().rating(10).userId("").hotelId("").feedback("this is created using feing client").build();
//        Rating saveRating= ratingService.createRating(rating);
//        System.out.print("new rating created using feing");
//    }
}
