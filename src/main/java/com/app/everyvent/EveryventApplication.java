package com.app.everyvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;

@SpringBootApplication
public class EveryventApplication {

	public static void main(String[] args) {
		SpringApplication.run(EveryventApplication.class, args);
	}
}
