package xml.converter.wiki.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import xml.converter.wiki.service.XmlValidatorService;

class XmlValidatorServiceImplTest {

    private static XmlValidatorService xmlValidatorService;

    @BeforeAll
    public static void setup(){
        xmlValidatorService = new XmlValidatorServiceImpl();
    }

    @Test
    public void load1(){
        boolean validate = xmlValidatorService.validate("src/test/resources/example1.xml");
        Assertions.assertTrue(validate);
    }

    @Test
    public void load2(){
        boolean validate = xmlValidatorService.validate("src/test/resources/example2.xml");
        Assertions.assertTrue(validate);
    }

    @Test
    public void loadInvalidXml(){
        boolean validate = xmlValidatorService.validate("src/test/resources/invalid.xml");
        Assertions.assertFalse(validate);
    }

    @Test
    public void loadWrongSchema(){
        boolean validate = xmlValidatorService.validate("src/test/resources/wrongSchema.xml");
        Assertions.assertFalse(validate);
    }

}