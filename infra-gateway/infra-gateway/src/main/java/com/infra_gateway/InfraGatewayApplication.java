package com.infra_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class InfraGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfraGatewayApplication.class, args);
	}

}
