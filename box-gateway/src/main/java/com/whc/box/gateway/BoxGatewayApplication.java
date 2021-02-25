package com.whc.box.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BoxGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoxGatewayApplication.class, args);
    }
}
