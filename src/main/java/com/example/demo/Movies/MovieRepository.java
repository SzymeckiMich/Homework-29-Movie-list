package com.example.demo.Movies;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {

    @PersistenceContext
    private EntityManager entityManager;

    //CREATE
    @Transactional
    public void addMovie(Movie movie) {
        entityManager.persist(movie);
    }

    //READ
    @Transactional
    public List<Movie> findByCategory(MovieCategory category) {
        TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.category = " + category, Movie.class);
        return new ArrayList<>(query.getResultList());
    }

    //READ
    @Transactional
    public Movie findById(Long id) {
        TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.id = " + id, Movie.class);
        return query.getSingleResult();
    }
}
