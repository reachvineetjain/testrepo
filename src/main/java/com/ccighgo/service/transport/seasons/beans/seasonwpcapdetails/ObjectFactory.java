//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.20 at 01:32:07 PM CST 
//


package com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails package. 
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

    private final static QName _SeasonWPCAPDetails_QNAME = new QName("http://www.ccighgo.com/seasonwpcapdetails", "SeasonWPCAPDetails");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.seasons.beans.seasonwpcapdetails
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SeasonWPCAPDetails }
     * 
     */
    public SeasonWPCAPDetails createSeasonWPCAPDetails() {
        return new SeasonWPCAPDetails();
    }

    /**
     * Create an instance of {@link SeasonWPCAPNotes }
     * 
     */
    public SeasonWPCAPNotes createSeasonWPCAPNotes() {
        return new SeasonWPCAPNotes();
    }

    /**
     * Create an instance of {@link WPCAPTraineeDetails }
     * 
     */
    public WPCAPTraineeDetails createWPCAPTraineeDetails() {
        return new WPCAPTraineeDetails();
    }

    /**
     * Create an instance of {@link SeasonWPCAPDocuments }
     * 
     */
    public SeasonWPCAPDocuments createSeasonWPCAPDocuments() {
        return new SeasonWPCAPDocuments();
    }

    /**
     * Create an instance of {@link WPCAPBasicDetails }
     * 
     */
    public WPCAPBasicDetails createWPCAPBasicDetails() {
        return new WPCAPBasicDetails();
    }

    /**
     * Create an instance of {@link WPCAPGeneralSettings }
     * 
     */
    public WPCAPGeneralSettings createWPCAPGeneralSettings() {
        return new WPCAPGeneralSettings();
    }

    /**
     * Create an instance of {@link WPCAPProgramAllocations }
     * 
     */
    public WPCAPProgramAllocations createWPCAPProgramAllocations() {
        return new WPCAPProgramAllocations();
    }

    /**
     * Create an instance of {@link WPCAPInternshipDetails }
     * 
     */
    public WPCAPInternshipDetails createWPCAPInternshipDetails() {
        return new WPCAPInternshipDetails();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SeasonWPCAPDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/seasonwpcapdetails", name = "SeasonWPCAPDetails")
    public JAXBElement<SeasonWPCAPDetails> createSeasonWPCAPDetails(SeasonWPCAPDetails value) {
        return new JAXBElement<SeasonWPCAPDetails>(_SeasonWPCAPDetails_QNAME, SeasonWPCAPDetails.class, null, value);
    }

}
