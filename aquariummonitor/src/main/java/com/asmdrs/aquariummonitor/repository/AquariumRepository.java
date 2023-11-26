package com.asmdrs.aquariummonitor.repository;

import com.asmdrs.aquariummonitor.entities.Aquarium;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AquariumRepository extends JpaRepository<Aquarium, Long> {

}
