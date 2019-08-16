package com.omaru.storelocator.util.json.parser;

import static java.util.Objects.isNull;

public class StringUtil {
    private StringUtil() throws IllegalAccessException {
        throw new IllegalAccessException("utility class");
    }
    public static boolean isNullOrBlank(Object value){
        return value == null || "".equals(value.toString().trim());
    }
    public static String toFalseBooleanStringIfNull(Object value){
        return isNull(value) ? "false" : value.toString().trim();
    }
    public static String toStringIfNotNull(Object value){
        return value == null ? null : value.toString().trim();
    }
}
