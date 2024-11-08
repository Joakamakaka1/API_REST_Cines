package com.es.diecines.repository;

import com.es.diecines.model.Sesiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface Sesiones repository.
 */
@Repository
public interface SesionesRepository extends JpaRepository<Sesiones, Long> {
    /**
     * Find by date list.
     *
     * @param date the date
     * @return the list
     */
    List<Sesiones> findByDate(LocalDate date);
}
