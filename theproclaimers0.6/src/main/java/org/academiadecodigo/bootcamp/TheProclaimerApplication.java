package org.academiadecodigo.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TheProclaimerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheProclaimerApplication.class, args);
	}
}
