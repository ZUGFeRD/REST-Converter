// Copyright (c) 2023 Jochen Stärk, see LICENSE file
package org.mustangproject.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class })
public class MainServerApplication {

	private static final Logger LOGGER = LogManager.getLogger(MainServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MainServerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	ApplicationRunner applicationRunner(Environment environment) {
//		System.getenv().forEach((key, value) -> System.out.println(key + ": " + value));
		return args ->
				LOGGER.info(
						"Application Properties env:{} ",
						environment.getProperty("message-from-application-properties"));
	}

}
