package com.omaru.storelocator.service;

import com.omaru.storelocator.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;

import java.util.Collection;
import java.util.Optional;

public interface StoreService {
    /***
     * Retrieves a store by uuid
     * @param uuid
     */
    Optional<Store> get(String uuid);
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

    Page<Store> getStoresByAddressLocationNear(Point referenceLocation, Pageable pageable);
}
