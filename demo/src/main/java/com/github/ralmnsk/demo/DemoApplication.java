package com.github.ralmnsk.demo;

import com.github.ralmnsk.dao.connection.JpaConfig;
import com.github.ralmnsk.demo.config.WebConfiguration;
import com.github.ralmnsk.demo.security.SecurityConfig;
import com.github.ralmnsk.service.ServiceConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;

@Slf4j
@Configuration
@SpringBootApplication
//@ComponentScan(basePackages = {"com.github.ralmnsk.demo"})
@Import({WebConfiguration.class,
		SecurityConfig.class, JpaConfig.class,
		ServiceConfiguration.class})


public class DemoApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
//		System.setProperty("server.servlet.context-path", "/tomapp");
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}


}
