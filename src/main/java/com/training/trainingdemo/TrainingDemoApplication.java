package com.training.trainingdemo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrainingDemoApplication {

	@Bean
	public ObjectMapper getObjectMapper(){
		ObjectMapper mapper= new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		return mapper;
	}
	public static void main(String[] args) {
		SpringApplication.run(TrainingDemoApplication.class, args);
	}

}
