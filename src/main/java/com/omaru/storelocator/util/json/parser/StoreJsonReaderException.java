package com.omaru.storelocator.util.json.parser;

class StoreJsonReaderException extends RuntimeException {
    public StoreJsonReaderException(String message, Exception e) {
        super(message,e);
    }
}