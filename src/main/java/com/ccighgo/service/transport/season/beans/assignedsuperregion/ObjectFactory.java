//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.07 at 05:54:59 PM IST 
//


package com.ccighgo.service.transport.season.beans.assignedsuperregion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.season.beans.assignedsuperregion package. 
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

    private final static QName _AssignedSuperRegion_QNAME = new QName("http://www.ccighgo.com/assignedsuperregion", "AssignedSuperRegion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.season.beans.assignedsuperregion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AssignedSuperRegion }
     * 
     */
    public AssignedSuperRegion createAssignedSuperRegion() {
        return new AssignedSuperRegion();
    }

    /**
     * Create an instance of {@link AssignedERDStaff }
     * 
     */
    public AssignedERDStaff createAssignedERDStaff() {
        return new AssignedERDStaff();
    }

    /**
     * Create an instance of {@link SuperRegion }
     * 
     */
    public SuperRegion createSuperRegion() {
        return new SuperRegion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignedSuperRegion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/assignedsuperregion", name = "AssignedSuperRegion")
    public JAXBElement<AssignedSuperRegion> createAssignedSuperRegion(AssignedSuperRegion value) {
        return new JAXBElement<AssignedSuperRegion>(_AssignedSuperRegion_QNAME, AssignedSuperRegion.class, null, value);
    }

}
