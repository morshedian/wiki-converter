package xml.converter.wiki.converter.api;

import xml.converter.wiki.converter.spi.ConverterRegistry;
import xml.converter.wiki.converter.spi.ElementConverter;

public class StringConverter implements ElementConverter<String> {
    @Override
    public String convert(String s, ConverterRegistry converterRegistry) {
        return s;
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
