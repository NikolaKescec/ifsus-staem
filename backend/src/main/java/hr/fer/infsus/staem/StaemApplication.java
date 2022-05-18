package hr.fer.infsus.staem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class StaemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaemApplication.class, args);
	}

}
