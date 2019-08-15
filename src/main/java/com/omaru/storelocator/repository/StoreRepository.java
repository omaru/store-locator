package com.omaru.storelocator.repository;

import com.omaru.storelocator.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface StoreRepository  extends PagingAndSortingRepository<Store, Long> {
    Optional<Store> findByUuid(String uuid);
    Page<Store> findByLocationLocationNear(Point point, Pageable pageable);
}
