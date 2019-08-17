package com.omaru.storelocator.resource;

import com.omaru.storelocator.controller.StoreController;
import com.omaru.storelocator.domain.model.Store;
import org.springframework.data.geo.GeoPage;
import org.springframework.data.geo.GeoResult;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GeoPageStoreResourceAssembler extends ResourceAssemblerSupport<GeoPage<Store>,GeoPageStoreResource> {
    private final StoreResourceAssembler storeResourceAssembler;
    public GeoPageStoreResourceAssembler(StoreResourceAssembler storeResourceAssembler){
        super(StoreController.class, GeoPageStoreResource.class);
        this.storeResourceAssembler  = storeResourceAssembler;
    }

    @Override
    public GeoPageStoreResource toResource(GeoPage<Store> geoPage) {
        GeoPageStoreResource resource = new GeoPageStoreResource(geoPage);
        List<GeoResult<Store>> content = geoPage.getContent();
        List<StoreResource> storeResources = content.stream().map(geoResult->
                storeResourceAssembler.toResource(geoResult.getContent(),geoResult.getDistance()))
        .collect(Collectors.toList());
        resource.setStoreResources(storeResources);
        return resource;
    }
}
