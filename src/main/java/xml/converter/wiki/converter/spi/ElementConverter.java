package xml.converter.wiki.converter.spi;

public interface ElementConverter<T> {

    String convert(T t, ConverterRegistry converterRegistry);

    Class<T> getType();
}
