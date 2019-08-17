package com.omaru.storelocator;

import com.omaru.storelocator.util.cmd.CommandLineDataIngester;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.inject.Inject;

@SpringBootApplication
public class StoreLocatorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StoreLocatorApplication.class, args);
	}

	@Inject
	private CommandLineDataIngester commandLineDataIngester;
	@Override
	public void run(String... args) throws Exception {
		commandLineDataIngester.accept(args);
	}
}