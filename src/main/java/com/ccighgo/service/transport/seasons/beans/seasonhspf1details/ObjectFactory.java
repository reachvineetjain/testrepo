//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.23 at 12:03:15 PM CDT 
//


package com.ccighgo.service.transport.seasons.beans.seasonhspf1details;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.seasons.beans.seasonhspf1details package. 
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

    private final static QName _SeasonHSPF1Details_QNAME = new QName("http://www.ccighgo.com/seasonhspf1details", "SeasonHSPF1Details");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.seasons.beans.seasonhspf1details
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SeasonHSPF1Details }
     * 
     */
    public SeasonHSPF1Details createSeasonHSPF1Details() {
        return new SeasonHSPF1Details();
    }

    /**
     * Create an instance of {@link HSPF1BasicDetails }
     * 
     */
    public HSPF1BasicDetails createHSPF1BasicDetails() {
        return new HSPF1BasicDetails();
    }

    /**
     * Create an instance of {@link HSPF1SeasonHspF1Documents }
     * 
     */
    public HSPF1SeasonHspF1Documents createHSPF1SeasonHspF1Documents() {
        return new HSPF1SeasonHspF1Documents();
    }

    /**
     * Create an instance of {@link HSPF1JanuaryStart2NdSemesterDetails }
     * 
     */
    public HSPF1JanuaryStart2NdSemesterDetails createHSPF1JanuaryStart2NdSemesterDetails() {
        return new HSPF1JanuaryStart2NdSemesterDetails();
    }

    /**
     * Create an instance of {@link HSPF1ProgramAllocations }
     * 
     */
    public HSPF1ProgramAllocations createHSPF1ProgramAllocations() {
        return new HSPF1ProgramAllocations();
    }

    /**
     * Create an instance of {@link HSPF1ProgramAllocationDetails }
     * 
     */
    public HSPF1ProgramAllocationDetails createHSPF1ProgramAllocationDetails() {
        return new HSPF1ProgramAllocationDetails();
    }

    /**
     * Create an instance of {@link HSPF1AugustStartFullYearDetails }
     * 
     */
    public HSPF1AugustStartFullYearDetails createHSPF1AugustStartFullYearDetails() {
        return new HSPF1AugustStartFullYearDetails();
    }

    /**
     * Create an instance of {@link HSPF1JanuaryStartFullYearDetail }
     * 
     */
    public HSPF1JanuaryStartFullYearDetail createHSPF1JanuaryStartFullYearDetail() {
        return new HSPF1JanuaryStartFullYearDetail();
    }

    /**
     * Create an instance of {@link HSPF1AugustStart1StSemesterDetails }
     * 
     */
    public HSPF1AugustStart1StSemesterDetails createHSPF1AugustStart1StSemesterDetails() {
        return new HSPF1AugustStart1StSemesterDetails();
    }

    /**
     * Create an instance of {@link HSPF1FieldSettings }
     * 
     */
    public HSPF1FieldSettings createHSPF1FieldSettings() {
        return new HSPF1FieldSettings();
    }

    /**
     * Create an instance of {@link HSPF1Accounting }
     * 
     */
    public HSPF1Accounting createHSPF1Accounting() {
        return new HSPF1Accounting();
    }

    /**
     * Create an instance of {@link HSPF1SeasonHspF1Notes }
     * 
     */
    public HSPF1SeasonHspF1Notes createHSPF1SeasonHspF1Notes() {
        return new HSPF1SeasonHspF1Notes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SeasonHSPF1Details }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/seasonhspf1details", name = "SeasonHSPF1Details")
    public JAXBElement<SeasonHSPF1Details> createSeasonHSPF1Details(SeasonHSPF1Details value) {
        return new JAXBElement<SeasonHSPF1Details>(_SeasonHSPF1Details_QNAME, SeasonHSPF1Details.class, null, value);
    }

}
