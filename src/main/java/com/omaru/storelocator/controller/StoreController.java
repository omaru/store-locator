package com.omaru.storelocator.controller;

import com.omaru.storelocator.controller.exception.NotFoundException;
import com.omaru.storelocator.domain.model.Store;
import com.omaru.storelocator.domain.service.StoreService;
import com.omaru.storelocator.resource.GeoPageStoreResource;
import com.omaru.storelocator.resource.GeoPageStoreResourceAssembler;
import com.omaru.storelocator.resource.StoreResource;
import com.omaru.storelocator.resource.StoreResourceAssembler;
import org.springframework.beans.factory.annotation.Value;
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
    private final GeoPageStoreResourceAssembler geoPageStoreResourceAssembler;
    private  final PageRequest defaultNumberOfResultsPagination;
    @Inject
    public StoreController(StoreService storeService, StoreResourceAssembler storeResourceAssembler,
                           GeoPageStoreResourceAssembler geoPageStoreResourceAssembler,
                           @Value("${default.page.request.size}")Integer pageRequestSize){
        this.storeService = storeService;
        this.storeResourceAssembler = storeResourceAssembler;
        this.geoPageStoreResourceAssembler=geoPageStoreResourceAssembler;
        this.defaultNumberOfResultsPagination = PageRequest.of(0,pageRequestSize);
    }
    @RequestMapping(value={"/"},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<StoreResource>> getStores(){
        Collection<Store> stores =storeService.get();
        return new ResponseEntity<>(storeResourceAssembler.toResources(stores), HttpStatus.OK);
    }
    @RequestMapping(value={"","/"},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE,params={"latitude","longitude"})
    public ResponseEntity<GeoPageStoreResource> getStores(@RequestParam Double  latitude, @RequestParam Double longitude){
        GeoPage<Store> stores = storeService.getStoresByAddressLocationNear(new Point(latitude,longitude), defaultNumberOfResultsPagination);
        return new ResponseEntity<>(geoPageStoreResourceAssembler.toResource(stores), HttpStatus.OK);
    }
    @RequestMapping(value={"{id}"},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoreResource>getStore(@PathVariable  String id) {
        Store store = storeService.get(id).orElseThrow(()->new NotFoundException("Store not found"));
        return new ResponseEntity<>(storeResourceAssembler.toResource(store), HttpStatus.OK);
    }
}