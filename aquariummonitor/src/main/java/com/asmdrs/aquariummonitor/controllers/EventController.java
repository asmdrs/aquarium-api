package com.asmdrs.aquariummonitor.controllers;

import com.asmdrs.aquariummonitor.dto.EventDTO;
import com.asmdrs.aquariummonitor.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value="/events")
public class EventController {
    @Autowired
    private EventService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventDTO> findById(@PathVariable Long id){
        EventDTO eventDTO = service.getById(id);
        return ResponseEntity.ok(eventDTO);
    }

    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable){
        Page<EventDTO> eventDTO = service.getAll(pageable);
        return ResponseEntity.ok(eventDTO);
    }

    @PostMapping
    public ResponseEntity<EventDTO> insertNewEvent(@RequestBody EventDTO event){
        EventDTO eventDTO = service.insert(event);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(eventDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(eventDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventDTO> change(@PathVariable Long id, @RequestBody EventDTO event){
        event = service.update(id, event);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEventById (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
