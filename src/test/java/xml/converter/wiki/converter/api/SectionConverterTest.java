package xml.converter.wiki.converter.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import xml.converter.wiki.converter.manager.ConverterManager;
import xml.converter.wiki.converter.spi.ElementConverter;
import xml.converter.wiki.model.Section;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static xml.converter.wiki.test.utils.TestUtil.createSectionWithDepth;
import static xml.converter.wiki.test.utils.TestUtil.createSectionWithDepthAndOtherElements;

class SectionConverterTest {

    public static final ConverterManager CONVERTER_MANAGER = ConverterManager.getInstance();
    private static ElementConverter<Section> sectionConverter;

    @BeforeAll
    public static void setup() {
        sectionConverter = CONVERTER_MANAGER.get(Section.class);
    }

    @Test
    public void getType() {
        Class<Section> clazz = sectionConverter.getType();
        assertTrue(clazz.equals(Section.class));
    }

    @Test
    public void convertSectionNull() {
        String expected = "";
        String result = sectionConverter.convert(null, CONVERTER_MANAGER);
        assertEquals(expected, result);
    }

    @Test
    public void convert() {
        Section section = createSectionWithDepth(1);
        String expected = "\n=header1=\n";
        String result = sectionConverter.convert(section, CONVERTER_MANAGER);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth2() {
        Section section = createSectionWithDepth(2);
        String expected = "\n" +
                "=header1=\n" +
                "\n" +
                "==header2==\n";
        String result = sectionConverter.convert(section, CONVERTER_MANAGER);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth3() {
        Section section = createSectionWithDepth(3);
        String expected = "\n" +
                "=header1=\n" +
                "\n" +
                "==header2==\n" +
                "\n" +
                "===header3===\n";
        String result = sectionConverter.convert(section, CONVERTER_MANAGER);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth4() {
        Section section = createSectionWithDepth(4);
        String expected = "\n" +
                "=header1=\n" +
                "\n" +
                "==header2==\n" +
                "\n" +
                "===header3===\n" +
                "\n" +
                "====header4====\n";
        String result = sectionConverter.convert(section, CONVERTER_MANAGER);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth5() {
        Section section = createSectionWithDepth(5);
        String expected = "\n" +
                "=header1=\n" +
                "\n" +
                "==header2==\n" +
                "\n" +
                "===header3===\n" +
                "\n" +
                "====header4====\n" +
                "\n" +
                "=====header5=====\n";
        String result = sectionConverter.convert(section, CONVERTER_MANAGER);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth6() {
        Section section = createSectionWithDepth(6);
        String expected = "\n" +
                "=header1=\n" +
                "\n" +
                "==header2==\n" +
                "\n" +
                "===header3===\n" +
                "\n" +
                "====header4====\n" +
                "\n" +
                "=====header5=====\n" +
                "\n" +
                "======header6======\n";
        String result = sectionConverter.convert(section, CONVERTER_MANAGER);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth7() {
        Section section = createSectionWithDepth(7);
        String expected = "\n" +
                "=header1=\n" +
                "\n" +
                "==header2==\n" +
                "\n" +
                "===header3===\n" +
                "\n" +
                "====header4====\n" +
                "\n" +
                "=====header5=====\n" +
                "\n" +
                "======header6======\n" +
                "\n" +
                "======header6======\n";
        String result = sectionConverter.convert(section, CONVERTER_MANAGER);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth8() {
        Section section = createSectionWithDepth(8);
        String expected = "\n" +
                "=header1=\n" +
                "\n" +
                "==header2==\n" +
                "\n" +
                "===header3===\n" +
                "\n" +
                "====header4====\n" +
                "\n" +
                "=====header5=====\n" +
                "\n" +
                "======header6======\n" +
                "\n" +
                "======header6======\n" +
                "\n" +
                "======header6======\n";
        String result = sectionConverter.convert(section, CONVERTER_MANAGER);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth8Complex() {
        Section section = createSectionWithDepthAndOtherElements(8);
        String expected = "\n" +
                "=header1=\n" +
                "'''bold1''italic1'''''\n" +
                "==header2==\n" +
                "'''bold2''italic2'''''\n" +
                "===header3===\n" +
                "'''bold3''italic3'''''\n" +
                "====header4====\n" +
                "'''bold4''italic4'''''\n" +
                "=====header5=====\n" +
                "'''bold5''italic5'''''\n" +
                "======header6======\n" +
                "'''bold6''italic6'''''\n" +
                "======header6======\n" +
                "'''bold7''italic7'''''\n" +
                "======header6======\n" +
                "'''bold8''italic8'''''";
        String result = sectionConverter.convert(section, CONVERTER_MANAGER);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth6Complex() {
        Section section = createSectionWithDepthAndOtherElements(6);
        String expected = "\n" +
                "=header1=\n" +
                "'''bold1''italic1'''''\n" +
                "==header2==\n" +
                "'''bold2''italic2'''''\n" +
                "===header3===\n" +
                "'''bold3''italic3'''''\n" +
                "====header4====\n" +
                "'''bold4''italic4'''''\n" +
                "=====header5=====\n" +
                "'''bold5''italic5'''''\n" +
                "======header6======\n" +
                "'''bold6''italic6'''''";
        String result = sectionConverter.convert(section, CONVERTER_MANAGER);
        assertEquals(expected, result);
    }

    @Test
    public void convertComplex() {
        Section section = createSectionWithDepthAndOtherElements(1);
        String expected = "\n" +
                "=header1=\n" +
                "'''bold1''italic1'''''";
        String result = sectionConverter.convert(section, CONVERTER_MANAGER);
        assertEquals(expected, result);
    }



}