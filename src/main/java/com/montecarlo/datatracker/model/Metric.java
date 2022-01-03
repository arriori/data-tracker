package com.montecarlo.datatracker.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String code;
    private Long lastDayStandardDeviation;

    @OneToMany(mappedBy = "metric", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MetricValue> values;
    
    public Metric() {
    }

    public Metric(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getLastDayStandardDeviation() {
        return lastDayStandardDeviation;
    }

    public void setLastDayStandardDeviation(Long lastDayStandardDeviation) {
        this.lastDayStandardDeviation = lastDayStandardDeviation;
    }

    public List<MetricValue> getValues() {
        return values;
    } 

    public void setValues(List<MetricValue> values) {
        this.values = values;
    } 
}