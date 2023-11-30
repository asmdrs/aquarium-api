package com.asmdrs.aquariummonitor.services;

import com.asmdrs.aquariummonitor.dto.EventDTO;
import com.asmdrs.aquariummonitor.dto.PlantDTO;
import com.asmdrs.aquariummonitor.entities.Aquarium;
import com.asmdrs.aquariummonitor.entities.Event;
import com.asmdrs.aquariummonitor.entities.Plant;
import com.asmdrs.aquariummonitor.repository.AquariumRepository;
import com.asmdrs.aquariummonitor.repository.EventRepository;
import com.asmdrs.aquariummonitor.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private AquariumRepository aquariumRepository;

    @Transactional(readOnly = true)
    public Page<EventDTO> getAll(Pageable pageable){
        Page<Event> eventList = eventRepository.findAll(pageable);
        return eventList.map( e -> new EventDTO(e));
    }

    @Transactional(readOnly = true)
    public EventDTO getById(Long id){
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(("Recuso não encontrado"))
        );
        return new EventDTO(event);
    }

    @Transactional(readOnly = false)
    public EventDTO insert(EventDTO event){
        Event entity = new Event();
        copyDtoToEntity(event, entity);
        Aquarium aquarium = aquariumRepository.getReferenceById(event.getAquarium().getId());

        entity.setAquarium(aquarium);
        entity = eventRepository.save(entity);

        return new EventDTO(entity);
    }

    @Transactional(readOnly = false)
    public EventDTO update(Long id, EventDTO event){
        Event entity = eventRepository.getReferenceById(id);
        copyDtoToEntity(event, entity);
        entity= eventRepository.save(entity);
        return new EventDTO(entity);
    }

    @Transactional
    public void delete(Long id){
        eventRepository.deleteById(id);
    }


    private void copyDtoToEntity(EventDTO event, Event entity) {
        entity.setName(event.getName());
        entity.setDescription(event.getDescription());
    }
}