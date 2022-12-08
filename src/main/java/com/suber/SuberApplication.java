package com.suber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SuberApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuberApplication.class, args);
	}

}
