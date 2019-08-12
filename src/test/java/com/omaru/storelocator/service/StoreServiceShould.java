package com.omaru.storelocator.service;

import com.omaru.storelocator.model.Store;
import com.omaru.storelocator.repository.StoreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class StoreServiceShould {
	private StoreService storeService;
	@Inject
	private StoreRepository storeRepository;
	@BeforeEach
	void setUp(){
		storeService = new DefaultStoreService(storeRepository);
	}
	@Test	
	void beAbleToRetrieveStoreByUUID(){
		Optional<Store> store = storeService.get("EOgKYx4XFiQAAAFJa_YYZ4At");
		assertTrue(store.isPresent());
		assertEquals(store.get().getUuid(),"EOgKYx4XFiQAAAFJa_YYZ4At");
	}
}
