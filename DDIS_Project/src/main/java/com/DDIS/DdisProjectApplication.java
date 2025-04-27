package com.DDIS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.DDIS")
public class DdisProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdisProjectApplication.class, args);
	}

}
