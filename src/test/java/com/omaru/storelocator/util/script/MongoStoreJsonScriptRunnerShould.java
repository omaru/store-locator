package com.omaru.storelocator.util.script;

import com.omaru.storelocator.domain.service.StoreService;
import com.omaru.storelocator.util.json.parser.StoresJsonReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@ExtendWith(MockitoExtension.class)
class MongoStoreJsonScriptRunnerShould {
    private ScriptRunner runner;
    @Mock
    private StoreService storeService;
    @Mock
    private StoresJsonReader storesJsonReader;
    @BeforeEach
    void setUp(){
        runner = new MongoStoreJsonScriptRunner(storeService,storesJsonReader);
    }
    @Test
    void beAbleToRunFromInputStream() throws Exception {
        String input ="";
        final InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        runner.run(inputStream);
    }
    @Test
    void beAbleToCallRunFromResource() throws Exception {
        Resource resource = new ClassPathResource("notfound");
        runner.run(resource);
    }
}