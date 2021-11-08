package com.example.moviecatalogservice.services;
import java.util.Arrays;
import java.util.List;

import com.example.moviecatalogservice.models.Rating;
import com.example.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserRatingInfo {

    @Autowired
	private RestTemplate restTemplate; 
    
    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRatings(String userId){
        return restTemplate.getForObject(
            "http://ratings-data-service/ratingsData/users/"+userId, 
            UserRating.class
        );
    }

    public UserRating getFallbackUserRating(String userId) {
        List<Rating> ratings = Arrays.asList(
            new Rating("0", 0)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}





