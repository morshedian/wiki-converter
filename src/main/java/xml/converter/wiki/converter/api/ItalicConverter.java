package xml.converter.wiki.converter.api;

import xml.converter.wiki.converter.spi.ConverterRegistry;
import xml.converter.wiki.model.Italic;

public class ItalicConverter extends AbstractCompositeElementConverter<Italic> {
    @Override
    public String convert(Italic italic, ConverterRegistry converterRegistry) {
        if(italic == null){
            return "";
        }
        String content = convertChildren(italic, converterRegistry);
        if(content.isEmpty()){
            return "";
        }
        return String.format("''%s''", content);
    }

    @Override
    public Class<Italic> getType() {
        return Italic.class;
    }
}
