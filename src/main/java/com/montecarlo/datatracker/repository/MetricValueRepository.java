package com.montecarlo.datatracker.repository;

import java.util.List;

import com.montecarlo.datatracker.model.Metric;
import com.montecarlo.datatracker.model.MetricValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricValueRepository extends JpaRepository<MetricValue, Long>{
 
    List<MetricValue> findByMetric(Metric metric);  
}

