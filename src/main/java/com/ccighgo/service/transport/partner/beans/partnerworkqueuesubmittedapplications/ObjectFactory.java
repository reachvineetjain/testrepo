//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.13 at 05:00:24 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications package. 
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

    private final static QName _PartnerWorkQueueSubmittedApplications_QNAME = new QName("http://www.ccighgo.com/partnerworkqueuesubmittedapplications", "PartnerWorkQueueSubmittedApplications");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.partnerworkqueuesubmittedapplications
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PartnerWorkQueueSubmittedApplications }
     * 
     */
    public PartnerWorkQueueSubmittedApplications createPartnerWorkQueueSubmittedApplications() {
        return new PartnerWorkQueueSubmittedApplications();
    }

    /**
     * Create an instance of {@link PartnerWorkQueueSubmittedApplicationsDetail }
     * 
     */
    public PartnerWorkQueueSubmittedApplicationsDetail createPartnerWorkQueueSubmittedApplicationsDetail() {
        return new PartnerWorkQueueSubmittedApplicationsDetail();
    }

    /**
     * Create an instance of {@link PartnerAdminWorkQueueTypeCategoryApplicationNotes }
     * 
     */
    public PartnerAdminWorkQueueTypeCategoryApplicationNotes createPartnerAdminWorkQueueTypeCategoryApplicationNotes() {
        return new PartnerAdminWorkQueueTypeCategoryApplicationNotes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerWorkQueueSubmittedApplications }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/partnerworkqueuesubmittedapplications", name = "PartnerWorkQueueSubmittedApplications")
    public JAXBElement<PartnerWorkQueueSubmittedApplications> createPartnerWorkQueueSubmittedApplications(PartnerWorkQueueSubmittedApplications value) {
        return new JAXBElement<PartnerWorkQueueSubmittedApplications>(_PartnerWorkQueueSubmittedApplications_QNAME, PartnerWorkQueueSubmittedApplications.class, null, value);
    }

}
