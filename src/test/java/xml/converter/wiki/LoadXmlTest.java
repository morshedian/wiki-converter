package xml.converter.wiki;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import xml.converter.wiki.converter.manager.ConverterManager;
import xml.converter.wiki.converter.spi.ElementConverter;
import xml.converter.wiki.model.Report;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileReader;
import java.io.IOException;

public class LoadXmlTest {

    @Test
    public void load() throws JAXBException, IOException {
        Report report = unmarshall("src/test/resources/example1.xml");
        Assertions.assertNotNull(report);

        ConverterManager manager = ConverterManager.getInstance();
        ElementConverter<Report> converter = manager.get(Report.class);
        String s = converter.convert(report, manager);
        System.out.println(s);
    }

    @Test
    public void load2() throws JAXBException, IOException {
        Report report = unmarshall("src/test/resources/example2.xml");
        Assertions.assertNotNull(report);

        ConverterManager manager = ConverterManager.getInstance();
        ElementConverter<Report> converter = manager.get(Report.class);
        String s = converter.convert(report, manager);
        System.out.println(s);
    }

    public static Report unmarshall(String file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Report.class);
        return (Report) context.createUnmarshaller()
                .unmarshal(new FileReader(file));
    }
}
