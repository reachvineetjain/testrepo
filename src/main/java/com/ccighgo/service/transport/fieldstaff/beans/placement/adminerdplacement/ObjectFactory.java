//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.21 at 03:43:23 PM CST 
//


package com.ccighgo.service.transport.fieldstaff.beans.placement.adminerdplacement;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.fieldstaff.beans.placement.adminerdplacement package. 
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

    private final static QName _AdminERDPlacements_QNAME = new QName("http://www.ccighgo.com/fieldstaffdashboard", "AdminERDPlacements");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.fieldstaff.beans.placement.adminerdplacement
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AdminERDPlacements }
     * 
     */
    public AdminERDPlacements createAdminERDPlacements() {
        return new AdminERDPlacements();
    }

    /**
     * Create an instance of {@link AdminERDPlacement }
     * 
     */
    public AdminERDPlacement createAdminERDPlacement() {
        return new AdminERDPlacement();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdminERDPlacements }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/fieldstaffdashboard", name = "AdminERDPlacements")
    public JAXBElement<AdminERDPlacements> createAdminERDPlacements(AdminERDPlacements value) {
        return new JAXBElement<AdminERDPlacements>(_AdminERDPlacements_QNAME, AdminERDPlacements.class, null, value);
    }

}
