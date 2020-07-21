package xml.converter.wiki.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xml.converter.wiki.exception.InvalidDirectoryExecption;
import xml.converter.wiki.runnable.ConverterRunnable;
import xml.converter.wiki.runnable.InputMonitorRunnable;
import xml.converter.wiki.service.ConverterSchedulerService;
import xml.converter.wiki.service.WikiConverterService;
import xml.converter.wiki.service.XmlValidatorService;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConverterSchedulerServiceImpl implements ConverterSchedulerService {

    private final static Logger logger = LoggerFactory.getLogger(ConverterSchedulerServiceImpl.class);
    public static final int THREAD_POOL_SIZE = 10;
    public static final int MONITORING_CYCLE = 5;
    public static final int CONVERSION_DELAY = 10;
    public static final int CYCLE_START_DELAY = 1;

    private final ScheduledExecutorService executor;
    private final WikiConverterService converterService;
    private final XmlValidatorService xmlValidatorService;


    public ConverterSchedulerServiceImpl(WikiConverterService converterService, XmlValidatorService xmlValidatorService) {
        executor = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);
        this.converterService = converterService;
        this.xmlValidatorService = xmlValidatorService;
    }

    @Override
    public void scheduleConversion(String filePath, String outputLocation) {
        logger.debug("scheduling the xml file for conversion: {}", filePath);
        ConverterRunnable runnable = new ConverterRunnable(converterService, filePath, outputLocation);
        executor.schedule(runnable, CONVERSION_DELAY, TimeUnit.MICROSECONDS);
    }

    public void monitorInputFolder(String inputLocation, String outputLocation) {
        if (!validatePath(inputLocation) || !validatePath(outputLocation)) {
            executor.shutdownNow();
            throw new InvalidDirectoryExecption("input or out folder is wrong.");
        } else {
            logger.debug("starting the monitor for the input directory: {}", inputLocation);
            InputMonitorRunnable inputMonitorRunnable = new InputMonitorRunnable(inputLocation, outputLocation, this, xmlValidatorService);
            executor.scheduleWithFixedDelay(inputMonitorRunnable, CYCLE_START_DELAY, MONITORING_CYCLE, TimeUnit.SECONDS);
        }
    }

    public boolean validatePath(String path) {
        File dir = new File(path);
        if (dir.exists() && dir.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }
}
