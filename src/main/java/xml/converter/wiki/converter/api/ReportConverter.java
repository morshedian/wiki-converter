package xml.converter.wiki.converter.api;

import xml.converter.wiki.converter.spi.ConverterRegistry;
import xml.converter.wiki.model.Report;

public class ReportConverter extends AbstractCompositeElementConverter<Report> {

    @Override
    public String convert(Report report, ConverterRegistry converterRegistry) {
        if(report == null){
            return "";
        }
        String wiki = convertChildren(report, converterRegistry);
        wiki = wiki.replaceAll("(?m)^[ \t]*\r?\n", ""); //removing empty lines
        return wiki;
    }

    @Override
    public Class<Report> getType() {
        return Report.class;
    }
}
