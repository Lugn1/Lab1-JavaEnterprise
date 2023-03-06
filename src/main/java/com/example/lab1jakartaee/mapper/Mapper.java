package com.example.lab1jakartaee.mapper;

import com.example.lab1jakartaee.dto.MovieDto;
import com.example.lab1jakartaee.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;

import java.util.List;

@ApplicationScoped
public class Mapper {
    public List<MovieDto> map(List<Movie> allByTitle) {
        return allByTitle.stream().map(movie -> new MovieDto(movie.getId(), movie.getTitle())).toList();
    }
}
