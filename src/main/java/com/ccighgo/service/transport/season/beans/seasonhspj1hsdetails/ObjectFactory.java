//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.06 at 09:59:37 AM CDT 
//


package com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails package. 
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

    private final static QName _SeasonHspJ1HSDetails_QNAME = new QName("http://www.ccighgo.com/j1hs", "SeasonHspJ1HSDetails");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.season.beans.seasonhspj1hsdetails
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SeasonHspJ1HSDetails }
     * 
     */
    public SeasonHspJ1HSDetails createSeasonHspJ1HSDetails() {
        return new SeasonHspJ1HSDetails();
    }

    /**
     * Create an instance of {@link J1HSBasicDetail }
     * 
     */
    public J1HSBasicDetail createJ1HSBasicDetail() {
        return new J1HSBasicDetail();
    }

    /**
     * Create an instance of {@link J1HSProgramAllocations }
     * 
     */
    public J1HSProgramAllocations createJ1HSProgramAllocations() {
        return new J1HSProgramAllocations();
    }

    /**
     * Create an instance of {@link J1HSJanStart }
     * 
     */
    public J1HSJanStart createJ1HSJanStart() {
        return new J1HSJanStart();
    }

    /**
     * Create an instance of {@link J1HSDocuments }
     * 
     */
    public J1HSDocuments createJ1HSDocuments() {
        return new J1HSDocuments();
    }

    /**
     * Create an instance of {@link J1HSFieldSettings }
     * 
     */
    public J1HSFieldSettings createJ1HSFieldSettings() {
        return new J1HSFieldSettings();
    }

    /**
     * Create an instance of {@link J1HSAugStart }
     * 
     */
    public J1HSAugStart createJ1HSAugStart() {
        return new J1HSAugStart();
    }

    /**
     * Create an instance of {@link J1HSNotes }
     * 
     */
    public J1HSNotes createJ1HSNotes() {
        return new J1HSNotes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SeasonHspJ1HSDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/j1hs", name = "SeasonHspJ1HSDetails")
    public JAXBElement<SeasonHspJ1HSDetails> createSeasonHspJ1HSDetails(SeasonHspJ1HSDetails value) {
        return new JAXBElement<SeasonHspJ1HSDetails>(_SeasonHspJ1HSDetails_QNAME, SeasonHspJ1HSDetails.class, null, value);
    }

}
