package com.montecarlo.datatracker.tasks;

import java.time.Instant;
import java.util.List;

import com.montecarlo.datatracker.model.Metric;
import com.montecarlo.datatracker.service.MetricService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MetricsTasks {

	@Autowired
   	private MetricService metricService;

	@Autowired
	private CryptoWatchClient cryptoClient;

	// TODO: move to properties.
	@Scheduled(fixedRate = 60000)
	public void UpdateTrackedMetrics() {
		List<Metric> metrics = this.metricService.findAllMetrics();

		for (Metric metric: metrics) {
			long measureTime = Instant.now().toEpochMilli();
			double measureValue = cryptoClient.getLatestCurrencyValue(metric.getCode());

			this.metricService.addMetricValue(metric, measureTime, measureValue);
		}

	}

	@Scheduled(cron = "@daily")
    public void CalculateStdev() throws InterruptedException {
       // GetTrackedMetrics
       // Calculate 
    }
}
