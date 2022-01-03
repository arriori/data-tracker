package com.montecarlo.datatracker.controller;

import java.util.List;
import java.util.Optional;

import com.montecarlo.datatracker.dto.MetricDto;
import com.montecarlo.datatracker.dto.MetricValueDto;
import com.montecarlo.datatracker.model.Metric;
import com.montecarlo.datatracker.model.MetricValue;
import com.montecarlo.datatracker.service.MetricService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/metrics")
public class MetricController {

    @Autowired
    private MetricService metricService;

    @Autowired
    private ModelMapper modelMapper;
  
    @GetMapping
    public List<MetricDto> findAllMetrics() {
        var metrics = this.metricService.findAllMetrics();
        return metrics.stream().map(this::convertMetricToDto).toList();
    }
 
   /* @GetMapping("/{id}")
    public ResponseEntity<MetricDto> findMetricById(@PathVariable(value = "id") long id) {
        
        Optional<Metric> metric = this.metricService.findMetricById(id);

        if(metric.isPresent()) {
            return ResponseEntity.ok().body(convertToDto(metric.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @GetMapping("/{id}/quotes")
    public ResponseEntity<List<MetricValueDto>> getMetricValues(@PathVariable(value = "id") long id, @RequestParam("from") long from, @RequestParam("to") long to) {
        List<MetricValue> values = this.metricService.getMetricValues(id, from, to);

        if (values != null) {
            List<MetricValueDto> dtoValues = values.stream().map(this::convertValueToDto).toList();
            return ResponseEntity.ok().body(dtoValues);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/rank")
    public ResponseEntity<String> getMetricRank(@PathVariable(value = "id") long id) {
        
        String metricRank = this.metricService.getRank(id);

        if(metricRank != null) {
            return ResponseEntity.ok().body(metricRank);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private MetricDto convertMetricToDto(Metric metric) {
        MetricDto metricDto = modelMapper.map(metric, MetricDto.class);
        return metricDto;
    }

    private MetricValueDto convertValueToDto(MetricValue value){
        return modelMapper.map(value, MetricValueDto.class);
    }
}
