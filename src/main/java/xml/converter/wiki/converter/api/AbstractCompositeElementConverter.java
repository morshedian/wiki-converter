package xml.converter.wiki.converter.api;

import xml.converter.wiki.converter.spi.ConverterRegistry;
import xml.converter.wiki.converter.spi.ElementConverter;
import xml.converter.wiki.model.BaseModel;

public abstract class AbstractCompositeElementConverter<T> implements ElementConverter<T> {

    protected String convertChildren(T t, ConverterRegistry converterRegistry) {
        StringBuilder builder = new StringBuilder();
        if (t instanceof BaseModel) {
            BaseModel model = (BaseModel) t;
            normalize(model);
            for (Object child : model.getContent()) {
                ElementConverter converter = converterRegistry.get(child.getClass());
                builder.append(converter.convert(child, converterRegistry));
            }
        }
        return builder.toString();
    }


    private void normalize(BaseModel baseModel) {
        int size = baseModel.getContent().size();
        if (size == 1) {
            Object firstChild = baseModel.getContent().get(0);
            if(firstChild instanceof String){
                baseModel.getContent().set(0, ((String) firstChild).trim());
            }
        } else if (size > 1){
            Object firstChild = baseModel.getContent().get(0);
            if(firstChild instanceof String){
                String trimmed = ((String) firstChild).replaceAll("^\\s+", ""); //trim left
                baseModel.getContent().set(0, trimmed);
            }

            Object lastChild = baseModel.getContent().get(size - 1);
            if(lastChild instanceof String){
                String trimmed = ((String) lastChild).replaceAll("\\s+$", ""); //trim right
                baseModel.getContent().set(size -1, trimmed);
            }
        }
    }

}
