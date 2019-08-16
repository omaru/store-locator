package com.omaru.storelocator.resource;

import com.omaru.storelocator.domain.model.Location;
import com.omaru.storelocator.domain.model.Store;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalTime;

@Data
public class StoreResource extends ResourceSupport {
    private String uuid;
    private Long sapStoreID;
    private LocalTime todayOpen;
    private LocalTime todayClose;
    private Location location;
    public StoreResource(Store store){
        setUuid(store.getUuid());
        setSapStoreID(store.getSapStoreID());
        setTodayOpen(store.getTodayOpen());
        setTodayClose(store.getTodayClose());
        setLocation(store.getLocation());
    }
}