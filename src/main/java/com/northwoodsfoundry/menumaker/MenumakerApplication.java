package com.northwoodsfoundry.menumaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MenumakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MenumakerApplication.class, args);
	}

}
