package com.asmdrs.aquariummonitor.dto;

import com.asmdrs.aquariummonitor.entities.Plant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlantDTO {
    private Long id;
    private String name;
    private String description;
    private AquariumDTO aquarium;

    public PlantDTO(Plant entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.aquarium = new AquariumDTO(entity.getAquarium());
    }
}
