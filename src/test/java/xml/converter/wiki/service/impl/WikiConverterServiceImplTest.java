package xml.converter.wiki.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import xml.converter.wiki.converter.manager.ConverterManager;
import xml.converter.wiki.model.Report;
import xml.converter.wiki.model.Section;
import xml.converter.wiki.service.WikiConverterService;

import static org.junit.jupiter.api.Assertions.*;
import static xml.converter.wiki.test.utils.TestUtil.createSectionWithDepthAndOtherElements;

class WikiConverterServiceImplTest {
    
    private static WikiConverterService wikiConverterService;
    private static ConverterManager converterManager;
    
    @BeforeAll
    public static void setup(){
        converterManager = ConverterManager.getInstance();
        wikiConverterService = new WikiConverterServiceImpl(converterManager);
    }

    @Test
    public void convertSectionNull() {
        String expected = "";
        String result = wikiConverterService.convert(null);
        assertEquals(expected, result);
    }

    @Test
    public void convert() {
        Report report = new Report();
        String expected = "";
        String result = wikiConverterService.convert(report);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth1() {
        Report report = new Report();
        Section section = createSectionWithDepthAndOtherElements(1);
        section.setParent(report);
        report.getContent().add(section);
        String expected = "=header1=\n" +
                "'''bold1''italic1'''''";
        String result = wikiConverterService.convert(report);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth1WithString() {
        Report report = new Report();
        Section section = createSectionWithDepthAndOtherElements(1);
        section.setParent(report);
        report.getContent().add("start");
        report.getContent().add(section);
        String expected = "start\n" +
                "=header1=\n" +
                "'''bold1''italic1'''''";
        String result = wikiConverterService.convert(report);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth3() {
        Report report = new Report();
        Section section = createSectionWithDepthAndOtherElements(3);
        section.setParent(report);
        report.getContent().add(section);
        String expected = "=header1=\n" +
                "'''bold1''italic1'''''\n" +
                "==header2==\n" +
                "'''bold2''italic2'''''\n" +
                "===header3===\n" +
                "'''bold3''italic3'''''";
        String result = wikiConverterService.convert(report);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth3WithString() {
        Report report = new Report();
        Section section = createSectionWithDepthAndOtherElements(3);
        section.setParent(report);
        report.getContent().add("start");
        report.getContent().add(section);
        String expected = "start\n" +
                "=header1=\n" +
                "'''bold1''italic1'''''\n" +
                "==header2==\n" +
                "'''bold2''italic2'''''\n" +
                "===header3===\n" +
                "'''bold3''italic3'''''";
        String result = wikiConverterService.convert(report);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth6() {
        Report report = new Report();
        Section section = createSectionWithDepthAndOtherElements(6);
        section.setParent(report);
        report.getContent().add(section);
        String expected = "=header1=\n" +
                "'''bold1''italic1'''''\n" +
                "==header2==\n" +
                "'''bold2''italic2'''''\n" +
                "===header3===\n" +
                "'''bold3''italic3'''''\n" +
                "====header4====\n" +
                "'''bold4''italic4'''''\n" +
                "=====header5=====\n" +
                "'''bold5''italic5'''''\n" +
                "======header6======\n" +
                "'''bold6''italic6'''''";
        String result = wikiConverterService.convert(report);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth6WithString() {
        Report report = new Report();
        Section section = createSectionWithDepthAndOtherElements(6);
        section.setParent(report);
        report.getContent().add("start");
        report.getContent().add(section);
        String expected = "start\n" +
                "=header1=\n" +
                "'''bold1''italic1'''''\n" +
                "==header2==\n" +
                "'''bold2''italic2'''''\n" +
                "===header3===\n" +
                "'''bold3''italic3'''''\n" +
                "====header4====\n" +
                "'''bold4''italic4'''''\n" +
                "=====header5=====\n" +
                "'''bold5''italic5'''''\n" +
                "======header6======\n" +
                "'''bold6''italic6'''''";
        String result = wikiConverterService.convert(report);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth8() {
        Report report = new Report();
        Section section = createSectionWithDepthAndOtherElements(8);
        section.setParent(report);
        report.getContent().add(section);
        String expected = "=header1=\n" +
                "'''bold1''italic1'''''\n" +
                "==header2==\n" +
                "'''bold2''italic2'''''\n" +
                "===header3===\n" +
                "'''bold3''italic3'''''\n" +
                "====header4====\n" +
                "'''bold4''italic4'''''\n" +
                "=====header5=====\n" +
                "'''bold5''italic5'''''\n" +
                "======header6======\n" +
                "'''bold6''italic6'''''\n" +
                "======header6======\n" +
                "'''bold7''italic7'''''\n" +
                "======header6======\n" +
                "'''bold8''italic8'''''";
        String result = wikiConverterService.convert(report);
        assertEquals(expected, result);
    }

    @Test
    public void convertDepth8WithString() {
        Report report = new Report();
        Section section = createSectionWithDepthAndOtherElements(8);
        section.setParent(report);
        report.getContent().add("start");
        report.getContent().add(section);
        String expected = "start\n" +
                "=header1=\n" +
                "'''bold1''italic1'''''\n" +
                "==header2==\n" +
                "'''bold2''italic2'''''\n" +
                "===header3===\n" +
                "'''bold3''italic3'''''\n" +
                "====header4====\n" +
                "'''bold4''italic4'''''\n" +
                "=====header5=====\n" +
                "'''bold5''italic5'''''\n" +
                "======header6======\n" +
                "'''bold6''italic6'''''\n" +
                "======header6======\n" +
                "'''bold7''italic7'''''\n" +
                "======header6======\n" +
                "'''bold8''italic8'''''";
        String result = wikiConverterService.convert(report);
        assertEquals(expected, result);
    }
    

}