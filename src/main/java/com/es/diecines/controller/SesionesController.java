package com.es.diecines.controller;

import com.es.diecines.dto.SesionDTO;
import com.es.diecines.errores.ErrorMsg;
import com.es.diecines.service.SesionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Sesiones controller.
 */
@RestController
@RequestMapping("/sesiones") // -> http://localhost:8080/sesiones
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
    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        List<SesionDTO> sesiones = sesionService.getAll();
        if (sesiones == null || sesiones.isEmpty()) {
            ErrorMsg error = new ErrorMsg("Not Found", "No sesiones found.", 404);
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok(sesiones);
    }

    /**
     * Create sesion response entity.
     *
     * @param sesionDTO the sesion dto
     * @return the response entity
     */
    @PostMapping("/")
    public ResponseEntity<?> createSesion(@RequestBody SesionDTO sesionDTO) {
        if (sesionDTO == null) {
            ErrorMsg error = new ErrorMsg("Bad Request", "Sesion data is missing or invalid.", 400);
            return ResponseEntity.badRequest().body(error);
        }
        try {
            SesionDTO createdSesion = sesionService.save(sesionDTO);
            return ResponseEntity.ok(createdSesion);
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", e.getMessage(), 500);
            return ResponseEntity.status(500).body(error);
        }
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
            boolean exists = sesionService.deleteByID(id);
            if (!exists) {
                ErrorMsg error = new ErrorMsg("Not Found", "Sesion with ID " + id + " not found.", 404);
                return ResponseEntity.status(404).body(error);
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", e.getMessage(), 500);
            return ResponseEntity.status(500).body(error);
        }
    }

    /**
     * Update response entity.
     *
     * @param id        the id
     * @param sesionDTO the sesion dto
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody SesionDTO sesionDTO) {
        if (sesionDTO == null) {
            ErrorMsg error = new ErrorMsg("Bad Request", "Sesion data is missing or invalid.", 400);
            return ResponseEntity.badRequest().body(error);
        }
        try {
            SesionDTO updatedSesion = sesionService.update(id, sesionDTO);
            if (updatedSesion == null) {
                ErrorMsg error = new ErrorMsg("Not Found", "Sesion with ID " + id + " not found.", 404);
                return ResponseEntity.status(404).body(error);
            }
            return ResponseEntity.ok(updatedSesion);
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", e.getMessage(), 500);
            return ResponseEntity.status(500).body(error);
        }
    }

    /**
     * Gets sesiones today.
     *
     * @return the sesiones today
     */
    @GetMapping("/hoy")
    public ResponseEntity<?> getSesionesToday() {
        try {
            List<SesionDTO> sesiones = sesionService.getByDate();
            if (sesiones == null || sesiones.isEmpty()) {
                ErrorMsg error = new ErrorMsg("Not Found", "No sesiones found for today.", 404);
                return ResponseEntity.status(404).body(error);
            }
            return ResponseEntity.ok(sesiones);
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", e.getMessage(), 500);
            return ResponseEntity.status(500).body(error);
        }
    }
}
