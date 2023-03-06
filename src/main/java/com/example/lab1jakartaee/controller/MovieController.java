package com.example.lab1jakartaee.controller;

import com.example.lab1jakartaee.dto.MovieDto;
import com.example.lab1jakartaee.entity.Movie;
import com.example.lab1jakartaee.mapper.Mapper;
import com.example.lab1jakartaee.repository.MovieRepository;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/movies")
public class MovieController {

    @Inject
    MovieRepository repository;

    @Inject
    Mapper mapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieDto> getAll(@QueryParam("title") String title) {
        if (title == null)
            return mapper.map(repository.findAll());
        return mapper.map(repository.findAllByTitle(title));
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
    public Response addOne(@Valid Movie movie) {
        repository.insertMovie(movie);
        return Response.created(URI.create("movies/" + movie.getId())).build();
    }


    @DELETE
    @Path("/{id}")
    public void deleteOne(@PathParam("id") Long id) {
        repository.deleteMovie(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMovie(@PathParam("id") Long id, @Valid MovieDto movieDto){
        var movie = repository.findOne(id);
        if (movie.isPresent())
            return Response.ok().entity(mapper.map(repository.update(id, mapper.map(movieDto)))).build();
        throw new NotFoundException(Response.status(404).build());
    }


}
