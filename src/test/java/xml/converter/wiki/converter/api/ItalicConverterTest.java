package xml.converter.wiki.converter.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import xml.converter.wiki.converter.manager.ConverterManager;
import xml.converter.wiki.converter.spi.ElementConverter;
import xml.converter.wiki.model.Bold;
import xml.converter.wiki.model.Italic;

import static org.junit.jupiter.api.Assertions.*;

class ItalicConverterTest {
    public static final ConverterManager CONVERTER_MANAGER = ConverterManager.getInstance();
    private static ElementConverter<Italic> italicConverter;

    @BeforeAll
    public static void setup() {
        italicConverter = CONVERTER_MANAGER.get(Italic.class);
    }

    @Test
    public void getType() {
        Class<Italic> clazz = italicConverter.getType();
        assertTrue(clazz.equals(Italic.class));
    }

    @Test
    public void convert() {
        Italic italic = new Italic();
        italic.getContent().add("this is a test");

        String result = italicConverter.convert(italic, CONVERTER_MANAGER);
        String expected = "''this is a test''";
        assertEquals(expected, result);
    }

    @Test
    public void convertNullValue() {
        String result = italicConverter.convert(null, CONVERTER_MANAGER);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void convertNoChild() {
        Italic italic = new Italic();
        String result = italicConverter.convert(italic, CONVERTER_MANAGER);
        String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void convertWithComplexChildren() {
        Italic italic = new Italic();
        italic.getContent().add("this is ");

        Bold bold = new Bold();
        bold.getContent().add("a test");
        italic.getContent().add(bold);

        String result = italicConverter.convert(italic, CONVERTER_MANAGER);
        String expected = "''this is '''a test'''''";
        assertEquals(expected, result);
    }

}