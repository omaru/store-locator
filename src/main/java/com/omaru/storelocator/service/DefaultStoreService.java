package com.omaru.storelocator.service;

import com.omaru.storelocator.model.Store;
import com.omaru.storelocator.repository.StoreRepository;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DefaultStoreService implements StoreService {
    private StoreRepository storeRepository;
    @Inject
    public DefaultStoreService(StoreRepository storeRepository){
        this.storeRepository=storeRepository;
    }

    @Override
    public Optional<Store> get(String uuid) {
        return storeRepository.findByUuid(uuid);
    }

    @Override
    public Collection<Store> get() {
        Iterable<Store> stores = storeRepository.findAll();
        return StreamSupport.stream(stores.spliterator(),false).collect(Collectors.toSet());
    }

    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }
}
