package com.omaru.storelocator.util.json.parser;

public class StringUtil {
    private StringUtil() throws IllegalAccessException {
        throw new IllegalAccessException("utility class");
    }
    public static boolean isNullOrBlank(Object value){
        return value == null || "".equals(value.toString().trim());
    }
    public static String toFalseBooleanStringIfNull(Object value){
        return isNullOrBlank(value) ? "false" : value.toString().trim();
    }
    public static String toStringIfNotNull(Object value){
        return value == null ? null : value.toString().trim();
    }
}