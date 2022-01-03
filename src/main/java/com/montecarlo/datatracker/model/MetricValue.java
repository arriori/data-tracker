package com.montecarlo.datatracker.model;

import javax.persistence.*;

@Entity
public class MetricValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long timestamp;
    private double value;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "metric_id", nullable = false)
    private Metric metric;
    
    public MetricValue() { }

    public MetricValue(long timestamp, double value, Metric metric) {
        this.timestamp = timestamp;
        this.value = value;
        this.metric = metric;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
}
