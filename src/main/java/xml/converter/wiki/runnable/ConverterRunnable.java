package xml.converter.wiki.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xml.converter.wiki.service.WikiConverterService;
import xml.converter.wiki.service.impl.XmlParserService;

import java.io.File;

public class ConverterRunnable implements Runnable {
    private final static Logger logger = LoggerFactory.getLogger(ConverterRunnable.class);


    private WikiConverterService converterService;
    private String filePath;
    private String outputPath;
    private XmlParserService xmlParserService = XmlParserService.getInstance();

    public ConverterRunnable(WikiConverterService converterService, String filePath, String outputPath) {
        this.converterService = converterService;
        this.filePath = filePath;
        this.outputPath = outputPath;
    }

    @Override
    public void run() {
        File file = new File(filePath);

        if (file.exists()) {
            String fileName = file.getName();

            xmlParserService.unmarshall(filePath)
                    .map(converterService::convert)
                    .map(content -> converterService.saveAsFile(content.trim(), fileName, outputPath))
                    .orElse("");

            logger.debug("xml file successfully converted: {}", filePath);
        }
    }
}
