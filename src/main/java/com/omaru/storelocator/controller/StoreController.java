package com.omaru.storelocator.controller;

import com.omaru.storelocator.domain.model.Store;
import com.omaru.storelocator.domain.service.StoreService;
import com.omaru.storelocator.resource.StoreResource;
import com.omaru.storelocator.resource.StoreResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collection;

@RestController
public class StoreController {
    private final StoreService storeService
    private final StoreResourceAssembler storeResourceAssembler;
    @Inject
    public StoreController(StoreService storeService, StoreResourceAssembler storeResourceAssembler){
        this.storeService = storeService;
        this.storeResourceAssembler = storeResourceAssembler;
    }
    @RequestMapping(value={""},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<StoreResource>> getStores(){
        Collection<Store> stores =storeService.get();
        return new ResponseEntity<>(storeResourceAssembler.toResources(stores), HttpStatus.OK);
    }

}
