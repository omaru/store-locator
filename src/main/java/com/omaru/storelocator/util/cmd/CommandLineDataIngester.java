package com.omaru.storelocator.util.cmd;

public interface CommandLineDataIngester {
    void accept(String...args) throws Exception;
}
