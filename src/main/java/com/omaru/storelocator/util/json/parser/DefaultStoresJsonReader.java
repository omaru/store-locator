package com.omaru.storelocator.util.json.parser;

import com.omaru.storelocator.domain.model.Address;
import com.omaru.storelocator.domain.model.Location;
import com.omaru.storelocator.domain.model.Store;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.Resource;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.Collection;
import java.util.stream.Collectors;

import static com.omaru.storelocator.util.json.parser.StringUtil.*;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.util.Objects.isNull;
import static org.json.simple.parser.ParseException.ERROR_UNEXPECTED_EXCEPTION;
@Component
public class DefaultStoresJsonReader implements StoresJsonReader {
    private static final Integer MINUTE_INDEX = 1;
    private static final Integer HOUR_INDEX = 0;

    @Override
    public  Collection<Store> from(Resource resource){
        try {
            return from(resource.getInputStream());
        } catch (IOException e) {
            throw new StoreJsonReaderException("unable to parse input", e);
        }
    }
    @Override
    public  Collection<Store> from(InputStream stream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            JSONParser parser = new JSONParser();
            JSONObject jsonStores = (JSONObject) parser.parse(reader);
            JSONArray jsonStoresList = (JSONArray) jsonStores.get("stores");
            return (Collection<Store>) jsonStoresList.stream().map(DefaultStoresJsonReader::toStore).collect(Collectors.toSet());
        }catch(IOException | ParseException e){
            throw new StoreJsonReaderException("unable to parse input",e);
        }
    }

    private static Store toStore(Object o){
        JSONObject storeJson = (JSONObject) o;
        Store store = new Store();
        store.setUuid(storeJson.get("uuid").toString());
        store.setSapStoreID(Long.valueOf(storeJson.get("sapStoreID").toString()));
        store.setTodayOpen(todayTime(storeJson.get("todayOpen")));
        store.setTodayClose(todayTime(storeJson.get("todayClose")));
        try {
            store.setLocation(toLocation(storeJson));
        } catch (ParseException e) {
            throw new StoreJsonReaderException("unable to parse location",e);
        }
        return store;
    }

    private static Location toLocation(JSONObject storeJson) throws ParseException {
        if (isNullOrBlank(storeJson.get("latitude"))) {
            throw new ParseException(ERROR_UNEXPECTED_EXCEPTION, "latitude field is null!");
        }
        if (isNullOrBlank(storeJson.get("longitude"))) {
            throw new ParseException(ERROR_UNEXPECTED_EXCEPTION, "longitude field is null!");
        }
        Point pointLocation = new Point(parseDouble(storeJson.get("latitude").toString()),
                parseDouble(storeJson.get("longitude").toString()));
        Location location = new Location(pointLocation);
        location.setComplexNumber(parseInt(storeJson.get("complexNumber").toString()));
        location.setCollectionPoint(parseBoolean(toFalseBooleanStringIfNull(storeJson.get("collectionPoint"))));
        location.setLocationType(toStringIfNotNull(storeJson.get("locationType")));
        location.setWarningMessage(parseBoolean(toFalseBooleanStringIfNull(storeJson.get("showWarningMessage"))));
        location.setAddress(toAddress(storeJson));
        return location;

    }

    private static Address toAddress(JSONObject storeJson) {
        Address address = new Address();
        address.setAddressName(toStringIfNotNull(storeJson.get("addressName")));
        address.setStreet(toStringIfNotNull(storeJson.get("street")));
        address.setStreet2(toStringIfNotNull(storeJson.get("street2")));
        address.setStreet3(toStringIfNotNull(storeJson.get("street3")));
        address.setPostalCode(toStringIfNotNull(storeJson.get("postalCode")));
        address.setCity(toStringIfNotNull(storeJson.get("city")));
        return address;
    }

    private static LocalTime todayTime(Object value) {
        if (isNull(value)) {
            return null;
        }
        String todayClose = value.toString();
        return parseTimeValue(todayClose);
    }

    private static final LocalTime parseTimeValue(String time) {
        String hourMinutes[] = time.split(":");
        return LocalTime.of(parseInt(hourMinutes[HOUR_INDEX]), parseInt(hourMinutes[MINUTE_INDEX]));
    }
}
