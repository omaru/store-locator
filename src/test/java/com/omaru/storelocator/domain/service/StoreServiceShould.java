package com.omaru.storelocator.domain.service;

import com.omaru.storelocator.domain.model.Store;
import com.omaru.storelocator.util.MockUtil;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StoreServiceShould {
	@Inject
	private StoreService storeService;
	private Collection<Store> stores = MockUtil.getStores();
	@BeforeEach
	void setUp(){
		storeService.deleteAll();
		Consumer<Store> saveStores = s->{s.setId(null);storeService.save(s);};
		stores.stream().forEach(saveStores);

	}
	@Test	
	void beAbleToRetrieveStoreByUUID(){
		Optional<Store> store = storeService.get("1");
		assertTrue(store.isPresent());
		assertThat(store.get().getUuid()).isEqualTo("1");
	}
	@Test
	void beAbleToRetrieveNStoresByLocation(){
		Store firstStoreToBeFound = stores.iterator().next();
		Point firstStoreReferenceLocation = firstStoreToBeFound.getLocation().getLocation();
		PageRequest request = PageRequest.of(0,5);
		GeoPage<Store> stores = storeService.getStoresByAddressLocationNear(firstStoreReferenceLocation,request);
		GeoResult<Store> firstGeoResult = stores.getContent().get(0);
		assertThat(stores.getContent())
				.hasSize(5);
		assertThat(firstGeoResult.getContent().getSapStoreID())
				.isEqualTo(firstStoreToBeFound.getSapStoreID());
		assertThat(firstGeoResult.getDistance().getValue())
				.isCloseTo(0.0, Percentage.withPercentage(0.01));
	}
	@Test
	void beAbleToRetrieveNStoresByLocationBetweenAGivenDistance(){
		Distance oneKilometer = new Distance(1, Metrics.KILOMETERS);
		Point referenceLocation = new Point(51.770961,4.613171);
		PageRequest request = PageRequest.of(0,5);
		GeoResults<Store> stores = storeService.getStoresByAddressLocationNear(referenceLocation,oneKilometer);
		assertThat(stores).hasSize(2);
		Distance firstStoreDistance =stores.getContent().get(0).getDistance();
		assertThat(firstStoreDistance.getValue()).isCloseTo(0.87334,Percentage.withPercentage(0.012));
	}
}
