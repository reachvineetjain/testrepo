//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.26 at 05:01:56 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail package. 
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

    private final static QName _PartnerAdminJ1SeasonDetails_QNAME = new QName("http://www.ccighgo.com/partadminj1detail", "PartnerAdminJ1SeasonDetails");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.partner.admin.j1season.detail
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PartnerAdminJ1SeasonDetails }
     * 
     */
    public PartnerAdminJ1SeasonDetails createPartnerAdminJ1SeasonDetails() {
        return new PartnerAdminJ1SeasonDetails();
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
     * Create an instance of {@link Dates }
     * 
     */
    public Dates createDates() {
        return new Dates();
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
     * Create an instance of {@link ProgramAllocations }
     * 
     */
    public ProgramAllocations createProgramAllocations() {
        return new ProgramAllocations();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerAdminJ1SeasonDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/partadminj1detail", name = "PartnerAdminJ1SeasonDetails")
    public JAXBElement<PartnerAdminJ1SeasonDetails> createPartnerAdminJ1SeasonDetails(PartnerAdminJ1SeasonDetails value) {
        return new JAXBElement<PartnerAdminJ1SeasonDetails>(_PartnerAdminJ1SeasonDetails_QNAME, PartnerAdminJ1SeasonDetails.class, null, value);
    }

}
