package com.omaru.storelocator.domain.service;

import com.omaru.storelocator.domain.model.Store;
import com.omaru.storelocator.domain.repository.StoreRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoPage;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Component
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

    @Override
    public GeoPage<Store> getStoresByAddressLocationNear(Point referenceLocation, PageRequest pageRequest) {
        return storeRepository.findByLocationLocationNear(referenceLocation,pageRequest);
    }

    @Override
    public GeoResults<Store> getStoresByAddressLocationNear(Point referenceLocation, Distance distance) {
        return storeRepository.findByLocationLocationNear(referenceLocation,distance);
    }
    @Override
    public void deleteAll() {
        storeRepository.deleteAll();
    }
}
