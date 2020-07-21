package xml.converter.wiki.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xml.converter.wiki.converter.manager.ConverterManager;
import xml.converter.wiki.converter.spi.ElementConverter;
import xml.converter.wiki.model.Report;
import xml.converter.wiki.service.WikiConverterService;

import java.io.*;

public class WikiConverterServiceImpl implements WikiConverterService {

    private final static Logger logger = LoggerFactory.getLogger(WikiConverterServiceImpl.class);
    public static final String XML_FILE_EXTENSTION = ".xml";
    public static final String WIKI_FILE_EXTENSION = ".wiki";

    private ConverterManager converterManager;

    public WikiConverterServiceImpl(ConverterManager converterManager) {
        this.converterManager = converterManager;
    }

    @Override
    public String convert(Report report) {
        ElementConverter<Report> reportElementConverter = converterManager.get(Report.class);
        String wikiContent = reportElementConverter.convert(report, converterManager);
        logger.debug("successfully converted the report to the wiki representation");
        return wikiContent;
    }

    @Override
    public String saveAsFile(String content, String fileName, String location) {
        fileName = fileName.replace(XML_FILE_EXTENSTION, WIKI_FILE_EXTENSION);
        String filePath = location + File.separator + fileName;
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
            outStream.writeBytes(content);
            outStream.close();
            logger.debug("Saving wiki file: {}", filePath);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return filePath;
    }
}
