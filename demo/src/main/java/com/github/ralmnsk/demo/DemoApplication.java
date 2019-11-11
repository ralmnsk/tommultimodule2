package com.github.ralmnsk.demo;

import com.github.ralmnsk.demo.config.WebConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = {"com.github.ralmnsk.service","com.github.ralmnsk.dao",
		"com.github.ralmnsk.model", "com.github.ralmnsk.demo"})
@EnableJpaRepositories(basePackages = "com.github.ralmnsk.dao")
@EntityScan(basePackages = "com.github.ralmnsk.model")
//@EnableAutoConfiguration
//@Import({WebConfiguration.class,ServletInitializer.class})
public class DemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
