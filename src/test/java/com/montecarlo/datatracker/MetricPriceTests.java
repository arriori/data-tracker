package com.montecarlo.datatracker;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.montecarlo.datatracker.tasks.CryptoWatchClient;

import org.junit.jupiter.api.Test;

public class MetricPriceTests {
    private CryptoWatchClient cryptoClient = new CryptoWatchClient();

	@Test
	public void shouldGetMetricValue() {
    	
         double value = cryptoClient.getLatestCurrencyValue("btcusd");
         assertTrue(value > 0);
	}
}
