package xml.converter.wiki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xml.converter.wiki.converter.manager.ConverterManager;
import xml.converter.wiki.exception.InvalidDirectoryExecption;
import xml.converter.wiki.service.ConverterSchedulerService;
import xml.converter.wiki.service.WikiConverterService;
import xml.converter.wiki.service.impl.ConverterSchedulerServiceImpl;
import xml.converter.wiki.service.impl.WikiConverterServiceImpl;
import xml.converter.wiki.service.impl.XmlValidatorServiceImpl;

public class ConverterApplication {

    private static final Logger logger = LoggerFactory.getLogger(ConverterApplication.class);
    public static final int INPUT_DIR_ARG_INDEX = 0;
    public static final int OUTPUT_DIR_ARG_INDEX = 1;
    public static final int EXPECTED_ARG_COUNT = 2;

    public static void main(String[] args) {
        if (args.length == EXPECTED_ARG_COUNT) {
            String inputPath = args[INPUT_DIR_ARG_INDEX];
            String outputPath = args[OUTPUT_DIR_ARG_INDEX];
            ConverterManager converterManager = ConverterManager.getInstance();
            WikiConverterService wikiConverterService = new WikiConverterServiceImpl(converterManager);
            XmlValidatorServiceImpl xmlValidatorService = new XmlValidatorServiceImpl();
            ConverterSchedulerService converterSchedulerService = new ConverterSchedulerServiceImpl(wikiConverterService, xmlValidatorService);
            try {
                converterSchedulerService.monitorInputFolder(inputPath, outputPath);
                logger.info("application started ...");
            }catch (InvalidDirectoryExecption e){
                logger.debug("invalid input({}) or output({}) directory", inputPath, outputPath);
                logger.debug("closing application...");
            }
        }else {
            logger.info("Bad input, application closing...");
        }
    }
}
