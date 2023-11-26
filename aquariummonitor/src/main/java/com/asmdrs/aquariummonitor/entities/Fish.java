package com.asmdrs.aquariummonitor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_fish")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private int age;

    private String name;
    @ManyToOne
    @JoinColumn(name = "aquarium_id") // Nome da coluna que representa o relacionamento
    private Aquarium aquarium;
}
