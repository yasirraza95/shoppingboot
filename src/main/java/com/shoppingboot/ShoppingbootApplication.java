package com.shoppingboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.shoppingboot")
public class ShoppingbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingbootApplication.class, args);
	}
}