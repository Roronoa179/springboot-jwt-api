package com.acme.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.acme.app")
public class BackendPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendPracticeApplication.class, args);
	}

}
