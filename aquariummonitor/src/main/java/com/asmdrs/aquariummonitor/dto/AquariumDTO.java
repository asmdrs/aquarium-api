package com.asmdrs.aquariummonitor.dto;

import com.asmdrs.aquariummonitor.entities.Aquarium;
import com.asmdrs.aquariummonitor.entities.WaterType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@AllArgsConstructor
@Getter
public class AquariumDTO {
    private Long id;
    private String name;
    private Integer volume;
    private LocalDate lastReadingDate;
    private WaterType waterType;

    public AquariumDTO(Aquarium entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.volume = entity.getVolume();
        this.lastReadingDate = entity.getLastReadingDate();
        this.waterType = entity.getWaterType();
    }

}
