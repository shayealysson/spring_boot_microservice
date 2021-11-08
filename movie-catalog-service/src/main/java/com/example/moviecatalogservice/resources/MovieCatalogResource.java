package com.example.moviecatalogservice.resources;
import java.util.List;
import java.util.stream.Collectors;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.UserRating;
import com.example.moviecatalogservice.services.MovieInfo;
import com.example.moviecatalogservice.services.UserRatingInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
    /*
	@Autowired
	private WebClient.Builder wBuilder; 
    */

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        //get all rated movie ID's
        UserRating ratings = userRatingInfo.getUserRatings(userId);


        return ratings.getUserRating().stream().map(rating -> {
            return movieInfo.getCatalogItem(rating);
        })
            .collect(Collectors.toList());
    }    
}
