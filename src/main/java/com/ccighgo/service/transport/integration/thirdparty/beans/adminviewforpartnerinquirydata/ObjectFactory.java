//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.16 at 01:10:56 PM IST 
//


package com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata package. 
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

    private final static QName _PartnerRecruitmentAdmin_QNAME = new QName("http://www.ccighgo.com/adminViewForPartnerInquiryData", "PartnerRecruitmentAdmin");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.integration.thirdparty.beans.adminviewforpartnerinquirydata
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PartnerRecruitmentAdmin }
     * 
     */
    public PartnerRecruitmentAdmin createPartnerRecruitmentAdmin() {
        return new PartnerRecruitmentAdmin();
    }

    /**
     * Create an instance of {@link PartnerRecruitmentAdminScreeningNotes }
     * 
     */
    public PartnerRecruitmentAdminScreeningNotes createPartnerRecruitmentAdminScreeningNotes() {
        return new PartnerRecruitmentAdminScreeningNotes();
    }

    /**
     * Create an instance of {@link DocumentUploadUser }
     * 
     */
    public DocumentUploadUser createDocumentUploadUser() {
        return new DocumentUploadUser();
    }

    /**
     * Create an instance of {@link PartnerRecruitmentAdminScreeningContacts }
     * 
     */
    public PartnerRecruitmentAdminScreeningContacts createPartnerRecruitmentAdminScreeningContacts() {
        return new PartnerRecruitmentAdminScreeningContacts();
    }

    /**
     * Create an instance of {@link PartnerRecruitmentAdminScreeningReferenceCheck }
     * 
     */
    public PartnerRecruitmentAdminScreeningReferenceCheck createPartnerRecruitmentAdminScreeningReferenceCheck() {
        return new PartnerRecruitmentAdminScreeningReferenceCheck();
    }

    /**
     * Create an instance of {@link AdminPartnerHspSettings }
     * 
     */
    public AdminPartnerHspSettings createAdminPartnerHspSettings() {
        return new AdminPartnerHspSettings();
    }

    /**
     * Create an instance of {@link PartnerRecruitmentAdminScreeningDocuments }
     * 
     */
    public PartnerRecruitmentAdminScreeningDocuments createPartnerRecruitmentAdminScreeningDocuments() {
        return new PartnerRecruitmentAdminScreeningDocuments();
    }

    /**
     * Create an instance of {@link AdminPartnerRecruitmentScreeningDetail }
     * 
     */
    public AdminPartnerRecruitmentScreeningDetail createAdminPartnerRecruitmentScreeningDetail() {
        return new AdminPartnerRecruitmentScreeningDetail();
    }

    /**
     * Create an instance of {@link AdminPartnerProgramsElgibilityAndCCIContact }
     * 
     */
    public AdminPartnerProgramsElgibilityAndCCIContact createAdminPartnerProgramsElgibilityAndCCIContact() {
        return new AdminPartnerProgramsElgibilityAndCCIContact();
    }

    /**
     * Create an instance of {@link NoteUserCreator }
     * 
     */
    public NoteUserCreator createNoteUserCreator() {
        return new NoteUserCreator();
    }

    /**
     * Create an instance of {@link CCIInquiryFormPerson }
     * 
     */
    public CCIInquiryFormPerson createCCIInquiryFormPerson() {
        return new CCIInquiryFormPerson();
    }

    /**
     * Create an instance of {@link PartnerRecruitmentAdminScreeningOffices }
     * 
     */
    public PartnerRecruitmentAdminScreeningOffices createPartnerRecruitmentAdminScreeningOffices() {
        return new PartnerRecruitmentAdminScreeningOffices();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerRecruitmentAdmin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/adminViewForPartnerInquiryData", name = "PartnerRecruitmentAdmin")
    public JAXBElement<PartnerRecruitmentAdmin> createPartnerRecruitmentAdmin(PartnerRecruitmentAdmin value) {
        return new JAXBElement<PartnerRecruitmentAdmin>(_PartnerRecruitmentAdmin_QNAME, PartnerRecruitmentAdmin.class, null, value);
    }

}
