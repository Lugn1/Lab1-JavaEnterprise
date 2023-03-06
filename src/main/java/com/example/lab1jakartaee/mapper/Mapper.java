package com.example.lab1jakartaee.mapper;

import com.example.lab1jakartaee.dto.MovieDto;
import com.example.lab1jakartaee.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class Mapper {
    public List<MovieDto> map(List<Movie> allByTitle) {
        return allByTitle.stream().map(MovieDto::new).toList();
    }

    public MovieDto map(Movie movie){
        return new MovieDto(movie);
    }

    public Movie map(MovieDto movie){
        var m = new Movie();
        m.setId(movie.getId());
        m.setTitle(movie.getTitle());
        return m;
    }
}
