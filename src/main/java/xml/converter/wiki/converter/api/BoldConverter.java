package xml.converter.wiki.converter.api;

import xml.converter.wiki.converter.spi.ConverterRegistry;
import xml.converter.wiki.model.Bold;

public class BoldConverter extends AbstractCompositeElementConverter<Bold> {
    @Override
    public String convert(Bold bold, ConverterRegistry converterRegistry) {
        if (bold == null) {
            return "";
        }

        String children = convertChildren(bold, converterRegistry);
        if (children.isEmpty()) {
            return "";
        }

        return String.format("'''%s'''", children);
    }

    @Override
    public Class<Bold> getType() {
        return Bold.class;
    }
}
