package com.omaru.storelocator.resource;

import com.omaru.storelocator.controller.StoreController;
import com.omaru.storelocator.domain.model.Store;
import org.springframework.data.geo.GeoPage;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class GeoPageStoreResourceAssembler extends ResourceAssemblerSupport<GeoPage<Store>,GeoPageStoreResource> {
    public GeoPageStoreResourceAssembler(){
        super(StoreController.class, GeoPageStoreResource.class);
    }
    @Override
    public GeoPageStoreResource toResource(GeoPage<Store> geoPage) {
        return null;
    }
}
