//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.15 at 04:14:54 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partnerf1details;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.partnerf1details package. 
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

    private final static QName _PartnerF1Dashboard_QNAME = new QName("http://www.ccighgo.com/partnerf1", "PartnerF1Dashboard");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.partnerf1details
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PartnerF1Dashboard }
     * 
     */
    public PartnerF1Dashboard createPartnerF1Dashboard() {
        return new PartnerF1Dashboard();
    }

    /**
     * Create an instance of {@link PartnerF1QuickStatType }
     * 
     */
    public PartnerF1QuickStatType createPartnerF1QuickStatType() {
        return new PartnerF1QuickStatType();
    }

    /**
     * Create an instance of {@link F1Allocation }
     * 
     */
    public F1Allocation createF1Allocation() {
        return new F1Allocation();
    }

    /**
     * Create an instance of {@link PartnerF1Program }
     * 
     */
    public PartnerF1Program createPartnerF1Program() {
        return new PartnerF1Program();
    }

    /**
     * Create an instance of {@link PartnerF1WorkQueueType }
     * 
     */
    public PartnerF1WorkQueueType createPartnerF1WorkQueueType() {
        return new PartnerF1WorkQueueType();
    }

    /**
     * Create an instance of {@link PartnerF1CCIContact }
     * 
     */
    public PartnerF1CCIContact createPartnerF1CCIContact() {
        return new PartnerF1CCIContact();
    }

    /**
     * Create an instance of {@link PartnerF1WorkQueueCategory }
     * 
     */
    public PartnerF1WorkQueueCategory createPartnerF1WorkQueueCategory() {
        return new PartnerF1WorkQueueCategory();
    }

    /**
     * Create an instance of {@link PartnerF1Announcement }
     * 
     */
    public PartnerF1Announcement createPartnerF1Announcement() {
        return new PartnerF1Announcement();
    }

    /**
     * Create an instance of {@link PartnerF1QuickStatCategory }
     * 
     */
    public PartnerF1QuickStatCategory createPartnerF1QuickStatCategory() {
        return new PartnerF1QuickStatCategory();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerF1Dashboard }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/partnerf1", name = "PartnerF1Dashboard")
    public JAXBElement<PartnerF1Dashboard> createPartnerF1Dashboard(PartnerF1Dashboard value) {
        return new JAXBElement<PartnerF1Dashboard>(_PartnerF1Dashboard_QNAME, PartnerF1Dashboard.class, null, value);
    }

}
