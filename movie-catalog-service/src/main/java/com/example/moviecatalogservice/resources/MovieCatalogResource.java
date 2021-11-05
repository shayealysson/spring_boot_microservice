package com.example.moviecatalogservice.resources;
import java.util.List;
import java.util.stream.Collectors;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.UserRating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
    /*
	@Autowired
	private WebClient.Builder wBuilder; 
    */

    @Autowired
	private RestTemplate restTemplate; 

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        //get all rated movie ID's
        UserRating ratings = restTemplate.getForObject(
            "http://ratings-data-service/ratingsData/users/"+userId, 
            UserRating.class
        );


        return ratings.getUserRating().stream().map(rating -> {
            /*
            Movie movie = wBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/"+rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
            */
            //for each movieId, call movie info service and get details       
            Movie movie = restTemplate.getForObject(
                "http://movie-info-service/movies/"+rating.getMovieId(),
                Movie.class
            );
            //put them all together
            return new CatalogItem(movie.getName(), "test description", rating.getRating());
        })
            .collect(Collectors.toList());
    }
    
}
