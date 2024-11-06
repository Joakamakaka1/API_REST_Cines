package com.es.diecines.repository;

import com.es.diecines.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    List<Pelicula> findByRatingGreaterThanEqual(Double rating);
}
