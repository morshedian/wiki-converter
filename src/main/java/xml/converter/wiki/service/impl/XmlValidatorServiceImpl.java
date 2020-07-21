package xml.converter.wiki.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import xml.converter.wiki.service.XmlValidatorService;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidatorServiceImpl implements XmlValidatorService {

    private final static Logger logger = LoggerFactory.getLogger(XmlValidatorService.class);
    public static final String XSD_FILE = "report.xsd";

    @Override
    public boolean validate(String filepath) {
        boolean isValid = false;
        try {
            Source xmlFile = new StreamSource(new File(filepath));

            SchemaFactory schemaFactory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory
                    .newSchema(new Source[]{new StreamSource(getClass().getResourceAsStream(XSD_FILE))});

            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            isValid = true;
            logger.debug("resource is validated against the xsd: {}", filepath);
        } catch (SAXException | IOException e) {
            logger.error(e.getMessage());
        }
        return isValid;
    }
}
