package com.velas.candil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VelasCandilApplication {

	public static void main(String[] args) {
		SpringApplication.run(VelasCandilApplication.class, args);
	}
}
