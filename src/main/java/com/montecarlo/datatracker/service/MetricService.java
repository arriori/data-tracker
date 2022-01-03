package com.montecarlo.datatracker.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.montecarlo.datatracker.model.Metric;
import com.montecarlo.datatracker.model.MetricValue;
import com.montecarlo.datatracker.repository.MetricRepository;
import com.montecarlo.datatracker.repository.MetricValueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MetricService {
    
    @Autowired
    private MetricRepository metricRepository;

    @Autowired
    private MetricValueRepository metricValueRepository;

    public List<Metric> findAllMetrics() {
        return this.metricRepository.findAll();
    }

    public Optional<Metric> findMetricById(long id)
    {
        return this.metricRepository.findById(id);
    }

    public void addMetricValue(Metric metric, long timestamp, double value) {
        metricValueRepository.save(new MetricValue(timestamp, value, metric));
        
       // var std = CalculateLastDaySD(metricId);
       // _metricRepository.UpdateMetricSD(metricId, std);
    }



    /* private long CalculateLastDaySD(string metricId)
    {
        return -1;
    }
 */

    public List<MetricValue> getMetricValues(long metricId, long from, long to) {

        var metric = this.metricRepository.findById(metricId);
        if (metric.isPresent())
        {
            return this.metricValueRepository.findByMetric(metric.get());
        }

        return null;
    }

    public String getRank(long metricId) {
        var m = this.metricRepository.findById(metricId).get();

        var dss = this.metricRepository.findAll();
        dss.sort(Comparator.comparing(Metric::getLastDayStandardDeviation).reversed());

        return String.format("%s / %s",dss.indexOf(m) ,dss.size());
    }


}
