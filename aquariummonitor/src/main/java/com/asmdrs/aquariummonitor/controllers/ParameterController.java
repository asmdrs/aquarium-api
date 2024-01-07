package com.asmdrs.aquariummonitor.controllers;

import com.asmdrs.aquariummonitor.dto.ParametersDTO;
import com.asmdrs.aquariummonitor.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value ="/paramaters")
public class ParameterController {
    @Autowired
    private ParameterService service;
    private static final Logger LOGGER = Logger.getLogger(ParameterController.class.getName());

    @GetMapping(value = "/{id}")
    public ResponseEntity<ParametersDTO> findById(@PathVariable Long id){
        try {
            ParametersDTO parametersDTO = service.findById(id);
            return ResponseEntity.ok(parametersDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ocorreu um erro ao recuperar leitura de parametros por ID.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping
    public ResponseEntity<Page<ParametersDTO>> findAll(Pageable pageable){
        try {
            Page<ParametersDTO> parametersDTOPage = service.findAll(pageable);
            return ResponseEntity.ok(parametersDTOPage);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ocorreu um erro ao recuperar todas as leituras de parametros.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<ParametersDTO> insertNewParametersReading(@RequestBody ParametersDTO params){
        try {
            ParametersDTO parametersDTO = service.insert(params);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(parametersDTO.getId()).toUri();
            return ResponseEntity.created(uri).body(parametersDTO);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ocorreu um erro ao inserir nova leitura de parametro.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ParametersDTO> change(@PathVariable Long id, @RequestBody ParametersDTO params){
        try {
            params = service.update(id, params);
            return ResponseEntity.ok(params);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ocorreu um erro ao atualizar leitura de parametro selecionado.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteParameterReadingById(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Ocorreu um erro ao excluir leitura de parametro selecionado.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
