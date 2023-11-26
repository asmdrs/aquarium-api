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

    private int qtdPh;

    private int qtdNitrito;

    private int qtdNitrato;

    private int valorKh;

    private float temperature;

    @ManyToOne
    @JoinColumn(name = "aquarium_id") // Nome da coluna que representa o relacionamento
    private Aquarium aquarium;
}
