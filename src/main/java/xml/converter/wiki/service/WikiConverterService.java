package xml.converter.wiki.service;

import xml.converter.wiki.model.Report;

public interface WikiConverterService {

    String convert(Report report);

    String saveAsFile(String content, String fileName, String location);

}
