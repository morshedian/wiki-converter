package xml.converter.wiki.converter.manager;

import xml.converter.wiki.converter.spi.ConverterRegistry;
import xml.converter.wiki.converter.spi.ElementConverter;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

public class ConverterManager implements ConverterRegistry {

    private static ConverterManager CONVERTER_MANAGER;
    private static final ConcurrentHashMap<Class, ElementConverter> ELEMENT_CONVERTERS = new ConcurrentHashMap<>();

    public static ConverterManager getInstance() {
        if (CONVERTER_MANAGER == null) {
            CONVERTER_MANAGER = new ConverterManager();
            CONVERTER_MANAGER.load();
        }
        return CONVERTER_MANAGER;
    }

    public <T> ElementConverter<T> get(Class<T> clazz) {
        return ELEMENT_CONVERTERS.get(clazz);
    }

    private void load() {
        ServiceLoader<ElementConverter> loader = ServiceLoader.load(ElementConverter.class);
        Iterator<ElementConverter> iterator = loader.iterator();
        while (iterator.hasNext()) {
            ElementConverter elementConverter = iterator.next();
            ELEMENT_CONVERTERS.put(elementConverter.getType(), elementConverter);
        }
    }

}
