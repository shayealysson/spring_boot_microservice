package com.example.movieinfoservice.resources;

import com.example.movieinfoservice.models.Movie;
import com.example.movieinfoservice.models.MovieSummary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    //https://api.themoviedb.org/3/movie/551?api_key=37ca54e1270ed32ee3ea7a04e827c388
    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){
        MovieSummary movieSummary = restTemplate.getForObject(
            "https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey, 
            MovieSummary.class
            );
        
        return new Movie(movieId, movieSummary.getOriginal_title(), movieSummary.getOverview());
    }
    
}
