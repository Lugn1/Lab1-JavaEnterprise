package com.example.lab1jakartaee.repository;

import com.example.lab1jakartaee.entity.Movie;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@ApplicationScoped
public class MovieRepository {

    @PersistenceContext
    EntityManager entityManager;

    List<Movie> findAll() {

        var query = entityManager.createQuery("SELECT m FROM Movie m");
        return (List<Movie>) query.getResultList();
    }

    Optional<Movie> findOne(Long id) {
        return Optional.ofNullable(entityManager.find(Movie.class, id));

    }

    public void insertMovie(Movie movie) {
        entityManager.persist(movie);
    }

    public void deleteMovie(Long id) {
        var movie = findOne(id);
        movie.ifPresent((m) -> entityManager.remove(movie));
    }

}
