package com.es.diecines.repository;

import com.es.diecines.model.Sesiones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SesionesRepository extends JpaRepository<Sesiones, Long> {
}
