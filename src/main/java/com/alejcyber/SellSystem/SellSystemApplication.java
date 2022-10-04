package com.alejcyber.SellSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication 
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.alejcyber.SellSystem"})
@EnableJpaRepositories(basePackages="com.alejcyber.SellSystem.repositories")
@EnableTransactionManagement
@EntityScan(basePackages="com.alejcyber.SellSystem.entities")
public class SellSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellSystemApplication.class, args);
	}

}
