package com.omaru.storelocator.resource;

import com.omaru.storelocator.controller.StoreController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class MainNavigationResource extends ResourceSupport {
    public MainNavigationResource(){
        this.add(linkTo(methodOn(StoreController.class).getStores(null,null)).withRel("stores"));
    }

}