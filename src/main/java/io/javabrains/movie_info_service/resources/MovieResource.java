package io.javabrains.movie_info_service.resources;

import io.javabrains.movie_info_service.models.Message;
import io.javabrains.movie_info_service.models.Movie;
import io.javabrains.movie_info_service.models.MovieSummary;
import io.javabrains.movie_info_service.producer.ProducerService;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    // 785ac1833a5c91a962a7a28a382ecbd4
    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProducerService producerService;


    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey,
                MovieSummary.class
        );
        assert movieSummary != null;
        return new Movie(movieId, movieSummary.title(), movieSummary.overview());
    }

    @GetMapping("/generate")
    public String generate (@RequestParam String message,
                            @RequestParam int age) {
        producerService.produce(new Message(age, message));
        return "OK";
    }
}
