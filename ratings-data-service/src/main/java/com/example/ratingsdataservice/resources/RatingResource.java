package com.example.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import com.example.ratingsdataservice.models.Rating;
import com.example.ratingsdataservice.models.UserRating;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsData")
public class RatingResource {
    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getRatingByUserId(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
            new Rating("id1", 1),
            new Rating("id2", 2),
            new Rating("id3", 3),
            new Rating("id4", 4),
            new Rating("id5", 5)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}
