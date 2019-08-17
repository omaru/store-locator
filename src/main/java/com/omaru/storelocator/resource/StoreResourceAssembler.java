package com.omaru.storelocator.resource;

import com.omaru.storelocator.controller.StoreController;
import com.omaru.storelocator.domain.model.Location;
import com.omaru.storelocator.domain.model.Store;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class StoreResourceAssembler extends ResourceAssemblerSupport<Store,StoreResource> {
    public StoreResourceAssembler() {
        super(StoreController.class, StoreResource.class);
    }
    @Override
    public StoreResource toResource(Store store) {
        StoreResource resource=new StoreResource(store);
        addRelations(resource,store);
        return resource;
    }

    private void addRelations(StoreResource resource,Store store) {
        resource.add(linkTo(methodOn(StoreController.class).getStore(store.getId())).withSelfRel());
        resource.add(linkTo(StoreController.class).slash("?latitude={latitude}&longitude={longitude}")
                .withRel(Relations.LOCATION.getRelation()));
    }
}