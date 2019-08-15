package com.omaru.storelocator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;

@SpringBootApplication
public class StoreLocatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreLocatorApplication.class, args);
	}

}