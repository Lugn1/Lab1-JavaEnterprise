package com.example.lab1jakartaee.controller;

import com.example.lab1jakartaee.entity.Movie;
import com.example.lab1jakartaee.repository.MovieRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/movies")
public class MovieController {

    @Inject
    MovieRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAll(@QueryParam("title") String title) {
        if (title == null)
            return repository.findAll();
        return repository.findAllByTitle(title);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") Long id){
        var movie = repository.findOne(id);
        if (movie.isPresent())
            return Response.ok().entity(movie.get()).build();
        return Response.status(404).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOne(Movie movie) {
        repository.insertMovie(movie);
        return Response.created(URI.create("movies/" + movie.getId())).build();
    }



    @DELETE
    @Path("/{id}")
    public void deleteOne(@PathParam("id") Long id) {
        repository.deleteMovie(id);
    }


}
