//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.23 at 05:11:31 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.genericannouncement.announcements;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.genericannouncement.announcements package. 
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

    private final static QName _GenericAnnoucement_QNAME = new QName("http://www.ccighgo.com/genericannouncement", "GenericAnnoucement");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.genericannouncement.announcements
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GenericAnnoucement }
     * 
     */
    public GenericAnnoucement createGenericAnnoucement() {
        return new GenericAnnoucement();
    }

    /**
     * Create an instance of {@link GenericAnnouncementsDetails }
     * 
     */
    public GenericAnnouncementsDetails createGenericAnnouncementsDetails() {
        return new GenericAnnouncementsDetails();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenericAnnoucement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/genericannouncement", name = "GenericAnnoucement")
    public JAXBElement<GenericAnnoucement> createGenericAnnoucement(GenericAnnoucement value) {
        return new JAXBElement<GenericAnnoucement>(_GenericAnnoucement_QNAME, GenericAnnoucement.class, null, value);
    }

}
