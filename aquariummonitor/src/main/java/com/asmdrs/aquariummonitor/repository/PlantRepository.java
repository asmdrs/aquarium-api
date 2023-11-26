package com.asmdrs.aquariummonitor.repository;

import com.asmdrs.aquariummonitor.entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Long> {
}
