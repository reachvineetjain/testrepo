//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.27 at 05:47:44 PM CDT 
//


package com.ccighgo.service.transport.season.beans.seasonprogram;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.season.beans.seasonprogram package. 
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

    private final static QName _SeasonPrograms_QNAME = new QName("http://www.ccighgo.com/seasonprogram", "SeasonPrograms");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.season.beans.seasonprogram
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SeasonPrograms }
     * 
     */
    public SeasonPrograms createSeasonPrograms() {
        return new SeasonPrograms();
    }

    /**
     * Create an instance of {@link SeasonProgram }
     * 
     */
    public SeasonProgram createSeasonProgram() {
        return new SeasonProgram();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SeasonPrograms }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/seasonprogram", name = "SeasonPrograms")
    public JAXBElement<SeasonPrograms> createSeasonPrograms(SeasonPrograms value) {
        return new JAXBElement<SeasonPrograms>(_SeasonPrograms_QNAME, SeasonPrograms.class, null, value);
    }

}
