package com.omaru.storelocator.util.json.parser;

import com.omaru.storelocator.domain.model.Address;
import com.omaru.storelocator.domain.model.Location;
import com.omaru.storelocator.domain.model.Store;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.LocalTime;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultStoresJsonReaderShould {
    public static final String FILE_JSON_TEST_SAMPLE_NAME = "store-test.json";
    private StoresJsonReader storesJsonReader;

    @BeforeEach
    void setUp() {
        storesJsonReader = new DefaultStoresJsonReader();
    }

    @Test
    void beAbleToParseFromResource() throws IOException, ParseException {
        ClassPathResource resource = new ClassPathResource(FILE_JSON_TEST_SAMPLE_NAME);
        Collection<Store> stores = storesJsonReader.from(resource);
        assertThat(stores).hasSize(2);
        Store store = stores.iterator().next();
        assertThat(store.getUuid()).isEqualTo("EOgKYx4XFiQAAAFJa_YYZ4At");
        assertThat(store.getSapStoreID()).isEqualTo(3605);
        assertThat(store.getTodayOpen()).isEqualTo(LocalTime.of(8, 00));
        assertThat(store.getTodayClose()).isEqualTo(LocalTime.of(20, 00));
        Location location = store.getLocation();
        assertThat(location.getCollectionPoint()).isTrue();
        assertThat(location.getComplexNumber()).isEqualTo(33249);
        assertThat(location.getWarningMessage()).isTrue();
        assertThat(location.getLocationType()).isEqualTo("SupermarktPuP");
        assertThat(location.getLocation().getX()).isEqualTo(51.778461);
        assertThat(location.getLocation().getY()).isEqualTo(4.615551);
        Address address = location.getAddress();
        assertThat(address.getCity()).isEqualTo("'s Gravendeel");
        assertThat(address.getPostalCode()).isEqualTo("3295 BD");
        assertThat(address.getStreet()).isEqualTo("Kerkstraat");
        assertThat(address.getStreet2()).isEqualTo("37");
        assertThat(address.getStreet3()).isNullOrEmpty();
        assertThat(address.getAddressName()).isEqualTo("Jumbo 's Gravendeel Gravendeel Centrum");
    }

    @Test
    void throwJsonReaderExceptionWhenLatitudeIsNullOrEmpty() throws IOException, ParseException {
        String jsonInput = "{\n" +
                "  \"stores\": [\n" +
                "    {\n" +
                "      \"city\": \"'s Gravendeel\",\n" +
                "      \"postalCode\": \"3295 BD\",\n" +
                "      \"street\": \"Kerkstraat\",\n" +
                "      \"street2\": \"37\",\n" +
                "      \"street3\": \"\",\n" +
                "      \"addressName\": \"Jumbo 's Gravendeel Gravendeel Centrum\",\n" +
                "      \"uuid\": \"EOgKYx4XFiQAAAFJa_YYZ4At\",\n" +
                "      \"longitude\": \"4.615551\",\n" +
                "      \"latitude\": null,\n" +
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
        Assertions.assertThrows(StoreJsonReaderException.class, () -> storesJsonReader.from(inputStream));
        jsonInput = "{\n" +
                "  \"stores\": [\n" +
                "    {\n" +
                "      \"city\": \"'s Gravendeel\",\n" +
                "      \"postalCode\": \"3295 BD\",\n" +
                "      \"street\": \"Kerkstraat\",\n" +
                "      \"street2\": \"37\",\n" +
                "      \"street3\": \"\",\n" +
                "      \"addressName\": \"Jumbo 's Gravendeel Gravendeel Centrum\",\n" +
                "      \"uuid\": \"EOgKYx4XFiQAAAFJa_YYZ4At\",\n" +
                "      \"longitude\": \"4.615551\",\n" +
                "      \"latitude\": \"\",\n" +
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
        final InputStream stream = new ByteArrayInputStream(jsonInput.getBytes(Charset.forName("UTF-8")));
        Assertions.assertThrows(StoreJsonReaderException.class, () -> storesJsonReader.from(stream));
    }

    @Test
    void throwJsonReaderExceptionWhenLongitudeIsNullOrEmpty() throws IOException, ParseException {
        String jsonInput = "{\n" +
                "  \"stores\": [\n" +
                "    {\n" +
                "      \"city\": \"'s Gravendeel\",\n" +
                "      \"postalCode\": \"3295 BD\",\n" +
                "      \"street\": \"Kerkstraat\",\n" +
                "      \"street2\": \"37\",\n" +
                "      \"street3\": \"\",\n" +
                "      \"addressName\": \"Jumbo 's Gravendeel Gravendeel Centrum\",\n" +
                "      \"uuid\": \"EOgKYx4XFiQAAAFJa_YYZ4At\",\n" +
                "      \"longitude\": \"\",\n" +
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
        Assertions.assertThrows(StoreJsonReaderException.class, () -> storesJsonReader.from(inputStream));
        jsonInput = "{\n" +
                "  \"stores\": [\n" +
                "    {\n" +
                "      \"city\": \"'s Gravendeel\",\n" +
                "      \"postalCode\": \"3295 BD\",\n" +
                "      \"street\": \"Kerkstraat\",\n" +
                "      \"street2\": \"37\",\n" +
                "      \"street3\": \"\",\n" +
                "      \"addressName\": \"Jumbo 's Gravendeel Gravendeel Centrum\",\n" +
                "      \"uuid\": \"EOgKYx4XFiQAAAFJa_YYZ4At\",\n" +
                "      \"longitude\": null,\n" +
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
        final InputStream stream = new ByteArrayInputStream(jsonInput.getBytes(Charset.forName("UTF-8")));
        Assertions.assertThrows(StoreJsonReaderException.class, () -> storesJsonReader.from(stream));
    }
    @Test
    void whenClosedTimeToNull() throws IOException, ParseException {
        String jsonInput = "{\n" +
                "  \"stores\": [\n" +
                "    {\n" +
                "      \"city\": \"'s Gravendeel\",\n" +
                "      \"postalCode\": \"3295 BD\",\n" +
                "      \"street\": \"Kerkstraat\",\n" +
                "      \"street2\": \"37\",\n" +
                "      \"street3\": \"\",\n" +
                "      \"addressName\": \"Jumbo 's Gravendeel Gravendeel Centrum\",\n" +
                "      \"uuid\": \"EOgKYx4XFiQAAAFJa_YYZ4At\",\n" +
                "      \"longitude\": \"23\",\n" +
                "      \"latitude\": \"4.615551\",\n" +
                "      \"complexNumber\": \"33249\",\n" +
                "      \"showWarningMessage\": true,\n" +
                "      \"todayOpen\": \"Gesloten\",\n" +
                "      \"locationType\": \"SupermarktPuP\",\n" +
                "      \"collectionPoint\": true,\n" +
                "      \"sapStoreID\": \"3605\",\n" +
                "      \"todayClose\": \"Gesloten\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        final InputStream inputStream = new ByteArrayInputStream(jsonInput.getBytes(Charset.forName("UTF-8")));
        Collection<Store> stores = storesJsonReader.from(inputStream);
        assertThat(stores).hasSize(1);
        assertThat(stores.iterator().next().getTodayOpen()).isNull();
        assertThat(stores.iterator().next().getTodayClose()).isNull();

    }
}
