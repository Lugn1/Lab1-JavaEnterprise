package com.example.lab1jakartaee.dto;

import com.example.lab1jakartaee.entity.Movie;

public class MovieDto {
    private Long id;
    private String title;

    public MovieDto() {
    }

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
