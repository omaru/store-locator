package com.omaru.storelocator.domain.repository;

import com.omaru.storelocator.domain.model.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoPage;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface StoreRepository  extends PagingAndSortingRepository<Store, String> {
    Optional<Store> findByUuid(String uuid);
    GeoPage<Store> findByLocationLocationNear(Point point, Pageable pageRequest);
    GeoResults<Store> findByLocationLocationNear(Point point, Distance distance);
}
