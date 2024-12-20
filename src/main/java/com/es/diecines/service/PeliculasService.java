package com.es.diecines.service;

import com.es.diecines.dto.PeliculasDTO;
import com.es.diecines.errores.BdException;
import com.es.diecines.errores.NotFoundException;
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
        try {
            if (peliculaDTO == null) {
                throw new BdException("La película enviada es inválida.");
            }
            Pelicula pelicula = mapper.mapToEntity(peliculaDTO);
            pelicula = peliculaRepository.save(pelicula);
            return mapper.mapToDTO(pelicula);
        } catch (Exception e) {
            throw new BdException("Error al crear la película: " + e.getMessage(), e);
        }
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public PeliculasDTO getByID(String id) {
        try {
            Long idLong = StringToLong.stringToLong(id);
            Pelicula pelicula = peliculaRepository.findById(idLong).orElseThrow(() -> new NotFoundException("Película no encontrada con ID: " + id));
            return mapper.mapToDTO(pelicula);
        } catch (NotFoundException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new BdException("ID inválido para la película: " + id, e);
        } catch (Exception e) {
            throw new BdException("Error al obtener la película con ID: " + id, e);
        }
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<PeliculasDTO> getAll() {
        try {
            List<Pelicula> peliculas = peliculaRepository.findAll();
            if (peliculas.isEmpty()) {
                throw new NotFoundException("No hay películas disponibles.");
            }
            List<PeliculasDTO> peliculasDTO = new ArrayList<>();
            peliculas.forEach(pelicula -> peliculasDTO.add(mapper.mapToDTO(pelicula)));
            return peliculasDTO;
        } catch (Exception e) {
            throw new BdException("Error al obtener todas las películas: " + e.getMessage(), e);
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
            if (!peliculaRepository.existsById(idLong)) {
                throw new NotFoundException("Película no encontrada para eliminar con ID: " + id);
            }
            peliculaRepository.deleteById(idLong);
            return true;
        } catch (Exception e) {
            throw new BdException("Error al eliminar la película con ID: " + id, e);
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
        try {
            Long idLong = StringToLong.stringToLong(id);
            Pelicula pelicula = peliculaRepository.findById(idLong).orElseThrow(() -> new NotFoundException("Película no encontrada para actualizar con ID: " + id));
            Pelicula peliculaActualizada = mapper.mapToEntity(peliculaDTO);
            peliculaActualizada.setId(idLong);
            peliculaRepository.save(peliculaActualizada);
            return mapper.mapToDTO(peliculaActualizada);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new BdException("Error al actualizar la película con ID: " + id, e);
        }
    }

    /**
     * Gets by rating.
     *
     * @param rating the rating
     * @return the by rating
     */
    public List<PeliculasDTO> getByRating(Double rating) {
        try {
            List<Pelicula> peliculas = peliculaRepository.findByRatingGreaterThanEqual(rating);
            if (peliculas.isEmpty()) {
                throw new NotFoundException("No se encontraron películas con rating mayor o igual a: " + rating);
            }
            List<PeliculasDTO> peliculasDTO = new ArrayList<>();
            peliculas.forEach(pelicula -> peliculasDTO.add(mapper.mapToDTO(pelicula)));
            return peliculasDTO;
        } catch (Exception e) {
            throw new BdException("Error al obtener películas por rating: " + rating, e);
        }
    }
}

