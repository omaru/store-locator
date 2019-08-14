package com.omaru.storelocator.service;

import com.omaru.storelocator.model.Store;
import com.omaru.storelocator.repository.StoreRepository;
import com.omaru.storelocator.util.MockUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
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
		Collection<Store> stores = MockUtil.getStores();
		Consumer<Store> saveStores = s->{s.setId(null);storeService.save(s);};
		stores.stream().forEach(saveStores);

	}
	@Test	
	void beAbleToRetrieveStoreByUUID(){
		Optional<Store> store = storeService.get("1");
		assertTrue(store.isPresent());
		assertThat(store.get().getUuid(),is("1"));
	}
	@Test
	void beAbleToRetrieveNStoresByLocation(){
		Point storeSixReferenceLocation = new Point(51.275006, 3.444601);
		Pageable firstFive = new PageRequest(1,5);
		Page<Store> stores = storeService.getStoresByAddressLocationNear(storeSixReferenceLocation,firstFive);
		assertThat(stores.getContent(),hasSize(5));
	}
}
