package com.omaru.storelocator.resource;

import com.omaru.storelocator.controller.StoreController;
import com.omaru.storelocator.domain.model.Store;
import org.springframework.data.geo.Distance;
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
    public StoreResource toResource(Store store, Distance distance){
        StoreResource resource = toResource(store);
        resource.setDistanceFromLocation(distance);
        return resource;
    }
    private void addRelations(StoreResource resource,Store store) {
        resource.add(linkTo(methodOn(StoreController.class).getStore(store.getId())).withSelfRel());
        resource.add(linkTo(methodOn(StoreController.class).getStores(store.getLocation().getLocation().getX(),
                store.getLocation().getLocation().getY())).withRel(Relations.LOCATION.getRelation()));
    }
}