package com.omaru.storelocator.util.script;

import com.omaru.storelocator.domain.service.StoreService;
import com.omaru.storelocator.util.json.parser.StoresJsonReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

@ExtendWith(MockitoExtension.class)
public class MongoStoreJsonScriptRunnerShould {
    private ScriptRunner runner;
    @Mock
    private StoreService storeService;
    @Mock
    private StoresJsonReader storesJsonReader;
    @Test
    void beAbleToRunFromInputStream() throws Exception {
        runner = new MongoStoreJsonScriptRunner(storeService,storesJsonReader);
        String jsonInput ="{\n" +
                "  \"stores\": [\n" +
                "    {\n" +
                "      \"city\": \"'s Gravendeel\",\n" +
                "      \"postalCode\": \"3295 BD\",\n" +
                "      \"street\": \"Kerkstraat\",\n" +
                "      \"street2\": \"37\",\n" +
                "      \"street3\": \"\",\n" +
                "      \"addressName\": \"Jumbo 's Gravendeel Gravendeel Centrum\",\n" +
                "      \"uuid\": \"EOgKYx4XFiQAAAFJa_YYZ4At\",\n" +
                "      \"longitude\": \"44\",\n" +
                "      \"latitude\": \"4.615551\",\n" +
                "      \"complexNumber\": \"33249\",\n" +
                "      \"showWarningMessage\": true,\n" +
                "      \"todayOpen\": \"08:00\",\n" +
                "      \"locationType\": \"SupermarktPuP\",\n" +
                "      \"collectionPoint\": true,\n" +
                "      \"sapStoreID\": \"3605\",\n" +
                "      \"todayClose\": \"20:00\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        final InputStream inputStream = new ByteArrayInputStream(jsonInput.getBytes(Charset.forName("UTF-8")));
        runner.run(inputStream);
    }
}
