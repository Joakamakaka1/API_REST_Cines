package com.es.diecines.controller;

import com.es.diecines.dto.SesionDTO;
import com.es.diecines.errores.ErrorMsg;
import com.es.diecines.errores.NotFoundException;
import com.es.diecines.service.SesionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    @GetMapping("/") // -> http://localhost:8080/sesiones
    public ResponseEntity<?> getAll() {
        try {
            List<SesionDTO> sesiones = sesionService.getAll();
            return ResponseEntity.ok(sesiones);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg("Not Found", e.getMessage(), 404);
            return ResponseEntity.status(404).body(error);
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", "Error al obtener todas las sesiones.", 500);
            return ResponseEntity.status(500).body(error);
        }
    }

    /**
     * Create sesion response entity.
     *
     * @param sesionDTO the sesion dto
     * @return the response entity
     */
    @PostMapping("/") // -> http://localhost:8080/sesiones
    public ResponseEntity<?> createSesion(@RequestBody SesionDTO sesionDTO) {
        if (sesionDTO == null) {
            ErrorMsg error = new ErrorMsg("Bad Request", "Sesion data is missing or invalid.", 400);
            return ResponseEntity.badRequest().body(error);
        }
        try {
            SesionDTO createdSesion = sesionService.save(sesionDTO);
            return ResponseEntity.ok(createdSesion);
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", "Error al guardar la sesión.", 500);
            return ResponseEntity.status(500).body(error);
        }
    }

    /**
     * Delete by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}") // -> http://localhost:8080/sesiones
    public ResponseEntity<?> deleteByID(@PathVariable String id) {
        try {
            boolean exists = sesionService.deleteByID(id);
            if (!exists) {
                ErrorMsg error = new ErrorMsg("Not Found", "Sesion with ID " + id + " not found.", 404);
                return ResponseEntity.status(404).body(error);
            }
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg("Not Found", e.getMessage(), 404);
            return ResponseEntity.status(404).body(error);
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", "Error al eliminar la sesión.", 500);
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
    @PutMapping("/{id}") // -> http://localhost:8080/sesiones
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody SesionDTO sesionDTO) {
        if (sesionDTO == null) {
            ErrorMsg error = new ErrorMsg("Bad Request", "Sesion data is missing or invalid.", 400);
            return ResponseEntity.badRequest().body(error);
        }
        try {
            SesionDTO updatedSesion = sesionService.update(id, sesionDTO);
            return ResponseEntity.ok(updatedSesion);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg("Not Found", e.getMessage(), 404);
            return ResponseEntity.status(404).body(error);
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", "Error al actualizar la sesión.", 500);
            return ResponseEntity.status(500).body(error);
        }
    }

    /**
     * Gets sesiones today.
     *
     * @return the sesiones today
     */
    @GetMapping("/hoy") // -> http://localhost:8080/sesiones
    public ResponseEntity<?> getSesionesToday() {
        try {
            List<SesionDTO> sesiones = sesionService.getByDate();
            return ResponseEntity.ok(sesiones);
        } catch (NotFoundException e) {
            ErrorMsg error = new ErrorMsg("Not Found", e.getMessage(), 404);
            return ResponseEntity.status(404).body(error);
        } catch (Exception e) {
            ErrorMsg error = new ErrorMsg("Internal Server Error", "Error al obtener sesiones para hoy.", 500);
            return ResponseEntity.status(500).body(error);
        }
    }
}
