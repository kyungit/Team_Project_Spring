package com.dormitory;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DormitoryApplication {
	public static void main(String[] args) {
		System.out.println("DB_URL: " + System.getenv("DB_URL"));
				
		Properties props = System.getProperties();
		props.list(System.out);

		SpringApplication.run(DormitoryApplication.class, args);
	}

}
