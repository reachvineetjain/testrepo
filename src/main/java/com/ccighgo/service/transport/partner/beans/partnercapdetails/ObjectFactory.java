//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.13 at 03:48:23 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partnercapdetails;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.partnercapdetails package. 
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

    private final static QName _PartnerCAPDashboard_QNAME = new QName("http://www.ccighgo.com/partnercap", "PartnerCAPDashboard");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.partnercapdetails
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PartnerCAPDashboard }
     * 
     */
    public PartnerCAPDashboard createPartnerCAPDashboard() {
        return new PartnerCAPDashboard();
    }

    /**
     * Create an instance of {@link PartnerCAPAnnouncement }
     * 
     */
    public PartnerCAPAnnouncement createPartnerCAPAnnouncement() {
        return new PartnerCAPAnnouncement();
    }

    /**
     * Create an instance of {@link PartnerCAPProgram }
     * 
     */
    public PartnerCAPProgram createPartnerCAPProgram() {
        return new PartnerCAPProgram();
    }

    /**
     * Create an instance of {@link PartnerCAPQuickStatType }
     * 
     */
    public PartnerCAPQuickStatType createPartnerCAPQuickStatType() {
        return new PartnerCAPQuickStatType();
    }

    /**
     * Create an instance of {@link PartnerCAPQuickStatCategory }
     * 
     */
    public PartnerCAPQuickStatCategory createPartnerCAPQuickStatCategory() {
        return new PartnerCAPQuickStatCategory();
    }

    /**
     * Create an instance of {@link PartnerCAPWorkQueueType }
     * 
     */
    public PartnerCAPWorkQueueType createPartnerCAPWorkQueueType() {
        return new PartnerCAPWorkQueueType();
    }

    /**
     * Create an instance of {@link PartnerCAPCCIContact }
     * 
     */
    public PartnerCAPCCIContact createPartnerCAPCCIContact() {
        return new PartnerCAPCCIContact();
    }

    /**
     * Create an instance of {@link PartnerCAPWorkQueueCategory }
     * 
     */
    public PartnerCAPWorkQueueCategory createPartnerCAPWorkQueueCategory() {
        return new PartnerCAPWorkQueueCategory();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerCAPDashboard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/partnercap", name = "PartnerCAPDashboard")
    public JAXBElement<PartnerCAPDashboard> createPartnerCAPDashboard(PartnerCAPDashboard value) {
        return new JAXBElement<PartnerCAPDashboard>(_PartnerCAPDashboard_QNAME, PartnerCAPDashboard.class, null, value);
    }

}