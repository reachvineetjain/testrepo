//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.06 at 03:55:26 PM CDT 
//


package com.ccighgo.service.transport.season.beans.assignerdstosuperregion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.season.beans.assignerdstosuperregion package. 
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

    private final static QName _AssignedERDsToSuperRegion_QNAME = new QName("http://www.ccighgo.com/erdstoregion", "AssignedERDsToSuperRegion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.season.beans.assignerdstosuperregion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AssignedERDToSuperRegion }
     * 
     */
    public AssignedERDToSuperRegion createAssignedERDToSuperRegion() {
        return new AssignedERDToSuperRegion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignedERDToSuperRegion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/erdstoregion", name = "AssignedERDsToSuperRegion")
    public JAXBElement<AssignedERDToSuperRegion> createAssignedERDsToSuperRegion(AssignedERDToSuperRegion value) {
        return new JAXBElement<AssignedERDToSuperRegion>(_AssignedERDsToSuperRegion_QNAME, AssignedERDToSuperRegion.class, null, value);
    }

}
