package com.app;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class DentistApplication {

	private static final Logger LOGGER = Logger.getLogger(DentistApplication.class);

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		LOGGER.debug("Starting running dentistApplication");
		SpringApplication.run(DentistApplication.class, args);
	}

}
