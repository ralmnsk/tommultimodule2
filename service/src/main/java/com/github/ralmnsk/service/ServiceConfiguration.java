package com.github.ralmnsk.service;

import com.github.ralmnsk.dao.connection.JpaConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.github.ralmnsk.service")
//@Import(JpaConfig.class)
public class ServiceConfiguration {
}
