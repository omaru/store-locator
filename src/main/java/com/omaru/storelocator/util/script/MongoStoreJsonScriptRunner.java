package com.omaru.storelocator.util.script;

import com.omaru.storelocator.domain.model.Store;
import com.omaru.storelocator.domain.service.StoreService;
import com.omaru.storelocator.util.json.parser.StoresJsonReader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.InputStream;
import java.util.Collection;
import java.util.function.Supplier;

@Component
public class MongoStoreJsonScriptRunner implements ScriptRunner {
    private StoreService storeService;
    private StoresJsonReader storesjsonReader;
    @Inject
    public MongoStoreJsonScriptRunner(StoreService storeService, StoresJsonReader storesjsonReader){
        this.storeService = storeService;
        this.storesjsonReader = storesjsonReader;
    }

    @Override
    public void run(Resource resource) {
        run(()-> storesjsonReader.from(resource));
    }

    @Override
    public void run(InputStream stream) throws Exception{
        run(()-> storesjsonReader.from(stream));
    }
    private void run(Supplier<Collection<Store>> supplier){
        supplier.get().stream().forEach(storeService::save);
    }
}