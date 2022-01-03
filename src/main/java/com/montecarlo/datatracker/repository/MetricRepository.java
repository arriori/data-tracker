package com.montecarlo.datatracker.repository;

import com.montecarlo.datatracker.model.Metric;

import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.JpaRepository;

@Component
public interface MetricRepository extends JpaRepository<Metric, Long> {
    
}

