package com.maruf.i18n;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		Environment env = app.run(args).getEnvironment();
		String protocol = "http";
		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{} v{}' is running :) \n\t" +
						"Access URL: \t{}://localhost:{}\n\t" +
						"Profile(s): \t{}\n----------------------------------------------------------",
				env.getProperty("app.name"),
				env.getProperty("app.version"),
				protocol,
				env.getProperty("server.port"),
				env.getActiveProfiles());
	}
}
