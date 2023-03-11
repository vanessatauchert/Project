package com.aplicacao.ibm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DasfnApplication {

	public static void main(String[] args) {
		SpringApplication.run(DasfnApplication.class, args);
	}

}
