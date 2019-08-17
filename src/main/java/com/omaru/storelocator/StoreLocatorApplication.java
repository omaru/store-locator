package com.omaru.storelocator;

import com.omaru.storelocator.util.cmd.CommandLineDataIngester;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.inject.Inject;

@SpringBootApplication
public class StoreLocatorApplication implements CommandLineRunner {

	public StoreLocatorApplication(CommandLineDataIngester commandLineDataIngester) {
		this.commandLineDataIngester = commandLineDataIngester;
	}

	public static void main(String[] args) {
		SpringApplication.run(StoreLocatorApplication.class, args);
	}

	private final CommandLineDataIngester commandLineDataIngester;
	@Override
	public void run(String... args) throws Exception {
		commandLineDataIngester.accept(args);
	}
}