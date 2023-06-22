package com.madeeasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootService1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootService1Application.class, args);
	}

	@GetMapping("/service-1")
	public String service1() {
        return "service-1";
    }
}
