package com.example.issuetracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IssuetrackerApplication {
	private static final Logger logger = LoggerFactory.getLogger(IssuetrackerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(IssuetrackerApplication.class, args);
		logger.info("Application started successfully!");
	}

}
