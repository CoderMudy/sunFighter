package com.sun.fighter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@Slf4j
public class SunFighterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunFighterApplication.class, args);
		log.info("http://localhost:8080");
	}
}
