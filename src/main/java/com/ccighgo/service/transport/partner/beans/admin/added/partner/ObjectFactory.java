//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.18 at 01:11:00 PM CST 
//


package com.ccighgo.service.transport.partner.beans.admin.added.partner;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.admin.added.partner package. 
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

    private final static QName _AddedPartners_QNAME = new QName("http://www.ccighgo.com/addedpartner", "AddedPartners");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.admin.added.partner
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddedPartners }
     * 
     */
    public AddedPartners createAddedPartners() {
        return new AddedPartners();
    }

    /**
     * Create an instance of {@link AddedPartner }
     * 
     */
    public AddedPartner createAddedPartner() {
        return new AddedPartner();
    }

    /**
     * Create an instance of {@link PartnerSeasons }
     * 
     */
    public PartnerSeasons createPartnerSeasons() {
        return new PartnerSeasons();
    }

    /**
     * Create an instance of {@link PartnerCountry }
     * 
     */
    public PartnerCountry createPartnerCountry() {
        return new PartnerCountry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddedPartners }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/addedpartner", name = "AddedPartners")
    public JAXBElement<AddedPartners> createAddedPartners(AddedPartners value) {
        return new JAXBElement<AddedPartners>(_AddedPartners_QNAME, AddedPartners.class, null, value);
    }

}
