package com.wordle.marcus.spring_wordle;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class SpringWordleApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringWordleApplication.class, args);
	}

}
