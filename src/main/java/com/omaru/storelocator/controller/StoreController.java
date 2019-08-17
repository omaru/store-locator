package com.omaru.storelocator.controller;

import com.omaru.storelocator.controller.exception.NotFoundException;
import com.omaru.storelocator.domain.model.Store;
import com.omaru.storelocator.domain.service.StoreService;
import com.omaru.storelocator.resource.StoreResource;
import com.omaru.storelocator.resource.StoreResourceAssembler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.GeoPage;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@RestController
@RequestMapping(value="/store")
public class StoreController {
    private final StoreService storeService;
    private final StoreResourceAssembler storeResourceAssembler;
    private static final PageRequest DEFAULT_TEN_RESULTS_PAGINATION = PageRequest.of(0,10);
    @Inject
    public StoreController(StoreService storeService, StoreResourceAssembler storeResourceAssembler){
        this.storeService = storeService;
        this.storeResourceAssembler = storeResourceAssembler;
    }
    @RequestMapping(value={"/"},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<StoreResource>> getStores(){
        Collection<Store> stores =storeService.get();
        return new ResponseEntity<>(storeResourceAssembler.toResources(stores), HttpStatus.OK);
    }
    @RequestMapping(value={""},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE,params={"latitude","longitude"})
    public ResponseEntity<Collection<StoreResource>> getStores(@RequestParam Double  latitude,@RequestParam Double longitude){
        GeoPage<Store> stores = storeService.getStoresByAddressLocationNear(new Point(latitude,longitude), DEFAULT_TEN_RESULTS_PAGINATION);
        return new ResponseEntity<>(storeResourceAssembler.toResources(stores), HttpStatus.OK);
    }
    @RequestMapping(value={"{id}"},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreResource>getStore(@PathVariable  String id) {
        Store store = storeService.get(id).orElseThrow(()->new NotFoundException("Store not found"));
        return new ResponseEntity<>(storeResourceAssembler.toResource(store), HttpStatus.OK);
    }
}