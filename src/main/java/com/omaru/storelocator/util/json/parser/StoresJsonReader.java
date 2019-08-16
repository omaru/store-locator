package com.omaru.storelocator.util.json.parser;

import com.omaru.storelocator.domain.model.Store;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.Collection;

public interface StoresJsonReader {
    Collection<Store> from(Resource resource);
    Collection<Store> from(InputStream stream);
}
