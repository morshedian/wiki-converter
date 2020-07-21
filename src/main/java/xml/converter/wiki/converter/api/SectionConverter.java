package xml.converter.wiki.converter.api;

import xml.converter.wiki.converter.spi.ConverterRegistry;
import xml.converter.wiki.model.BaseModel;
import xml.converter.wiki.model.Section;

public class SectionConverter extends AbstractCompositeElementConverter<Section> {
    private static final String SECTION_CHAR = "=";

    @Override
    public String convert(Section section, ConverterRegistry converterRegistry) {
        if (section == null) {
            return "";
        }

        String literal = createCharBaseOnDepth(section);
        StringBuilder builder = new StringBuilder("\n");
        builder.append(literal).append(section.getHeading()).append(literal).append("\n").append(convertChildren(section, converterRegistry));

        return builder.toString();
    }

    private String createCharBaseOnDepth(Section section) {
        int depth = 1;
        BaseModel temp = section;
        while (temp.getParent() != null && depth < 6) {
            temp = (BaseModel) temp.getParent();
            if (temp instanceof Section) {
                depth++;
            }
        }

        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < depth; i++) {
            builder.append(SECTION_CHAR);
        }
        return builder.toString();
    }

    @Override
    public Class<Section> getType() {
        return Section.class;
    }
}
