package com.omaru.storelocator.util.json.parser;

import org.junit.jupiter.api.Test;

import static com.omaru.storelocator.util.json.parser.StringUtil.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilShould {
    @Test
    void returnTrueIfStringIsNullOrBlank(){
        boolean result = isNullOrBlank("");
        assertTrue(result,"should return true for an empty string");
        result = isNullOrBlank("  ");
        assertTrue(result,"should return true for an empty string");
        result = isNullOrBlank(null);
        assertTrue(result,"should return true for a null string");
    }
    @Test
    void returnFalseBooleanStringIfNull(){
        String result = toFalseBooleanStringIfNull("");
        assertThat(result).isEqualTo("false");
        result = toFalseBooleanStringIfNull(null);
        assertThat(result).isEqualTo("false");
        result = toFalseBooleanStringIfNull(" ");
        assertThat(result).isEqualTo("false");
    }
    @Test
    void returnStringIfNotNull(){
        String result = toStringIfNotNull("fdsafdsa");
        assertThat(result).isEqualTo("fdsafdsa");
        result = toStringIfNotNull(null);
        assertThat(result).isNull();
    }
}
