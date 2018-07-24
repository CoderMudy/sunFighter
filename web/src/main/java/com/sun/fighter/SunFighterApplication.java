package com.sun.fighter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class SunFighterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunFighterApplication.class, args);
	}
}
