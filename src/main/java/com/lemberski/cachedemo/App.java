package com.lemberski.cachedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class App {

	static final String TASKS_CACHE = "tasks";

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
