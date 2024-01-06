package com.asmdrs.aquariummonitor.dto;

import com.asmdrs.aquariummonitor.entities.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private AquariumDTO aquarium;

    public EventDTO (Event entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.aquarium = new AquariumDTO(entity.getAquarium());
    }
}
