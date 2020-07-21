package xml.converter.wiki.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xml.converter.wiki.model.Report;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;

public final class XmlParserService {
    private static final XmlParserService PARSER = new XmlParserService();
    private final static Logger logger = LoggerFactory.getLogger(XmlParserService.class);

    private XmlParserService() {
    }

    public static XmlParserService getInstance() {
        return PARSER;
    }

    public Optional<Report> unmarshall(String filePath) {
        try {
            logger.debug("trying to parse xml file: {}", filePath);
            JAXBContext context = JAXBContext.newInstance(Report.class);
            Report report = (Report) context.createUnmarshaller()
                    .unmarshal(new FileReader(filePath));
            logger.debug("successfully parsed xml file: {}", filePath);
            return Optional.of(report);
        } catch (FileNotFoundException | JAXBException e) {
            logger.error(e.getMessage());
        }
        return Optional.empty();
    }
}
