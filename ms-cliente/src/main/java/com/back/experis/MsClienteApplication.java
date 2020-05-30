package com.back.experis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsClienteApplication.class, args);
	}

}
