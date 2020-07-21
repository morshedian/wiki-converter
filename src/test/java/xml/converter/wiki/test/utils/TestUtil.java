package xml.converter.wiki.test.utils;

import xml.converter.wiki.model.Bold;
import xml.converter.wiki.model.Italic;
import xml.converter.wiki.model.Section;

public class TestUtil {
    public static Section createSectionWithDepth(int depth) {
        Section main = new Section();
        main.setHeading("header1");
        Section parent = main;
        for (int i = 2; i <= depth; i++) {
            Section section = new Section();
            section.setHeading(String.format("header%d", Math.min(i, 6)));
            parent.getContent().add(section);
            section.setParent(parent);
            parent = section;
        }
        return main;
    }

    public static Section createSectionWithDepthAndOtherElements(int depth) {
        Section main = new Section();
        main.setHeading("header1");
        main.getContent().add(createBold(1));
        Section parent = main;
        for (int i = 2; i <= depth; i++) {
            Section section = new Section();
            section.setHeading(String.format("header%d", Math.min(i, 6)));
            section.getContent().add(createBold(i));
            parent.getContent().add(section);
            section.setParent(parent);
            parent = section;
        }
        return main;
    }

    public static Bold createBold(int i){
        Bold bold = new Bold();
        bold.getContent().add(String.format("bold%d", i));

        Italic italic = new Italic();
        italic.getContent().add(String.format("italic%d", i));
        bold.getContent().add(italic);
        return bold;
    }
}
