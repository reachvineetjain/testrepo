//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.19 at 04:03:10 PM CDT 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata package. 
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

    private final static QName _PartnerRecruitmentAdminLead_QNAME = new QName("http://www.ccighgo.com/adminLeadViewForPartnerInquiryData", "PartnerRecruitmentAdminLead");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.integration.thirdparty.beans.adminleadviewforpartnerinquirydata
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PartnerRecruitmentAdminLead }
     * 
     */
    public PartnerRecruitmentAdminLead createPartnerRecruitmentAdminLead() {
        return new PartnerRecruitmentAdminLead();
    }

    /**
     * Create an instance of {@link PartnerRecruitmentAdminScreeningNotes }
     * 
     */
    public PartnerRecruitmentAdminScreeningNotes createPartnerRecruitmentAdminScreeningNotes() {
        return new PartnerRecruitmentAdminScreeningNotes();
    }

    /**
     * Create an instance of {@link PartnerRecruitmentAdminLeadScreeningDetail }
     * 
     */
    public PartnerRecruitmentAdminLeadScreeningDetail createPartnerRecruitmentAdminLeadScreeningDetail() {
        return new PartnerRecruitmentAdminLeadScreeningDetail();
    }

    /**
     * Create an instance of {@link PartnerRecruitmentAdminScreeningAdditionalInfo }
     * 
     */
    public PartnerRecruitmentAdminScreeningAdditionalInfo createPartnerRecruitmentAdminScreeningAdditionalInfo() {
        return new PartnerRecruitmentAdminScreeningAdditionalInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerRecruitmentAdminLead }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/adminLeadViewForPartnerInquiryData", name = "PartnerRecruitmentAdminLead")
    public JAXBElement<PartnerRecruitmentAdminLead> createPartnerRecruitmentAdminLead(PartnerRecruitmentAdminLead value) {
        return new JAXBElement<PartnerRecruitmentAdminLead>(_PartnerRecruitmentAdminLead_QNAME, PartnerRecruitmentAdminLead.class, null, value);
    }

}