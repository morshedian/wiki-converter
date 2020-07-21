package xml.converter.wiki.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xml.converter.wiki.service.XmlValidatorService;
import xml.converter.wiki.service.impl.ConverterSchedulerServiceImpl;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;

public class InputMonitorRunnable implements Runnable {

    private final static Logger logger = LoggerFactory.getLogger(ConverterRunnable.class);
    private static final HashSet<String> CACHED = new HashSet<>(200);
    public static final String XML_FILE_EXTENSION = ".xml";

    private final String inputFolder;
    private final String outputFolder;
    private final ConverterSchedulerServiceImpl converterSchedulerService;
    private final XmlValidatorService xmlValidatorService;

    public InputMonitorRunnable(String inputFolder, String outputFolder,
                                ConverterSchedulerServiceImpl converterSchedulerService, XmlValidatorService xmlValidatorService) {
        this.inputFolder = inputFolder;
        this.outputFolder = outputFolder;
        this.converterSchedulerService = converterSchedulerService;
        this.xmlValidatorService = xmlValidatorService;
    }

    @Override
    public void run() {
        File dir = new File(inputFolder);
        if (dir.exists() && dir.isDirectory()) {
            File[] array = dir.listFiles(file -> file.getAbsolutePath().endsWith(XML_FILE_EXTENSION));
            Arrays.stream(array)
                    .filter(file -> {
                        boolean contains = CACHED.contains(file.getAbsolutePath());
                        if (contains) {
                            logger.debug("the file is already converted: {}", file.getAbsolutePath());
                        }
                        return !contains;
                    })
                    .filter(file -> {
                        boolean validate = xmlValidatorService.validate(file.getAbsolutePath());
                        if (!validate) {
                            logger.debug("the xml file is not compatible with the xsd schema: {}", file.getAbsolutePath());
                        }
                        return validate;
                    })
                    .forEach(file -> {
                        String absolutePath = file.getAbsolutePath();
                        converterSchedulerService.scheduleConversion(absolutePath, outputFolder);
                        logger.debug("file will be scheduled for conversion: {}", file.getAbsolutePath());
                        CACHED.add(absolutePath);
                    });
        }else {
            logger.debug("invalid input directory: {}", inputFolder);
        }


    }
}
