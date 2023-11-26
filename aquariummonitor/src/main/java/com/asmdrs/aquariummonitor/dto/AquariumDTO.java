package com.asmdrs.aquariummonitor.dto;

import com.asmdrs.aquariummonitor.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Getter
public class AquariumDTO {
    private Long id;
    private String name;
    private int volume;
    private Date lastReadingDate;
    private WaterType waterType;

    public AquariumDTO(Aquarium entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.volume = entity.getVolume();
        this.lastReadingDate = entity.getLastReadingDate();
        this.waterType = entity.getWaterType();
    }

}
