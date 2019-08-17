package com.omaru.storelocator.resource;

import com.omaru.storelocator.domain.model.Store;
import org.junit.jupiter.api.Test;
import org.springframework.data.geo.GeoPage;

public class GeoPageStoreResourceAssemblerShould {
    @Test
    void beAbleToAssembleFromGeoPageStore(){
        GeoPageStoreResourceAssembler geoPageStoreResourceAssembler = new GeoPageStoreResourceAssembler();
    }
}
