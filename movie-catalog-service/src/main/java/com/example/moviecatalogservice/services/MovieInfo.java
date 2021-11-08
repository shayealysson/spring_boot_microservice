package com.example.moviecatalogservice.services;
import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
	private RestTemplate restTemplate; 
    
    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating){
         //for each movieId, call movie info service and get details       
         Movie movie = restTemplate.getForObject(
                "http://movie-info-service/movies/"+rating.getMovieId(),
                Movie.class
            );
        //put them all together
        return new CatalogItem(movie.getName(), "test description", rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("No movie", "", 0);
    }
}

