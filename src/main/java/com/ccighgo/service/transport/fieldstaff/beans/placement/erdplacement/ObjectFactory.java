//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.21 at 11:09:26 AM CST 
//


package com.ccighgo.service.transport.fieldstaff.beans.placement.erdplacement;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.fieldstaff.beans.placement.erdplacement package. 
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

    private final static QName _ERDPlacements_QNAME = new QName("http://www.ccighgo.com/fieldstaffdashboard", "ERDPlacements");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.fieldstaff.beans.placement.erdplacement
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ERDPlacements }
     * 
     */
    public ERDPlacements createERDPlacements() {
        return new ERDPlacements();
    }

    /**
     * Create an instance of {@link ERDPlacement }
     * 
     */
    public ERDPlacement createERDPlacement() {
        return new ERDPlacement();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ERDPlacements }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/fieldstaffdashboard", name = "ERDPlacements")
    public JAXBElement<ERDPlacements> createERDPlacements(ERDPlacements value) {
        return new JAXBElement<ERDPlacements>(_ERDPlacements_QNAME, ERDPlacements.class, null, value);
    }

}
