package com.montecarlo.datatracker.config;

import com.montecarlo.datatracker.model.Metric;
import com.montecarlo.datatracker.model.MetricValue;
import com.montecarlo.datatracker.repository.MetricRepository;
import com.montecarlo.datatracker.repository.MetricValueRepository;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DatatrackerConfiguration {

  @Bean
  CommandLineRunner initDatabase(MetricRepository metricRepo, MetricValueRepository metricValueRepo) {

    return args -> {
      var metric1 = metricRepo.save(new Metric("BTC/USD", "btcusd"));
      //log.info("Preloading " + repository.save(new Metric("ETH/USD", "etcusd")));

        /*metricValueRepo.save(new MetricValue(1641161579, 45674.3, metric1));
        metricValueRepo.save(new MetricValue(1641164578, 46675.3, metric1));
        metricValueRepo.save(new MetricValue(1641166578, 48676.3, metric1));
        metricValueRepo.save(new MetricValue(1641168578, 49677.3, metric1));*/

    };
  }

  @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}