package com.es.diecines.utils;

import com.es.diecines.dto.PeliculasDTO;
import com.es.diecines.dto.SesionDTO;
import com.es.diecines.model.Pelicula;
import com.es.diecines.model.Sesiones;
import com.es.diecines.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public PeliculasDTO mapToDTO(Pelicula pelicula) {
        PeliculasDTO peliculaDTO = new PeliculasDTO();
        peliculaDTO.setId(pelicula.getId());
        peliculaDTO.setTitle(pelicula.getTitle());
        peliculaDTO.setDirector(pelicula.getDirector());
        peliculaDTO.setTime(pelicula.getTime());
        peliculaDTO.setTrailer(pelicula.getTrailer());
        peliculaDTO.setPosterImage(pelicula.getPosterImage());
        peliculaDTO.setScreenshot(pelicula.getScreenshot());
        peliculaDTO.setSynopsis(pelicula.getSynopsis());
        peliculaDTO.setRating(pelicula.getRating());
        return peliculaDTO;
    }

    public Pelicula mapToEntity(PeliculasDTO dto) {
        Pelicula entity = new Pelicula();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDirector(dto.getDirector());
        entity.setTime(dto.getTime());
        entity.setTrailer(dto.getTrailer());
        entity.setPosterImage(dto.getPosterImage());
        entity.setScreenshot(dto.getScreenshot());
        entity.setSynopsis(dto.getSynopsis());
        entity.setRating(dto.getRating());
        return entity;
    }

    public SesionDTO mapToDTO(Sesiones sesion) {
        SesionDTO sesionDTO = new SesionDTO();
        sesionDTO.setId(sesion.getId());
        sesionDTO.setMovieId(sesion.getPelicula().getId());
        sesionDTO.setRoomId(sesion.getRoomId());
        sesionDTO.setDate(sesion.getDate());
        return sesionDTO;
    }

    public Sesiones mapToEntity(SesionDTO dto) {
        Sesiones sesion = new Sesiones();
        sesion.setId(dto.getId());
        Pelicula pelicula = peliculaRepository.findById(dto.getMovieId()).orElse(null);
        sesion.setPelicula(pelicula);
        sesion.setRoomId(dto.getRoomId());
        sesion.setDate(dto.getDate());
        return sesion;
    }
}