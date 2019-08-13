package com.itsmadhu.moviecatalogservice.resourses;

import com.itsmadhu.moviecatalogservice.models.CatalogItem;
import com.itsmadhu.moviecatalogservice.models.Movie;
import com.itsmadhu.moviecatalogservice.models.Rating;
import com.itsmadhu.moviecatalogservice.models.UserRatings;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class MovieCatalogResourse {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/catalog/{userId}")
    public List<CatalogItem> getCatalog(String userId) {
        UserRatings ratings = restTemplate.getForObject("http://rating-data-service/ratings/users/" + userId,
                UserRatings.class);
            System.out.println(ratings.getRatings().size());
        return ratings.getRatings().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), "Test Desc", rating.getRating());
        }).collect(Collectors.toList());
    }
}
