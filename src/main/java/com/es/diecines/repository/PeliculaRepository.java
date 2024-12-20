package com.es.diecines.repository;

import com.es.diecines.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Pelicula repository.
 */
@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    /**
     * Find by rating greater than equal list.
     *
     * @param rating the rating
     * @return the list
     */
    List<Pelicula> findByRatingGreaterThanEqual(Double rating);
}

