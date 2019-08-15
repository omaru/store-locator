package com.omaru.storelocator.util;

import com.omaru.storelocator.model.Address;
import com.omaru.storelocator.model.Location;
import com.omaru.storelocator.model.Store;
import org.springframework.data.geo.Point;

import java.time.LocalTime;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockUtil {
    private MockUtil() throws IllegalAccessException{
        throw new IllegalAccessException("utility class");
    }
    public static Collection<Store> getStores(){
        return Stream.of(
                getNewStoreWithCoordinates("1", new Point(51.778461,4.615551)),
                getNewStoreWithCoordinates("2", new Point(51.778461,6.245829)),
                getNewStoreWithCoordinates("3", new Point(52.264417,4.762433)),
                getNewStoreWithCoordinates("4", new Point(51.399843,5.469597)),
                getNewStoreWithCoordinates("5", new Point(51.923993,6.576066)),
                getNewStoreWithCoordinates("6", new Point(51.275006,3.444601)),
                getNewStoreWithCoordinates("7", new Point(52.645601,4.749492)),
                getNewStoreWithCoordinates("8", new Point(52.665822,4.766146)),
                getNewStoreWithCoordinates("9", new Point(51.778461,4.615551)),
                getNewStoreWithCoordinates("10", new Point(52.633740,4.745031))
        ).collect(Collectors.toSet());
    }
    public static Store getNewStore(String uuid){
        Store store = new Store(uuid);
        store.setSapStoreID(3605L);
        store.setTodayOpen(LocalTime.of(8,00,00));
        store.setTodayClose(LocalTime.of(20,00,00));
        return store;
    }
    public static Store getNewStoreWithCoordinates(String storeUUID , Point coordinates){
        Store store = getNewStore(storeUUID);
        Location location = new Location(coordinates);
        location.setComplexNumber(33249);
        location.setAddress(createAddress());
        store.setLocation(location);
        return store;
    }

    private static Address createAddress() {
        Address address = new Address();
        address.setAddressName("Jumbo 's Gravendeel Gravendeel Centrum");
        address.setCity("'s Gravendeel");
        address.setPostalCode("3295 BD");
        address.setStreet("Kerkstraat");
        address.setStreet2("37");
        return address;
    }
}
