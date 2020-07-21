package xml.converter.wiki.service;

public interface ConverterSchedulerService {

    void scheduleConversion(String filePath, String outputLocation);

    void monitorInputFolder(String inputLocation, String outputLocation);

}
