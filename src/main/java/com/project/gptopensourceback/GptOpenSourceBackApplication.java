package com.project.gptopensourceback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GptOpenSourceBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(GptOpenSourceBackApplication.class, args);
	}

}
