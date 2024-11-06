package com.es.diecines.controller;

import com.es.diecines.dto.PeliculasDTO;
import com.es.diecines.service.PeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas") // -> http://localhost:8080/peliculas
public class PeliculasController {

    @Autowired
    private final PeliculasService peliculaService;

    public PeliculasController(PeliculasService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @PostMapping ("/") // -> http://localhost:8080/peliculas/
    public ResponseEntity<PeliculasDTO> createPelicula(@RequestBody PeliculasDTO peliculaDTO) {
        if(peliculaDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(peliculaService.createPelicula(peliculaDTO));
    }

    @GetMapping("/") // -> http://localhost:8080/peliculas/
    public ResponseEntity<List<PeliculasDTO>> getAll() {
        if(peliculaService.getAll() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(peliculaService.getAll());
    }

    @GetMapping("/{id}") // -> http://localhost:8080/peliculas/1
    public ResponseEntity<PeliculasDTO> getById(@PathVariable String id) {
        if(peliculaService.getByID(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(peliculaService.getByID(id));
    }

    @DeleteMapping("/{id}") // -> http://localhost:8080/peliculas/1
    public ResponseEntity<Boolean> deleteByID(@PathVariable String id) {
        peliculaService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}") // -> http://localhost:8080/peliculas/1
    public ResponseEntity<PeliculasDTO> update(@PathVariable String id, @RequestBody PeliculasDTO dto) {
        if(dto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(peliculaService.update(id, dto));
    }

    @GetMapping("/rating/{rating}") // -> http://localhost:8080/peliculas/rating/3.5
    public ResponseEntity<List<PeliculasDTO>> getByRating(@PathVariable Double rating) {
        if(peliculaService.getByRating(rating) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(peliculaService.getByRating(rating));
    }
}
