//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.11 at 09:54:55 AM CST 
//


package com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail package. 
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

    private final static QName _PartnerAdminIHPSeasonDetails_QNAME = new QName("http://www.ccighgo.com/partadminihpdetail", "PartnerAdminIHPSeasonDetails");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.partner.admin.ihpseason.detail
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PartnerAdminIHPSeasonDetails }
     * 
     */
    public PartnerAdminIHPSeasonDetails createPartnerAdminIHPSeasonDetails() {
        return new PartnerAdminIHPSeasonDetails();
    }

    /**
     * Create an instance of {@link OperatingAgreement }
     * 
     */
    public OperatingAgreement createOperatingAgreement() {
        return new OperatingAgreement();
    }

    /**
     * Create an instance of {@link NoteTopics }
     * 
     */
    public NoteTopics createNoteTopics() {
        return new NoteTopics();
    }

    /**
     * Create an instance of {@link OperatingAgreements }
     * 
     */
    public OperatingAgreements createOperatingAgreements() {
        return new OperatingAgreements();
    }

    /**
     * Create an instance of {@link DocumentType }
     * 
     */
    public DocumentType createDocumentType() {
        return new DocumentType();
    }

    /**
     * Create an instance of {@link Creator }
     * 
     */
    public Creator createCreator() {
        return new Creator();
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link SeasonStatus }
     * 
     */
    public SeasonStatus createSeasonStatus() {
        return new SeasonStatus();
    }

    /**
     * Create an instance of {@link NoteTags }
     * 
     */
    public NoteTags createNoteTags() {
        return new NoteTags();
    }

    /**
     * Create an instance of {@link PartnerSeasonStatus }
     * 
     */
    public PartnerSeasonStatus createPartnerSeasonStatus() {
        return new PartnerSeasonStatus();
    }

    /**
     * Create an instance of {@link Documents }
     * 
     */
    public Documents createDocuments() {
        return new Documents();
    }

    /**
     * Create an instance of {@link Note }
     * 
     */
    public Note createNote() {
        return new Note();
    }

    /**
     * Create an instance of {@link Topic }
     * 
     */
    public Topic createTopic() {
        return new Topic();
    }

    /**
     * Create an instance of {@link PartnerSeasonDetails }
     * 
     */
    public PartnerSeasonDetails createPartnerSeasonDetails() {
        return new PartnerSeasonDetails();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerAdminIHPSeasonDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/partadminihpdetail", name = "PartnerAdminIHPSeasonDetails")
    public JAXBElement<PartnerAdminIHPSeasonDetails> createPartnerAdminIHPSeasonDetails(PartnerAdminIHPSeasonDetails value) {
        return new JAXBElement<PartnerAdminIHPSeasonDetails>(_PartnerAdminIHPSeasonDetails_QNAME, PartnerAdminIHPSeasonDetails.class, null, value);
    }

}
