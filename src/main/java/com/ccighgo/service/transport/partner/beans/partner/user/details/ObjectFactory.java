//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.02 at 11:05:44 AM CST 
//


package com.ccighgo.service.transport.partner.beans.partner.user.details;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.partner.user.details package. 
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

    private final static QName _PartnerUserDetails_QNAME = new QName("http://www.ccighgo.com/partneruserdetails", "PartnerUserDetails");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.partner.user.details
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PartnerUserDetails }
     * 
     */
    public PartnerUserDetails createPartnerUserDetails() {
        return new PartnerUserDetails();
    }

    /**
     * Create an instance of {@link Programs }
     * 
     */
    public Programs createPrograms() {
        return new Programs();
    }

    /**
     * Create an instance of {@link UserSalutation }
     * 
     */
    public UserSalutation createUserSalutation() {
        return new UserSalutation();
    }

    /**
     * Create an instance of {@link UserAddressCountry }
     * 
     */
    public UserAddressCountry createUserAddressCountry() {
        return new UserAddressCountry();
    }

    /**
     * Create an instance of {@link Permissions }
     * 
     */
    public Permissions createPermissions() {
        return new Permissions();
    }

    /**
     * Create an instance of {@link UserOffice }
     * 
     */
    public UserOffice createUserOffice() {
        return new UserOffice();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerUserDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/partneruserdetails", name = "PartnerUserDetails")
    public JAXBElement<PartnerUserDetails> createPartnerUserDetails(PartnerUserDetails value) {
        return new JAXBElement<PartnerUserDetails>(_PartnerUserDetails_QNAME, PartnerUserDetails.class, null, value);
    }

}
