//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.11 at 03:48:48 PM CST 
//


package com.ccighgo.service.transport.sevis;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.sevis package. 
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

    private final static QName _SEVISBatchDetails_QNAME = new QName("http://www.ccighgo.com/sevisbatchdetails", "SEVISBatchDetails");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.sevis
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SEVISBatchDetails }
     * 
     */
    public SEVISBatchDetails createSEVISBatchDetails() {
        return new SEVISBatchDetails();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SEVISBatchDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/sevisbatchdetails", name = "SEVISBatchDetails")
    public JAXBElement<SEVISBatchDetails> createSEVISBatchDetails(SEVISBatchDetails value) {
        return new JAXBElement<SEVISBatchDetails>(_SEVISBatchDetails_QNAME, SEVISBatchDetails.class, null, value);
    }

}
