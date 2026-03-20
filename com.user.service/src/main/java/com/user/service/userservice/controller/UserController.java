package com.user.service.userservice.controller;

import com.user.service.userservice.entity.User;
import com.user.service.userservice.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.flogger.Flogger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users")
public class UserController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired

    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){


        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }


    int rertyCount = 1;
    @GetMapping("/{userId}")
    //@CircuitBreaker(name ="ratingHotelBreaker" ,fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public  ResponseEntity<User> getSingleUser( @PathVariable String userId){
        logger.info("Get Single User Handler: UserController");
        logger.info("Retry count: {}", rertyCount);
        rertyCount++;

        User user = userService.getUser(userId);
        return   ResponseEntity.ok(user);

    }

    //creating fallback method for circuit breaker
    public  ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        logger.info("Fallback is executed because service is down : ", ex.getMessage());
        User user= User.builder()
                .email("test@gmail.com")
                .name("Test")
                .about("This user is created  dummy because  some  services  is down")
                .userId("12345")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);

    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

}
