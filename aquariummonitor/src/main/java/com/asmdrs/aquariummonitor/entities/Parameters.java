package com.asmdrs.aquariummonitor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_parameters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parameters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer qtdPh;

    private Integer qtdNitrito;

    private Integer qtdNitrato;

    private Integer valorKh;

    private Double temperature;

    @ManyToOne
    @JoinColumn(name = "aquarium_id")
    private Aquarium aquarium;
}
