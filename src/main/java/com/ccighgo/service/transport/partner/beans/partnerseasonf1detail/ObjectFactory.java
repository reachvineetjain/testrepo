//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.04 at 12:57:32 AM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerseasonf1detail;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.partnerseasonf1detail package. 
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

    private final static QName _PartnerSeasonF1Detail_QNAME = new QName("http://www.ccighgo.com/partnerseasonf1detail", "PartnerSeasonF1Detail");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.partnerseasonf1detail
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PartnerSeasonF1Detail }
     * 
     */
    public PartnerSeasonF1Detail createPartnerSeasonF1Detail() {
        return new PartnerSeasonF1Detail();
    }

    /**
     * Create an instance of {@link NoteTopics }
     * 
     */
    public NoteTopics createNoteTopics() {
        return new NoteTopics();
    }

    /**
     * Create an instance of {@link Creator }
     * 
     */
    public Creator createCreator() {
        return new Creator();
    }

    /**
     * Create an instance of {@link PartnerProgram }
     * 
     */
    public PartnerProgram createPartnerProgram() {
        return new PartnerProgram();
    }

    /**
     * Create an instance of {@link PartnerSeasonAnnouncements }
     * 
     */
    public PartnerSeasonAnnouncements createPartnerSeasonAnnouncements() {
        return new PartnerSeasonAnnouncements();
    }

    /**
     * Create an instance of {@link PartnerDepartment }
     * 
     */
    public PartnerDepartment createPartnerDepartment() {
        return new PartnerDepartment();
    }

    /**
     * Create an instance of {@link PartnerSeasonF1ProgramAllocations }
     * 
     */
    public PartnerSeasonF1ProgramAllocations createPartnerSeasonF1ProgramAllocations() {
        return new PartnerSeasonF1ProgramAllocations();
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
     * Create an instance of {@link Note }
     * 
     */
    public Note createNote() {
        return new Note();
    }

    /**
     * Create an instance of {@link PartnerHLSeason }
     * 
     */
    public PartnerHLSeason createPartnerHLSeason() {
        return new PartnerHLSeason();
    }

    /**
     * Create an instance of {@link ApplicationDeadlilneDatesF1Allocations }
     * 
     */
    public ApplicationDeadlilneDatesF1Allocations createApplicationDeadlilneDatesF1Allocations() {
        return new ApplicationDeadlilneDatesF1Allocations();
    }

    /**
     * Create an instance of {@link Topic }
     * 
     */
    public Topic createTopic() {
        return new Topic();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerSeasonF1Detail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/partnerseasonf1detail", name = "PartnerSeasonF1Detail")
    public JAXBElement<PartnerSeasonF1Detail> createPartnerSeasonF1Detail(PartnerSeasonF1Detail value) {
        return new JAXBElement<PartnerSeasonF1Detail>(_PartnerSeasonF1Detail_QNAME, PartnerSeasonF1Detail.class, null, value);
    }

}
