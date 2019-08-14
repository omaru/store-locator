package com.omaru.storelocator.util;

import com.omaru.storelocator.model.Address;
import com.omaru.storelocator.model.Location;
import com.omaru.storelocator.model.Store;
import org.springframework.data.geo.Point;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockUtil {
    private MockUtil() throws IllegalAccessException{
        throw new IllegalAccessException("utility class");
    }
    public static Collection<Store> getStores(){
        return Stream.of(createStore("EOgKYx4XFiQAAAFJa_YYZ4At")).collect(Collectors.toSet());
    }
    public static Store createStore(String uuid){
        Store store = new Store(uuid);
        store.setSapStoreID(1234L);
        store.setTodayOpen(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        store.setTodayClose(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        return store;
    }
    public static Store createStoreWithCoordinates(String storeUUID , Point coordinates){
        Store store = createStore(storeUUID);
        Location location = new Location(coordinates);
        location.setComplexNumber(33249);
        location.setAddress(createAddress());
    }

    private static Address createAddress() {
        Address address = new Address();
        address.setAddressName("Jumbo 's Gravendeel Gravendeel Centrum");
        address.setCity("'s Gravendeel");
        address.setPostalCode("3295 BD");
        address.setStreet("Kerkstraat");
        address.setStreet2("37");
    }
}
