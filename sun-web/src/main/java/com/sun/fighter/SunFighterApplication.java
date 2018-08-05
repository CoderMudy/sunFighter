package com.sun.fighter;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class SunFighterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunFighterApplication.class, args);
		System.out.printf("http://localhost:8080");
	}
}
