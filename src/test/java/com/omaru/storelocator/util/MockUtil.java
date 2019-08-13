package com.omaru.storelocator.util;

import com.omaru.storelocator.model.Store;

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
        return store;
    }
}
