//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.16 at 08:01:44 PM IST 
//


package com.ccighgo.service.transport.partner.beans.subpartnerdetail;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.subpartnerdetail package. 
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

    private final static QName _SubpartnerDetails_QNAME = new QName("http://www.ccighgo.com/subpartnerdetail", "SubpartnerDetails");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.subpartnerdetail
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SubPartnerDetail }
     * 
     */
    public SubPartnerDetail createSubPartnerDetail() {
        return new SubPartnerDetail();
    }

    /**
     * Create an instance of {@link Details }
     * 
     */
    public Details createDetails() {
        return new Details();
    }

    /**
     * Create an instance of {@link SubPartnersPrimaryContact }
     * 
     */
    public SubPartnersPrimaryContact createSubPartnersPrimaryContact() {
        return new SubPartnersPrimaryContact();
    }

    /**
     * Create an instance of {@link SubPartnersPhysicalAddress }
     * 
     */
    public SubPartnersPhysicalAddress createSubPartnersPhysicalAddress() {
        return new SubPartnersPhysicalAddress();
    }

    /**
     * Create an instance of {@link Country }
     * 
     */
    public Country createCountry() {
        return new Country();
    }

    /**
     * Create an instance of {@link SubPartnersMailingAddress }
     * 
     */
    public SubPartnersMailingAddress createSubPartnersMailingAddress() {
        return new SubPartnersMailingAddress();
    }

    /**
     * Create an instance of {@link Salutation }
     * 
     */
    public Salutation createSalutation() {
        return new Salutation();
    }

    /**
     * Create an instance of {@link OfficeAddressCountry }
     * 
     */
    public OfficeAddressCountry createOfficeAddressCountry() {
        return new OfficeAddressCountry();
    }

    /**
     * Create an instance of {@link SubPartnerOffice }
     * 
     */
    public SubPartnerOffice createSubPartnerOffice() {
        return new SubPartnerOffice();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubPartnerDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/subpartnerdetail", name = "SubpartnerDetails")
    public JAXBElement<SubPartnerDetail> createSubpartnerDetails(SubPartnerDetail value) {
        return new JAXBElement<SubPartnerDetail>(_SubpartnerDetails_QNAME, SubPartnerDetail.class, null, value);
    }

}
