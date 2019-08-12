package com.omaru.storelocator.service;

import com.omaru.storelocator.model.Store;

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
     * @param uuid
     */
    Collection<Store> get();

}
