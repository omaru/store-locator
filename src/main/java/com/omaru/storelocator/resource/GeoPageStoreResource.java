package com.omaru.storelocator.resource;

import com.omaru.storelocator.domain.model.Store;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoPage;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class GeoPageStoreResource extends ResourceSupport {
    private  Distance averageDistance;
    private Long totalElements;
    private Integer size;
    private Integer totalPages;
    private List<StoreResource> storeResources;
    public GeoPageStoreResource(GeoPage<Store> geoPage){
        setAverageDistance(geoPage.getAverageDistance());
        setTotalElements(geoPage.getTotalElements());
        setSize(geoPage.getSize());
        setTotalPages(geoPage.getTotalPages());
    }
}