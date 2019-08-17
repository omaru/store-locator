package com.omaru.storelocator.util.cmd.exception;

public class CommandLineDataIngesterException extends RuntimeException {
    public CommandLineDataIngesterException(String message,Exception e) {
        super(message,e);
    }
}
