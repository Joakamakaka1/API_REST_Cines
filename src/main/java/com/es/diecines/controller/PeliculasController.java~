package com.es.diecines.controller;

import com.es.diecines.dto.PeliculasDTO;
import com.es.diecines.service.PeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping ("/") // -> http://localhost:8080/peliculas/
    public ResponseEntity<PeliculasDTO> createPelicula(@RequestBody PeliculasDTO peliculaDTO) {
        if(peliculaDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(peliculaService.createPelicula(peliculaDTO));
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping("/") // -> http://localhost:8080/peliculas/
    public ResponseEntity<List<PeliculasDTO>> getAll() {
        if(peliculaService.getAll() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(peliculaService.getAll());
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @GetMapping("/{id}") // -> http://localhost:8080/peliculas/1
    public ResponseEntity<PeliculasDTO> getById(@PathVariable String id) {
        if(peliculaService.getByID(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(peliculaService.getByID(id));
    }

    /**
     * Delete by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}") // -> http://localhost:8080/peliculas/1
    public ResponseEntity<Boolean> deleteByID(@PathVariable String id) {
        peliculaService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Update response entity.
     *
     * @param id  the id
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping("/{id}") // -> http://localhost:8080/peliculas/1
    public ResponseEntity<PeliculasDTO> update(@PathVariable String id, @RequestBody PeliculasDTO dto) {
        if(dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(peliculaService.update(id, dto));
    }

    /**
     * Gets by rating.
     *
     * @param rating the rating
     * @return the by rating
     */
    @GetMapping("/rating/{rating}") // -> http://localhost:8080/peliculas/rating/3.5
    public ResponseEntity<List<PeliculasDTO>> getByRating(@PathVariable Double rating) {
        if(peliculaService.getByRating(rating) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(peliculaService.getByRating(rating));
    }
}
