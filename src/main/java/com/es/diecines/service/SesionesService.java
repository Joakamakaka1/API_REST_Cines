package com.es.diecines.service;

import com.es.diecines.dto.SesionDTO;
import com.es.diecines.errores.BdException;
import com.es.diecines.errores.NotFoundException;
import com.es.diecines.model.Sesiones;
import com.es.diecines.repository.SesionesRepository;
import com.es.diecines.utils.Mapper;
import com.es.diecines.utils.StringToLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Sesiones service.
 */
@Service
public class SesionesService {

    @Autowired
    private final SesionesRepository sesionRepository;

    @Autowired
    private Mapper mapper;

    /**
     * Instantiates a new Sesiones service.
     *
     * @param sesionRepository the sesion repository
     */
    public SesionesService(SesionesRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    /**
     * Save sesion dto.
     *
     * @param sesionDTO the sesion dto
     * @return the sesion dto
     */
    public SesionDTO save(SesionDTO sesionDTO) {
        try {
            Sesiones sesion = mapper.mapToEntity(sesionDTO);
            sesion = sesionRepository.save(sesion);
            return mapper.mapToDTO(sesion);
        } catch (Exception e) {
            throw new BdException("Error al guardar la sesión: " + e.getMessage(), e);
        }
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<SesionDTO> getAll() {
        try {
            List<Sesiones> sesiones = sesionRepository.findAll();
            if (sesiones.isEmpty()) {
                throw new NotFoundException("No sesiones found.");
            }
            List<SesionDTO> sesionesDTO = new ArrayList<>();
            sesiones.forEach(sesion -> sesionesDTO.add(mapper.mapToDTO(sesion)));
            return sesionesDTO;
        } catch (NotFoundException e) {
            throw e;  // Propagar la excepción personalizada
        } catch (Exception e) {
            throw new BdException("Error al obtener todas las sesiones: " + e.getMessage(), e);
        }
    }

    /**
     * Delete by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteByID(String id) {
        try {
            Long idLong = StringToLong.stringToLong(id);
            Sesiones sesion = sesionRepository.findById(idLong).orElse(null);
            if (sesion == null) {
                throw new NotFoundException("Sesión con ID: " + id + " no encontrada.");
            }
            sesionRepository.deleteById(idLong);
            return true;
        } catch (NotFoundException e) {
            throw e;  // Propagar la excepción personalizada
        } catch (Exception e) {
            throw new BdException("Error al eliminar la sesión con ID: " + id, e);
        }
    }

    /**
     * Update sesion dto.
     *
     * @param id        the id
     * @param sesionDTO the sesion dto
     * @return the sesion dto
     */
    public SesionDTO update(String id, SesionDTO sesionDTO) {
        try {
            Long idLong = StringToLong.stringToLong(id);
            Sesiones sesion = sesionRepository.findById(idLong).orElse(null);
            if (sesion == null) {
                throw new NotFoundException("Sesión no encontrada para actualizar con ID: " + id);
            }
            Sesiones sesionActualizada = mapper.mapToEntity(sesionDTO);
            sesionActualizada.setId(idLong);
            sesionRepository.save(sesionActualizada);
            return mapper.mapToDTO(sesionActualizada);
        } catch (NotFoundException e) {
            throw e;  // Propagar la excepción personalizada
        } catch (Exception e) {
            throw new BdException("Error al actualizar la sesión con ID: " + id, e);
        }
    }

    /**
     * Gets by date.
     *
     * @return the by date
     */
    public List<SesionDTO> getByDate() {
        try {
            LocalDate today = LocalDate.now();
            List<Sesiones> sesiones = sesionRepository.findByDate(today);
            if (sesiones.isEmpty()) {
                throw new NotFoundException("No sesiones found for today.");
            }
            List<SesionDTO> sesionesDTO = new ArrayList<>();
            sesiones.forEach(sesion -> sesionesDTO.add(mapper.mapToDTO(sesion)));
            return sesionesDTO;
        } catch (NotFoundException e) {
            throw e;  // Propagar la excepción personalizada
        } catch (Exception e) {
            throw new BdException("Error al obtener sesiones por fecha de hoy: " + e.getMessage(), e);
        }
    }
}
