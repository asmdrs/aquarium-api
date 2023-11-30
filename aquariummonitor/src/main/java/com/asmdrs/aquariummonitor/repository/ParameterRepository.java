package com.asmdrs.aquariummonitor.repository;

import com.asmdrs.aquariummonitor.entities.Parameters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterRepository extends JpaRepository<Parameters, Long> {
}
