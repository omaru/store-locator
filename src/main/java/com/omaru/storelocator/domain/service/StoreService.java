package com.omaru.storelocator.domain.service;

import com.omaru.storelocator.domain.model.Store;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoPage;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;

import java.util.Collection;
import java.util.Optional;

public interface StoreService {
    /***
     * Retrieves a store by id
     * @param id
     */
    Optional<Store> get(String id);
    /***
     * Retrieves a store by uuid
     * @param uuid
     */
    Optional<Store> getByUuid(String uuid);
    /***
     * Retrieves all Stores available
     */
    Collection<Store> get();
    /***
     * Saves a store
     * @param store to save
     * @return  saved store with assigned generated id
     */
    Store save(Store store);
    /***
     * Retrieves the N Stores near a given reference location
     * @param referenceLocation -  location from where to start
     * @param pageRequest -  how many results do we need by page.
     */
    GeoPage<Store> getStoresByAddressLocationNear(Point referenceLocation, PageRequest pageRequest);
    /***
     * Retrieves the N Stores near a given distance starting from a  reference location
     * @param referenceLocation -  location from where to start
     * @param distance  how far should we search
     */
    GeoResults<Store> getStoresByAddressLocationNear(Point referenceLocation, Distance distance);

    void deleteAll();
}