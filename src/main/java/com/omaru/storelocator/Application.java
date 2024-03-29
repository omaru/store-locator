package com.omaru.storelocator;

import com.omaru.storelocator.util.cmd.CommandLineDataIngester;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.inject.Inject;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Inject
	public Application(CommandLineDataIngester commandLineDataIngester) {
		this.commandLineDataIngester = commandLineDataIngester;
	}
	@Configuration
	@EnableSwagger2
	static class SwaggerConfig {
		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.omaru.storelocator.controller"))
					.paths(PathSelectors.any())
					.build();
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private final CommandLineDataIngester commandLineDataIngester;
	@Override
	public void run(String... args) throws Exception {
		commandLineDataIngester.accept(args);
	}
}