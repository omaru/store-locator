package com.omaru.storelocator.util;

import com.omaru.storelocator.model.Store;

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
        return Stream.of(getStore("EOgKYx4XFiQAAAFJa_YYZ4At")).collect(Collectors.toSet());
    }
    public static Store getStore(String uuid){
        Store store = new Store(uuid);
        store.setSapStoreID(1234L);
        store.setTodayOpen(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        store.setTodayClose(new Timestamp(Calendar.getInstance().getTimeInMillis()));

        return store;
    }
}
