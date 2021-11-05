package com.example.moviecatalogservice.resources;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate; 

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        //RestTemplate restTemplate = new RestTemplate();
        /*
        Movie movie = restTemplate.getForObject(
            //url, 
            "http://localhost:8082/movies/shayed",
            //responseType
            Movie.class
        );*/


        //get all rated movie ID's
        List<Rating> ratings = Arrays.asList(
            new Rating("id1", 1),
            new Rating("id2", 2),
            new Rating("id3", 3),
            new Rating("id4", 4),
            new Rating("id5", 5)
        );


        return ratings.stream().map(rating -> {
            Movie movie = restTemplate.getForObject(
                //url, 
                "http://localhost:8082/movies/"+rating.getMovieId(),
                //responseType
                Movie.class
            );
            return new CatalogItem(movie.getName(), "test description", rating.getRating());
        })
            .collect(Collectors.toList());

        //for each movieId, call movie info service and get details

        //put them all together
        //return Collections.singletonList(new CatalogItem("Transformers", "test", 4));
    }
    
}
