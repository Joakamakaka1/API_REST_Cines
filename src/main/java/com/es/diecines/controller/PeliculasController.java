package com.es.diecines.controller;

import com.es.diecines.dto.PeliculasDTO;
import com.es.diecines.errores.ErrorMsg;
import com.es.diecines.service.PeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Peliculas controller.
 */
@RestController
@RequestMapping("/peliculas") // -> http://localhost:8080/peliculas
public class PeliculasController {

    @Autowired
    private final PeliculasService peliculaService;

    /**
     * Instantiates a new Peliculas controller.
     *
     * @param peliculaService the pelicula service
     */
    public PeliculasController(PeliculasService peliculaService) {
        this.peliculaService = peliculaService;
    }

    /**
     * Create pelicula response entity.
     *
     * @param peliculaDTO the pelicula dto
     * @return the response entity
     */
    @PostMapping("/")
    public ResponseEntity<?> createPelicula(@RequestBody PeliculasDTO peliculaDTO) {
        if (peliculaDTO == null) {
            ErrorMsg error = new ErrorMsg("Bad Request", "The pelicula data is missing or invalid.", 400);
            return ResponseEntity.badRequest().body(error);
        }
        try {
            PeliculasDTO createdPelicula = peliculaService.createPelicula(peliculaDTO);
            return ResponseEntity.ok(createdPelicula);
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<PeliculasDTO> peliculas = peliculaService.getAll();
        if (peliculas == null || peliculas.isEmpty()) {
            ErrorMsg error = new ErrorMsg("Not Found", "No peliculas found.", 404);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(peliculas);
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        PeliculasDTO pelicula = peliculaService.getByID(id);
        if (pelicula == null) {
            ErrorMsg error = new ErrorMsg("Not Found", "Pelicula with ID " + id + " not found.", 404);
            ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok(pelicula);
    }

    /**
     * Delete by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable String id) {
        try {
            boolean deleted = peliculaService.deleteByID(id);
            if (!deleted) {
                ErrorMsg error = new ErrorMsg("Not Found", "Pelicula with ID " + id + " not found to delete.", 404);
                return ResponseEntity.status(404).body(error);
            }
            return ResponseEntity.noContent().build();
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Update response entity.
     *
     * @param id  the id
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody PeliculasDTO dto) {
        if (dto == null) {
            ErrorMsg error = new ErrorMsg("Bad Request", "Pelicula data is missing or invalid.", 400);
            return ResponseEntity.badRequest().body(error);
        }
        try {
            PeliculasDTO updatedPelicula = peliculaService.update(id, dto);
            return ResponseEntity.ok(updatedPelicula);
        } catch (DataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Gets by rating.
     *
     * @param rating the rating
     * @return the by rating
     */
    @GetMapping("/rating/{rating}")
    public ResponseEntity<?> getByRating(@PathVariable Double rating) {
        List<PeliculasDTO> peliculas = peliculaService.getByRating(rating);
        if (peliculas == null || peliculas.isEmpty()) {
            ErrorMsg error = new ErrorMsg("Not Found", "No peliculas found with rating " + rating, 404);
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok(peliculas);
    }
}
