package com.northwoodsfoundry.menumaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
@EnableAsync
public class MenumakerApplication {

	private static final Logger logger = LoggerFactory.getLogger(MenumakerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MenumakerApplication.class, args);
	}

}
