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

    public List<Movie> findAll() {

        var query = entityManager.createQuery("SELECT m FROM Movie m");
        return (List<Movie>) query.getResultList();
    }

    public Optional<Movie> findOne(Long id) {
        return Optional.ofNullable(entityManager.find(Movie.class, id));

    }

    public void insertMovie(Movie movie) {
        entityManager.persist(movie);
    }

    public void deleteMovie(Long id) {
        var movie = findOne(id);
        movie.ifPresent((m) -> entityManager.remove(m));
    }

    public List<Movie> findAllByTitle(String title) {
        var query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.title LIKE :title");
        query.setParameter("title", title + "%");
        return query.getResultList();
    }

    public Movie update(Long id, Movie movie){
        var entity = entityManager.find(Movie.class, id);
        entity.setTitle(movie.getTitle());
        entityManager.persist(entity);
        return entity;
    }
}
