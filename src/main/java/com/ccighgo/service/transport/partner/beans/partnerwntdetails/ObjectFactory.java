//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.13 at 03:48:29 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partnerwntdetails;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.partnerwntdetails package. 
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

    private final static QName _PartnerWnTDashboard_QNAME = new QName("http://www.ccighgo.com/partnerwnt", "PartnerWnTDashboard");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.partnerwntdetails
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PartnerWnTDashboard }
     * 
     */
    public PartnerWnTDashboard createPartnerWnTDashboard() {
        return new PartnerWnTDashboard();
    }

    /**
     * Create an instance of {@link PartnerWnTAnnouncement }
     * 
     */
    public PartnerWnTAnnouncement createPartnerWnTAnnouncement() {
        return new PartnerWnTAnnouncement();
    }

    /**
     * Create an instance of {@link PartnerWnTWorkQueueCategory }
     * 
     */
    public PartnerWnTWorkQueueCategory createPartnerWnTWorkQueueCategory() {
        return new PartnerWnTWorkQueueCategory();
    }

    /**
     * Create an instance of {@link PartnerWnTWorkQueueType }
     * 
     */
    public PartnerWnTWorkQueueType createPartnerWnTWorkQueueType() {
        return new PartnerWnTWorkQueueType();
    }

    /**
     * Create an instance of {@link PartnerWnTQuickStatType }
     * 
     */
    public PartnerWnTQuickStatType createPartnerWnTQuickStatType() {
        return new PartnerWnTQuickStatType();
    }

    /**
     * Create an instance of {@link PartnerWnTQuickStatCategory }
     * 
     */
    public PartnerWnTQuickStatCategory createPartnerWnTQuickStatCategory() {
        return new PartnerWnTQuickStatCategory();
    }

    /**
     * Create an instance of {@link PartnerWnTCCIContact }
     * 
     */
    public PartnerWnTCCIContact createPartnerWnTCCIContact() {
        return new PartnerWnTCCIContact();
    }

    /**
     * Create an instance of {@link PartnerWnTProgram }
     * 
     */
    public PartnerWnTProgram createPartnerWnTProgram() {
        return new PartnerWnTProgram();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerWnTDashboard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/partnerwnt", name = "PartnerWnTDashboard")
    public JAXBElement<PartnerWnTDashboard> createPartnerWnTDashboard(PartnerWnTDashboard value) {
        return new JAXBElement<PartnerWnTDashboard>(_PartnerWnTDashboard_QNAME, PartnerWnTDashboard.class, null, value);
    }

}
