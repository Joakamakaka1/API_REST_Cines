package com.es.diecines.service;

import com.es.diecines.dto.SesionDTO;
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
        Sesiones sesion = mapper.mapToEntity(sesionDTO);
        sesion = sesionRepository.save(sesion);
        return mapper.mapToDTO(sesion);
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<SesionDTO> getAll() {
        List<Sesiones> sesiones = sesionRepository.findAll();
        List<SesionDTO> sesionesDTO = new ArrayList<>();
        sesiones.forEach(sesion -> sesionesDTO.add(mapper.mapToDTO(sesion)));
        return sesionesDTO;
    }

    /**
     * Delete by id boolean.
     *
     * @param id the id
     */
    public boolean deleteByID(String id) {
        Long idLong = StringToLong.stringToLong(id);
        sesionRepository.findById(idLong).ifPresent(sesion -> sesionRepository.deleteById(idLong));
        return true;
    }

    /**
     * Update sesion dto.
     *
     * @param id        the id
     * @param sesionDTO the sesion dto
     * @return the sesion dto
     */
    public SesionDTO update(String id, SesionDTO sesionDTO) {
        Long idLong = StringToLong.stringToLong(id);
        Sesiones sesion = sesionRepository.findById(idLong).orElse(null);
        if (sesion == null) {
            return null;
        }
        Sesiones sesionActualizada = mapper.mapToEntity(sesionDTO);
        sesionActualizada.setId(idLong);
        sesionRepository.save(sesionActualizada);
        return mapper.mapToDTO(sesionActualizada);
    }

    /**
     * Gets by date.
     *
     * @return the by date
     */
    public List<SesionDTO> getByDate() {
        LocalDate today = LocalDate.now();
        List<Sesiones> sesiones = sesionRepository.findByDate(today);
        List<SesionDTO> sesionesDTO = new ArrayList<>();
        sesiones.forEach(sesion -> sesionesDTO.add(mapper.mapToDTO(sesion)));
        return sesionesDTO;
    }
}
