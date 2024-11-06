package com.es.diecines.service;

import com.es.diecines.dto.PeliculasDTO;
import com.es.diecines.model.Pelicula;
import com.es.diecines.repository.PeliculaRepository;
import com.es.diecines.utils.Mapper;
import com.es.diecines.utils.StringToLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Peliculas service.
 */
@Service
public class PeliculasService {

    @Autowired
    private final PeliculaRepository peliculaRepository;

    @Autowired
    private Mapper mapper;

    /**
     * Instantiates a new Peliculas service.
     *
     * @param peliculaRepository the pelicula repository
     */
    public PeliculasService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    /**
     * Create pelicula peliculas dto.
     *
     * @param peliculaDTO the pelicula dto
     * @return the peliculas dto
     */
    public PeliculasDTO createPelicula(PeliculasDTO peliculaDTO) {
        Pelicula pelicula = mapper.mapToEntity(peliculaDTO);
        pelicula = peliculaRepository.save(pelicula);

        return mapper.mapToDTO(pelicula);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public PeliculasDTO getByID(String id) {
        Long idLong = StringToLong.stringToLong(id);
        Pelicula pelicula = peliculaRepository.findById(idLong).orElse(null);
        if (pelicula == null) {
            return null;
        } else {
            return mapper.mapToDTO(pelicula);
        }
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<PeliculasDTO> getAll() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        List<PeliculasDTO> peliculasDTO = new ArrayList<>();
        peliculas.forEach(pelicula -> peliculasDTO.add(mapper.mapToDTO(pelicula)));
        return peliculasDTO;
    }

    /**
     * Delete by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteByID(String id) {
        Long idLong = StringToLong.stringToLong(id);
        Pelicula pelicula = peliculaRepository.findById(idLong).orElse(null);
        if (pelicula == null) {
            return false;
        } else {
            peliculaRepository.deleteById(idLong);
            return true;
        }
    }

    /**
     * Update peliculas dto.
     *
     * @param id          the id
     * @param peliculaDTO the pelicula dto
     * @return the peliculas dto
     */
    public PeliculasDTO update(String id, PeliculasDTO peliculaDTO) {
        Long idLong = StringToLong.stringToLong(id);
        Pelicula pelicula = peliculaRepository.findById(idLong).orElse(null);
        if (pelicula == null) {
            return null;
        }
        Pelicula peliculaActualizada = mapper.mapToEntity(peliculaDTO);
        peliculaActualizada.setId(idLong);
        peliculaRepository.save(peliculaActualizada);
        return mapper.mapToDTO(peliculaActualizada);
    }

    /**
     * Gets by rating.
     *
     * @param rating the rating
     * @return the by rating
     */
    public List<PeliculasDTO> getByRating(Double rating) {
        List<Pelicula> peliculas = peliculaRepository.findByRatingGreaterThanEqual(rating);
        List<PeliculasDTO> peliculasDTO = new ArrayList<>();
        peliculas.forEach(pelicula -> peliculasDTO.add(mapper.mapToDTO(pelicula)));
        return peliculasDTO;
    }
}
