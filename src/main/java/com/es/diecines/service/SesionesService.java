package com.es.diecines.service;

import com.es.diecines.dto.SesionDTO;
import com.es.diecines.model.Sesiones;
import com.es.diecines.repository.SesionesRepository;
import com.es.diecines.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SesionesService {

    @Autowired
    private final SesionesRepository sesionRepository;

    @Autowired
    private Mapper mapper;

    public SesionesService(SesionesRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    public SesionDTO save(SesionDTO sesionDTO) {
        Sesiones sesion = mapper.mapToEntity(sesionDTO);
        sesion = sesionRepository.save(sesion);
        return mapper.mapToDTO(sesion);
    }

    public List<SesionDTO> getAll() {
        List<Sesiones> sesiones = sesionRepository.findAll();
        List<SesionDTO> sesionesDTO = new ArrayList<>();
        sesiones.forEach(sesion -> sesionesDTO.add(mapper.mapToDTO(sesion)));
        return sesionesDTO;
    }
}
