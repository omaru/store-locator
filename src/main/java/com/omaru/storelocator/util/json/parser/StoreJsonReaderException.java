package com.omaru.storelocator.util.json.parser;

public class StoreJsonReaderException extends RuntimeException {
    public StoreJsonReaderException(String message, Exception e) {
        super(message,e);
    }
}
