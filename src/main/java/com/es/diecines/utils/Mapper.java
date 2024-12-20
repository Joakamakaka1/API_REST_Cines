package com.es.diecines.utils;

import com.es.diecines.dto.PeliculasDTO;
import com.es.diecines.dto.SesionDTO;
import com.es.diecines.model.Pelicula;
import com.es.diecines.model.Sesiones;
import com.es.diecines.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Mapper.
 */
@Component
public class Mapper {

    @Autowired
    private PeliculaRepository peliculaRepository;

    /**
     * Map to dto peliculas dto.
     *
     * @param pelicula the pelicula
     * @return the peliculas dto
     */
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

    /**
     * Map to entity pelicula.
     *
     * @param dto the dto
     * @return the pelicula
     */
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

    /**
     * Map to dto sesion dto.
     *
     * @param sesion the sesion
     * @return the sesion dto
     */
    public SesionDTO mapToDTO(Sesiones sesion) {
        SesionDTO sesionDTO = new SesionDTO();
        sesionDTO.setId(sesion.getId());
        sesionDTO.setMovieId(sesion.getPelicula().getId());
        sesionDTO.setRoomId(sesion.getRoomId());
        sesionDTO.setDate(sesion.getDate());
        return sesionDTO;
    }

    /**
     * Map to entity sesiones.
     *
     * @param dto the dto
     * @return the sesiones
     */
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
