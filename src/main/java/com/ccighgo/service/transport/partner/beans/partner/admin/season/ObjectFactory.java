//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.22 at 03:00:14 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partner.admin.season;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.partner.admin.season package. 
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

    private final static QName _PartnerAdminSeasonList_QNAME = new QName("http://www.ccighgo.com/partneradminseason", "PartnerAdminSeasonList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.partner.admin.season
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PartnerAdminSeasonList }
     * 
     */
    public PartnerAdminSeasonList createPartnerAdminSeasonList() {
        return new PartnerAdminSeasonList();
    }

    /**
     * Create an instance of {@link PartnerAdminSeason }
     * 
     */
    public PartnerAdminSeason createPartnerAdminSeason() {
        return new PartnerAdminSeason();
    }

    /**
     * Create an instance of {@link SeasonStatus }
     * 
     */
    public SeasonStatus createSeasonStatus() {
        return new SeasonStatus();
    }

    /**
     * Create an instance of {@link PartnerSeasonStatus }
     * 
     */
    public PartnerSeasonStatus createPartnerSeasonStatus() {
        return new PartnerSeasonStatus();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerAdminSeasonList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/partneradminseason", name = "PartnerAdminSeasonList")
    public JAXBElement<PartnerAdminSeasonList> createPartnerAdminSeasonList(PartnerAdminSeasonList value) {
        return new JAXBElement<PartnerAdminSeasonList>(_PartnerAdminSeasonList_QNAME, PartnerAdminSeasonList.class, null, value);
    }

}
