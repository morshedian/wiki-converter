package xml.converter.wiki.converter.spi;

import xml.converter.wiki.converter.spi.ElementConverter;

public interface ConverterRegistry {

    <T> ElementConverter<T> get(Class<T> clazz);
}
