package com.DDIS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.DDIS")
@EnableJpaRepositories(basePackages = "com.DDIS")  // ✅ 여기도 필요함!
@EntityScan(basePackages = "com.DDIS")             // ✅ Entity도 함께!
public class DdisProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdisProjectApplication.class, args);
	}

}
