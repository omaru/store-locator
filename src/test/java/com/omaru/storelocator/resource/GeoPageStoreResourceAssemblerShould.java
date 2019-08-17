package com.omaru.storelocator.resource;

import com.omaru.storelocator.domain.model.Store;
import org.junit.jupiter.api.Test;
import org.springframework.data.geo.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.omaru.storelocator.util.MockUtil.getStores;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GeoPageStoreResourceAssemblerShould {
    @Test
    void beAbleToAssembleFromGeoPageStore(){
        GeoPageStoreResourceAssembler geoPageStoreResourceAssembler = new GeoPageStoreResourceAssembler(new StoreResourceAssembler());
        List<GeoResult<Store>> results = getStores().stream()
                .map(s->new GeoResult<>(s,new Distance(1d, Metrics.KILOMETERS))).collect(Collectors.toList());
        GeoPage<Store> geoPage = new GeoPage<>(new GeoResults(results));
        GeoPageStoreResource geoPageStoreResource = geoPageStoreResourceAssembler.toResource(geoPage);
        assertThat(geoPageStoreResource).isNotNull();
    }
}
