package xml.converter.wiki.converter.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import xml.converter.wiki.converter.spi.ElementConverter;
import xml.converter.wiki.model.Bold;
import xml.converter.wiki.model.Italic;
import xml.converter.wiki.model.Report;
import xml.converter.wiki.model.Section;

import static org.junit.jupiter.api.Assertions.*;

class ConverterManagerTest {

    private static ConverterManager converterManager;

    @BeforeAll
    public static void setup(){
        converterManager = ConverterManager.getInstance();
    }

    @Test
    void getStringConverterInstance() {
        ElementConverter<String> elementConverter = converterManager.get(String.class);
        assertNotNull(elementConverter);
    }

    @Test
    void getReportConverterInstance() {
        ElementConverter<Report> elementConverter = converterManager.get(Report.class);
        assertNotNull(elementConverter);
    }

    @Test
    void getSectionConverterInstance() {
        ElementConverter<Section> elementConverter = converterManager.get(Section.class);
        assertNotNull(elementConverter);
    }

    @Test
    void getItalicConverterInstance() {
        ElementConverter<Italic> elementConverter = converterManager.get(Italic.class);
        assertNotNull(elementConverter);
    }

    @Test
    void getBoldConverterInstance() {
        ElementConverter<Bold> elementConverter = converterManager.get(Bold.class);
        assertNotNull(elementConverter);
    }
}