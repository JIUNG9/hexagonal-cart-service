package com.bit.lotte.fresh.cart.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = {"com.bit.lotte.fresh.cart.data.repository"})
@EntityScan(basePackages = {"com.bit.lotte.fresh.cart.data.entity"})
@ComponentScan("com.bit.lotte.fresh.cart.service")
@ComponentScan("com.bit.lotte.fresh.cart.service.config")
@ComponentScan("com.bit.lotte.fresh.cart.service.util")
@ComponentScan("com.bit.lotte.fresh.cart.data")
@ComponentScan("com.bit.lotte.fresh.cart.message")
@SpringBootApplication(scanBasePackages = "com.bit.lotte.fresh.cart.app")
public class CartApplication {

  public static void main(String[] args) {
    SpringApplication.run(CartApplication.class, args);

  }
}
