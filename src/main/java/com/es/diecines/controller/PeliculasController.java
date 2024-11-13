package com.es.diecines.controller;

import com.es.diecines.dto.PeliculasDTO;
import com.es.diecines.errores.ErrorMsg;
import com.es.diecines.errores.NotFoundException;
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
    @PostMapping("/") // -> http://localhost:8080/peliculas
    public ResponseEntity<?> createPelicula(@RequestBody PeliculasDTO peliculaDTO) {
        if (peliculaDTO == null) {
            ErrorMsg error = new ErrorMsg("Bad Request", "La película enviada es inválida o incompleta.", 400);
            return ResponseEntity.badRequest().body(error);
        }

        try {
            PeliculasDTO createdPelicula = peliculaService.createPelicula(peliculaDTO);
            return ResponseEntity.status(201).body(createdPelicula);
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", "Error al guardar la película: " + e.getMessage(), 500);
            return ResponseEntity.status(500).body(error);
        }
    }

    /**
     * Get all peliculas response entity.
     *
     * @return the response entity
     */
    @GetMapping("/") // -> http://localhost:8080/peliculas
    public ResponseEntity<?> getAll() {
        try {
            List<PeliculasDTO> peliculas = peliculaService.getAll();
            return ResponseEntity.ok(peliculas);
        } catch (DataAccessException e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", "Error al obtener las películas.", 500);
            return ResponseEntity.status(500).body(error);
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", "Error desconocido al obtener las películas.", 500);
            return ResponseEntity.status(500).body(error);
        }
    }

    /**
     * Get pelicula by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}") // -> http://localhost:8080/peliculas
    public ResponseEntity<?> getById(@PathVariable String id) {
        try {
            PeliculasDTO pelicula = peliculaService.getByID(id);
            if (pelicula == null) {
                ErrorMsg error = new ErrorMsg("Not Found", "Película con ID " + id + " no encontrada.", 404);
                return ResponseEntity.status(404).body(error);
            }
            return ResponseEntity.ok(pelicula);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg("Not Found", e.getMessage(), 404);
            return ResponseEntity.status(404).body(error);
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", "Error al obtener la película con ID " + id, 500);
            return ResponseEntity.status(500).body(error);
        }
    }

    /**
     * Delete pelicula by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}") // -> http://localhost:8080/peliculas
    public ResponseEntity<?> deleteByID(@PathVariable String id) {
        try {
            boolean deleted = peliculaService.deleteByID(id);
            if (!deleted) {
                ErrorMsg error = new ErrorMsg("Not Found", "Película con ID " + id + " no encontrada para eliminar.", 404);
                return ResponseEntity.status(404).body(error);
            }
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg("Not Found", e.getMessage(), 404);
            return ResponseEntity.status(404).body(error);
        } catch (DataAccessException e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", "Error de acceso a la base de datos al eliminar la película.", 500);
            return ResponseEntity.status(500).body(error);
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", "Error al eliminar la película con ID " + id, 500);
            return ResponseEntity.status(500).body(error);
        }
    }

    /**
     * Update pelicula response entity.
     *
     * @param id          the id
     * @param peliculaDTO the pelicula dto
     * @return the response entity
     */
    @PutMapping("/{id}") // -> http://localhost:8080/peliculas
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody PeliculasDTO peliculaDTO) {
        if (peliculaDTO == null) {
            ErrorMsg error = new ErrorMsg("Bad Request", "Datos de la película inválidos.", 400);
            return ResponseEntity.badRequest().body(error);
        }

        try {
            PeliculasDTO updatedPelicula = peliculaService.update(id, peliculaDTO);
            return ResponseEntity.ok(updatedPelicula);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg("Not Found", e.getMessage(), 404);
            return ResponseEntity.status(404).body(error);
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", "Error al actualizar la película.", 500);
            return ResponseEntity.status(500).body(error);
        }
    }

    /**
     * Get peliculas by rating response entity.
     *
     * @param rating the rating
     * @return the response entity
     */
    @GetMapping("/rating/{rating}") // -> http://localhost:8080/peliculas
    public ResponseEntity<?> getByRating(@PathVariable Double rating) {
        try {
            List<PeliculasDTO> peliculas = peliculaService.getByRating(rating);
            return ResponseEntity.ok(peliculas);
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", "Error al obtener las películas por rating.", 500);
            return ResponseEntity.status(500).body(error);
        }
    }
}
