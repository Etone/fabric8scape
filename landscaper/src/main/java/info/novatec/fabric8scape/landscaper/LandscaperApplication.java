package info.novatec.fabric8scape.landscaper;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class LandscaperApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandscaperApplication.class, args);
	}

}
