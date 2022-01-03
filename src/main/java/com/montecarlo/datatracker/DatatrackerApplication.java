package com.montecarlo.datatracker;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DatatrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatatrackerApplication.class, args);
	}

	

}
