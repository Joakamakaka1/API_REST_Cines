package com.es.diecines.controller;

import com.es.diecines.dto.SesionDTO;
import com.es.diecines.service.SesionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Sesiones controller.
 */
@RestController
@RequestMapping ("/sesiones") // -> http://localhost:8080/sesiones
public class SesionesController {

    @Autowired
    private final SesionesService sesionService;

    /**
     * Instantiates a new Sesiones controller.
     *
     * @param sesionService the sesion service
     */
    public SesionesController(SesionesService sesionService) {
        this.sesionService = sesionService;
    }

    /**
     * Get all response entity.
     *
     * @return the response entity
     */
    @GetMapping ("/") // -> http://localhost:8080/sesiones/
    public ResponseEntity<List<SesionDTO>> getAll(){
        if(sesionService.getAll() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sesionService.getAll());
    }

    /**
     * Create sesion response entity.
     *
     * @param sesionDTO the sesion dto
     * @return the response entity
     */
    @PostMapping ("/") // -> http://localhost:8080/sesiones/
    public ResponseEntity<SesionDTO> createSesion(@RequestBody SesionDTO sesionDTO) {
        if(sesionDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(sesionService.save(sesionDTO));
    }

    /**
     * Delete by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}") // -> http://localhost:8080/sesiones/1
    public ResponseEntity<Boolean> deleteByID(@PathVariable String id) {
        sesionService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Update response entity.
     *
     * @param id        the id
     * @param sesionDTO the sesion dto
     * @return the response entity
     */
    @PutMapping("/{id}") // -> http://localhost:8080/sesiones/1
    public ResponseEntity<SesionDTO> update(@PathVariable String id, @RequestBody SesionDTO sesionDTO) {
        if(sesionDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(sesionService.update(id, sesionDTO));
    }

    /**
     * Gets sesiones today.
     *
     * @return the sesiones today
     */
    @GetMapping("/hoy") // -> http://localhost:8080/sesiones/hoy
    public ResponseEntity<List<SesionDTO>> getSesionesToday() {
        List<SesionDTO> sesiones = sesionService.getByDate();
        return ResponseEntity.ok(sesiones);
    }

}
