//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.03 at 03:52:48 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.PartnersSeasons;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.PartnersSeasons package. 
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

    private final static QName _PartnersSeasons_QNAME = new QName("http://www.ccighgo.com/partnersseasons", "PartnersSeasons");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.PartnersSeasons
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PartnersSeasons }
     * 
     */
    public PartnersSeasons createPartnersSeasons() {
        return new PartnersSeasons();
    }

    /**
     * Create an instance of {@link PartnersSeasonsNotesCreator }
     * 
     */
    public PartnersSeasonsNotesCreator createPartnersSeasonsNotesCreator() {
        return new PartnersSeasonsNotesCreator();
    }

    /**
     * Create an instance of {@link ProgramAllocationsUnguranteed }
     * 
     */
    public ProgramAllocationsUnguranteed createProgramAllocationsUnguranteed() {
        return new ProgramAllocationsUnguranteed();
    }

    /**
     * Create an instance of {@link Announcements }
     * 
     */
    public Announcements createAnnouncements() {
        return new Announcements();
    }

    /**
     * Create an instance of {@link PartnersSeasonsNotes }
     * 
     */
    public PartnersSeasonsNotes createPartnersSeasonsNotes() {
        return new PartnersSeasonsNotes();
    }

    /**
     * Create an instance of {@link ProgramAllocationsGuaranteed }
     * 
     */
    public ProgramAllocationsGuaranteed createProgramAllocationsGuaranteed() {
        return new ProgramAllocationsGuaranteed();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnersSeasons }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/partnersseasons", name = "PartnersSeasons")
    public JAXBElement<PartnersSeasons> createPartnersSeasons(PartnersSeasons value) {
        return new JAXBElement<PartnersSeasons>(_PartnersSeasons_QNAME, PartnersSeasons.class, null, value);
    }

}
