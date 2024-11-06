package com.es.diecines.controller;

import com.es.diecines.dto.SesionDTO;
import com.es.diecines.service.SesionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/sesiones") // -> http://localhost:8080/sesiones
public class SesionesController {

    @Autowired
    private final SesionesService sesionService;

    public SesionesController(SesionesService sesionService) {
        this.sesionService = sesionService;
    }

    @GetMapping ("/") // -> http://localhost:8080/sesiones/
    public ResponseEntity<List<SesionDTO>> getAll(){
        if(sesionService.getAll() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sesionService.getAll());
    }

    @PostMapping ("/") // -> http://localhost:8080/sesiones/
    public ResponseEntity<SesionDTO> createSesion(@RequestBody SesionDTO sesionDTO) {
        if(sesionDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(sesionService.save(sesionDTO));
    }

    @DeleteMapping("/{id}") // -> http://localhost:8080/sesiones/1
    public ResponseEntity<Boolean> deleteByID(@PathVariable String id) {
        sesionService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}") // -> http://localhost:8080/sesiones/1
    public ResponseEntity<SesionDTO> update(@PathVariable String id, @RequestBody SesionDTO sesionDTO) {
        if(sesionDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(sesionService.update(id, sesionDTO));
    }

    @GetMapping("/hoy") // -> http://localhost:8080/sesiones/hoy
    public ResponseEntity<List<SesionDTO>> getSesionesToday() {
        List<SesionDTO> sesiones = sesionService.getByDate();
        return ResponseEntity.ok(sesiones);
    }

}
