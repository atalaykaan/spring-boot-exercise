package com.atalaykaan.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(
/*
        scanBasePackages = {"com.atalaykaan.springcoredemo",
                            "com.atalaykaan.util"}
*/
)

public class SpringcoredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}

}
