package com.omaru.storelocator.repository;

import com.omaru.storelocator.model.Store;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface StoreRepository  extends PagingAndSortingRepository<Store, Long> {
    Optional<Store> findByUuid(String uuid);
}
