package com.omaru.storelocator.service;

import com.omaru.storelocator.model.Store;
import com.omaru.storelocator.repository.StoreRepository;
import com.omaru.storelocator.util.MockUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.Matchers.*;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
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
		Collection<Store> stores = MockUtil.getStores();
		Consumer<Store> saveStores = s->{s.setId(null);storeService.save(s);};
		stores.stream().forEach(saveStores);

	}
	@Test	
	void beAbleToRetrieveStoreByUUID(){
		Optional<Store> store = storeService.get("EOgKYx4XFiQAAAFJa_YYZ4At");
		assertTrue(store.isPresent());
		assertThat(store.get().getUuid(),is("EOgKYx4XFiQAAAFJa_YYZ4At"));
	}
	@Test
	void beAbleToRetrieveNStoresByLocation(){
		Point referenceLocation = new Point(52.51790, 13.41239);
		Distance oneKilometer = new Distance(1, Metrics.KILOMETERS);
		GeoResults<Store> stores = storeService.getStoresByAddressLocationNear(referenceLocation,oneKilometer);
		assertThat(stores.getContent(), hasSize(1));
		Distance distanceToFirstStore = stores.getContent().get(0).getDistance();
		assertThat(distanceToFirstStore.getMetric(), is(Metrics.KILOMETERS));
		assertThat(distanceToFirstStore.getValue(), closeTo(0.862, 0.001));

	}
}
