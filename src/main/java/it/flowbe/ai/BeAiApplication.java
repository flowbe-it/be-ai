package it.flowbe.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"it.flowbe", "it.flowbe.tenetcommonlibrary"})
@EnableJpaRepositories(basePackages = {"it.flowbe"})
@EntityScan(basePackages = {"it.flowbe"})
public class BeAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeAiApplication.class, args);
	}

}
