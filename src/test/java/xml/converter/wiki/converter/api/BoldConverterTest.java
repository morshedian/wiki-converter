package xml.converter.wiki.converter.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import xml.converter.wiki.converter.manager.ConverterManager;
import xml.converter.wiki.converter.spi.ElementConverter;
import xml.converter.wiki.model.Bold;
import xml.converter.wiki.model.Italic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoldConverterTest {
    public static final ConverterManager CONVERTER_MANAGER = ConverterManager.getInstance();
    private static ElementConverter<Bold> boldConverter;

    @BeforeAll
    public static void setup() {
        boldConverter = CONVERTER_MANAGER.get(Bold.class);
    }

    @Test
    public void getType() {
        Class<Bold> clazz = boldConverter.getType();
        assertTrue(clazz.equals(Bold.class));
    }

    @Test
    public void convert() {
        Bold bold = new Bold();
        bold.getContent().add("this is a test");

        String result = boldConverter.convert(bold, CONVERTER_MANAGER);
        String expected = "'''this is a test'''";
        assertEquals(expected, result);
    }

    @Test
    public void convertNullValue() {
        String result = boldConverter.convert(null, CONVERTER_MANAGER);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void convertNoChild() {
        Bold bold = new Bold();
        String result = boldConverter.convert(bold, CONVERTER_MANAGER);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void convertWithComplexChildren() {
        Bold bold = new Bold();
        bold.getContent().add("this is ");

        Italic italic = new Italic();
        italic.getContent().add("a test");
        bold.getContent().add(italic);

        String result = boldConverter.convert(bold, CONVERTER_MANAGER);
        String expected = "'''this is ''a test'''''";
        assertEquals(expected, result);
    }

}