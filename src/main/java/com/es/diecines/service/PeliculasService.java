package com.es.diecines.service;

import com.es.diecines.dto.PeliculasDTO;
import com.es.diecines.errores.BdException;
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
            Pelicula pelicula = mapper.mapToEntity(peliculaDTO);
            pelicula = peliculaRepository.save(pelicula);
            return mapper.mapToDTO(pelicula);
        } catch (Exception e) {
            throw new BdException("Error al crear la película: " + e.getMessage(), e);
        }
    }

    public PeliculasDTO getByID(String id) {
        try {
            Long idLong = StringToLong.stringToLong(id);
            Pelicula pelicula = peliculaRepository.findById(idLong).orElse(null);
            if (pelicula == null) {
                throw new BdException("Película no encontrada con ID: " + id);
            }
            return mapper.mapToDTO(pelicula);
        } catch (Exception e) {
            throw new BdException("Error al obtener la película por ID: " + id, e);
        }
    }

    public List<PeliculasDTO> getAll() {
        try {
            List<Pelicula> peliculas = peliculaRepository.findAll();
            List<PeliculasDTO> peliculasDTO = new ArrayList<>();
            peliculas.forEach(pelicula -> peliculasDTO.add(mapper.mapToDTO(pelicula)));
            return peliculasDTO;
        } catch (Exception e) {
            throw new BdException("Error al obtener todas las películas: " + e.getMessage(), e);
        }
    }

    public boolean deleteByID(String id) {
        try {
            Long idLong = StringToLong.stringToLong(id);
            peliculaRepository.findById(idLong).ifPresent(pelicula -> peliculaRepository.deleteById(idLong));
            return true;
        } catch (Exception e) {
            throw new BdException("Error al eliminar la película con ID: " + id, e);
        }
    }

    public PeliculasDTO update(String id, PeliculasDTO peliculaDTO) {
        try {
            Long idLong = StringToLong.stringToLong(id);
            Pelicula pelicula = peliculaRepository.findById(idLong).orElse(null);
            if (pelicula == null) {
                throw new BdException("Película no encontrada para actualizar con ID: " + id);
            }
            Pelicula peliculaActualizada = mapper.mapToEntity(peliculaDTO);
            peliculaActualizada.setId(idLong);
            peliculaRepository.save(peliculaActualizada);
            return mapper.mapToDTO(peliculaActualizada);
        } catch (Exception e) {
            throw new BdException("Error al actualizar la película con ID: " + id, e);
        }
    }

    public List<PeliculasDTO> getByRating(Double rating) {
        try {
            List<Pelicula> peliculas = peliculaRepository.findByRatingGreaterThanEqual(rating);
            List<PeliculasDTO> peliculasDTO = new ArrayList<>();
            peliculas.forEach(pelicula -> peliculasDTO.add(mapper.mapToDTO(pelicula)));
            return peliculasDTO;
        } catch (Exception e) {
            throw new BdException("Error al obtener películas por rating mayor o igual a: " + rating, e);
        }
    }
}
