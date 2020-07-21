package xml.converter.wiki.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import xml.converter.wiki.model.Report;

import java.util.Optional;

class XmlParserServiceTest {

    private static XmlParserService xmlParserService;

    @BeforeAll
    public static void setup(){
        xmlParserService = XmlParserService.getInstance();
    }

    @Test
    public void load1(){
        Optional<Report> report = xmlParserService.unmarshall("src/test/resources/example1.xml");
        Assertions.assertNotNull(report.get());
    }

    @Test
    public void load2(){
        Optional<Report> report = xmlParserService.unmarshall("src/test/resources/example2.xml");
        Assertions.assertNotNull(report.get());
    }

    @Test
    public void loadInvalidXml(){
        Optional<Report> report = xmlParserService.unmarshall("src/test/resources/invalid.xml");
        Assertions.assertFalse(report.isPresent());
    }

    @Test
    public void loadWrongSchema(){
        Optional<Report> report = xmlParserService.unmarshall("src/test/resources/wrongSchema.xml");
        Assertions.assertFalse(report.isPresent());
    }

}