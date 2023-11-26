package com.asmdrs.aquariummonitor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tb_aquarium")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aquarium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "aquarium", cascade = CascadeType.ALL)
    private List<Plant> flora = new ArrayList<>();

    @OneToMany(mappedBy = "aquarium", cascade = CascadeType.ALL)
    private List<Fish> fauna = new ArrayList<>();

    @OneToMany(mappedBy = "aquarium", cascade = CascadeType.ALL)
    private List<Parameters> controlReadings = new ArrayList<>();

    private String name;

    private int volume;

    private Date lastReadingDate;

    private WaterType waterType;

    @OneToMany(mappedBy = "aquarium", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();


}
