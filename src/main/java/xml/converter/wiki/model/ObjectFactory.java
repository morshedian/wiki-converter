//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.21 at 12:18:34 AM CEST 
//


package xml.converter.wiki.model;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Report }
     *
     */
    public Report createReport() {
        return new Report();
    }

    /**
     * Create an instance of {@link Section }
     *
     */
    public Section createSection() {
        return new Section();
    }

    /**
     * Create an instance of {@link Bold }
     *
     */
    public Bold createBold() {
        return new Bold();
    }

    /**
     * Create an instance of {@link Italic }
     *
     */
    public Italic createItalic() {
        return new Italic();
    }

}
