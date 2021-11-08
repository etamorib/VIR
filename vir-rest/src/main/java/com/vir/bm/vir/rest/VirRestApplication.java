package com.vir.bm.vir.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class VirRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirRestApplication.class, args);
	}

}
