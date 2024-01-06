package com.asmdrs.aquariummonitor.dto;

import com.asmdrs.aquariummonitor.entities.Parameters;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ParametersDTO {
    private Long id;
    private Integer qtdPh;
    private Integer qtdNitrito;
    private Integer qtdNitrato;
    private Integer valorKh;
    private Double temperature;
    private AquariumDTO aquarium;

    public ParametersDTO (Parameters entity){
        this.id = entity.getId();
        this.qtdPh = entity.getQtdPh();
        this.qtdNitrito = entity.getQtdNitrito();
        this.qtdNitrato = entity.getQtdNitrato();
        this.temperature = entity.getTemperature();
        this.valorKh = entity.getValorKh();
        this.aquarium = new AquariumDTO(entity.getAquarium());
    }
}
