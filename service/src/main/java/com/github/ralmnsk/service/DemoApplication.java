package com.github.ralmnsk.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.github.ralmnsk.service","com.github.ralmnsk.dao","com.github.ralmnsk.dao.user",
		"com.github.ralmnsk.model", "com.github.ralmnsk.demo"})

public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
