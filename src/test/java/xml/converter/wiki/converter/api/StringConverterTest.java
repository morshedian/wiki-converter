package xml.converter.wiki.converter.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import xml.converter.wiki.converter.manager.ConverterManager;
import xml.converter.wiki.converter.spi.ElementConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringConverterTest {

    public static final ConverterManager CONVERTER_MANAGER = ConverterManager.getInstance();
    private static ElementConverter<String> stringConverter;

    @BeforeAll
    public static void setup() {
        stringConverter = CONVERTER_MANAGER.get(String.class);
    }

    @Test
    public void getType() {
        Class<String> clazz = stringConverter.getType();
        assertTrue(clazz.equals(String.class));
    }

    @Test
    public void convert() {
        String value = "    this is a test  ";
        String result = stringConverter.convert(value, CONVERTER_MANAGER);
        assertEquals(value, result);
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void convert(String value) {
        String result = stringConverter.convert(value, CONVERTER_MANAGER);
        assertEquals(value, result);
    }

}