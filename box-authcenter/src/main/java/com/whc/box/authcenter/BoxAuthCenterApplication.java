package com.whc.box.authcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BoxAuthCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoxAuthCenterApplication.class,args);
    }
}
