package com.omaru.storelocator.resource;

import com.omaru.storelocator.controller.StoreController;
import com.omaru.storelocator.domain.model.Store;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class StoreResourceAssembler extends ResourceAssemblerSupport<Store,StoreResource> {
    public StoreResourceAssembler() {
        super(StoreController.class, StoreResource.class);
    }
    @Override
    public StoreResource toResource(Store entity) {
        return null;
    }
}
