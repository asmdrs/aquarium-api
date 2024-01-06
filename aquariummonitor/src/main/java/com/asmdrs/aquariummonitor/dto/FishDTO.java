package com.asmdrs.aquariummonitor.dto;

import com.asmdrs.aquariummonitor.entities.Fish;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FishDTO {
    private Long id;
    private String type;
    private Integer age;
    private String name;
    private AquariumDTO aquarium;

    public FishDTO (Fish entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.age = entity.getAge();
        this.type = entity.getType();
        this.aquarium = new AquariumDTO(entity.getAquarium());
    }
}
