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

@Service
public class PeliculasService {

    @Autowired
    private final PeliculaRepository peliculaRepository;

    @Autowired
    private Mapper mapper;

    public PeliculasService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    public PeliculasDTO createPelicula(PeliculasDTO peliculaDTO) {
        Pelicula pelicula = mapper.mapToEntity(peliculaDTO);
        pelicula = peliculaRepository.save(pelicula);

        return mapper.mapToDTO(pelicula);
    }

    public PeliculasDTO getByID(String id) {
        Long idLong = StringToLong.stringToLong(id);
        Pelicula pelicula = peliculaRepository.findById(idLong).orElse(null);
        if (pelicula == null) {
            return null;
        } else {
            return mapper.mapToDTO(pelicula);
        }
    }

    public List<PeliculasDTO> getAll() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        List<PeliculasDTO> peliculasDTO = new ArrayList<>();
        peliculas.forEach(pelicula -> peliculasDTO.add(mapper.mapToDTO(pelicula)));
        return peliculasDTO;
    }

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

    public List<PeliculasDTO> getByRating(Double rating) {
        List<Pelicula> peliculas = peliculaRepository.findByRatingGreaterThanEqual(rating);
        List<PeliculasDTO> peliculasDTO = new ArrayList<>();
        peliculas.forEach(pelicula -> peliculasDTO.add(mapper.mapToDTO(pelicula)));
        return peliculasDTO;
    }
}
