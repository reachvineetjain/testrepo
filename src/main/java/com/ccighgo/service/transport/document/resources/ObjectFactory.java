//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.09 at 06:29:38 PM IST 
//


package com.ccighgo.service.transport.document.resources;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.document.resources package. 
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

    private final static QName _DocumentResources_QNAME = new QName("http://www.ccighgo.com/drs", "DocumentResources");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.document.resources
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DocumentResources }
     * 
     */
    public DocumentResources createDocumentResources() {
        return new DocumentResources();
    }

    /**
     * Create an instance of {@link ProgramResources }
     * 
     */
    public ProgramResources createProgramResources() {
        return new ProgramResources();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentResources }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/drs", name = "DocumentResources")
    public JAXBElement<DocumentResources> createDocumentResources(DocumentResources value) {
        return new JAXBElement<DocumentResources>(_DocumentResources_QNAME, DocumentResources.class, null, value);
    }

}
