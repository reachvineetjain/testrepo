//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.03 at 04:58:39 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.subpartner;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.subpartner package. 
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

    private final static QName _Subpartner_QNAME = new QName("http://www.ccighgo.com/subpartner", "Subpartner");
    private final static QName _SubPartnerNotes_QNAME = new QName("", "notes");
    private final static QName _SubPartnerReceiveNotificationEmailFromCCI_QNAME = new QName("", "receiveNotificationEmailFromCCI");
    private final static QName _SubPartnerMailingAddressZipCode_QNAME = new QName("", "mailingAddressZipCode");
    private final static QName _SubPartnerMailingAddressCountry_QNAME = new QName("", "mailingAddressCountry");
    private final static QName _SubPartnerLogoUserName_QNAME = new QName("", "logoUserName");
    private final static QName _SubPartnerPhysicalAddressCountry_QNAME = new QName("", "physicalAddressCountry");
    private final static QName _SubPartnerPrimaryContactTitle_QNAME = new QName("", "primaryContactTitle");
    private final static QName _SubPartnerWebSite_QNAME = new QName("", "webSite");
    private final static QName _SubPartnerMailingAddressStateOrProvince_QNAME = new QName("", "mailingAddressStateOrProvince");
    private final static QName _SubPartnerNeedsPartnerReview_QNAME = new QName("", "needsPartnerReview");
    private final static QName _SubPartnerMailingAddressCity_QNAME = new QName("", "mailingAddressCity");
    private final static QName _SubPartnerMailingAddress2_QNAME = new QName("", "mailingAddress2");
    private final static QName _SubPartnerMailingAddress1_QNAME = new QName("", "mailingAddress1");
    private final static QName _SubPartnerPhysicalAddressStateOrProvince_QNAME = new QName("", "physicalAddressStateOrProvince");
    private final static QName _SubPartnerPhysicalAddressZipCode_QNAME = new QName("", "physicalAddressZipCode");
    private final static QName _SubPartnerPrimaryContactFirstFax_QNAME = new QName("", "primaryContactFirstFax");
    private final static QName _SubPartnerTypeOfPartnerUser_QNAME = new QName("", "typeOfPartnerUser");
    private final static QName _SubPartnerPrimaryContactFirstName_QNAME = new QName("", "primaryContactFirstName");
    private final static QName _SubPartnerPrimaryContactLastName_QNAME = new QName("", "primaryContactLastName");
    private final static QName _SubPartnerNewPassword_QNAME = new QName("", "newPassword");
    private final static QName _SubPartnerPrimaryContactPhone_QNAME = new QName("", "primaryContactPhone");
    private final static QName _SubPartnerPhysicalAddressCity_QNAME = new QName("", "physicalAddressCity");
    private final static QName _SubPartnerLogoImageURL_QNAME = new QName("", "logoImageURL");
    private final static QName _SubPartnerAgencyName_QNAME = new QName("", "agencyName");
    private final static QName _SubPartnerPhysicalAddress2_QNAME = new QName("", "physicalAddress2");
    private final static QName _SubPartnerDeliverDS2019Forms_QNAME = new QName("", "deliverDS2019Forms");
    private final static QName _SubPartnerPhysicalAddress1_QNAME = new QName("", "physicalAddress1");
    private final static QName _SubPartnerSkypeId_QNAME = new QName("", "skypeId");
    private final static QName _SubPartnerSubPartnerStatus_QNAME = new QName("", "subPartnerStatus");
    private final static QName _SubPartnerReTypedPassword_QNAME = new QName("", "ReTypedPassword");
    private final static QName _SubPartnerPrimaryContactSalutation_QNAME = new QName("", "primaryContactSalutation");
    private final static QName _SubPartnerPrimaryContactEmergencyPhone_QNAME = new QName("", "primaryContactEmergencyPhone");
    private final static QName _SubPartnerPayGreenHeartDirectly_QNAME = new QName("", "payGreenHeartDirectly");
    private final static QName _SubPartnerUsername_QNAME = new QName("", "username");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.subpartner
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SubPartner }
     * 
     */
    public SubPartner createSubPartner() {
        return new SubPartner();
    }

    /**
     * Create an instance of {@link SubPartnersSeasonsNotesCreator }
     * 
     */
    public SubPartnersSeasonsNotesCreator createSubPartnersSeasonsNotesCreator() {
        return new SubPartnersSeasonsNotesCreator();
    }

    /**
     * Create an instance of {@link SubPartnersSeasonsNotes }
     * 
     */
    public SubPartnersSeasonsNotes createSubPartnersSeasonsNotes() {
        return new SubPartnersSeasonsNotes();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubPartner }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/subpartner", name = "Subpartner")
    public JAXBElement<SubPartner> createSubpartner(SubPartner value) {
        return new JAXBElement<SubPartner>(_Subpartner_QNAME, SubPartner.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubPartnersSeasonsNotes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "notes", scope = SubPartner.class)
    public JAXBElement<SubPartnersSeasonsNotes> createSubPartnerNotes(SubPartnersSeasonsNotes value) {
        return new JAXBElement<SubPartnersSeasonsNotes>(_SubPartnerNotes_QNAME, SubPartnersSeasonsNotes.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "receiveNotificationEmailFromCCI", scope = SubPartner.class)
    public JAXBElement<Boolean> createSubPartnerReceiveNotificationEmailFromCCI(Boolean value) {
        return new JAXBElement<Boolean>(_SubPartnerReceiveNotificationEmailFromCCI_QNAME, Boolean.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "mailingAddressZipCode", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerMailingAddressZipCode(String value) {
        return new JAXBElement<String>(_SubPartnerMailingAddressZipCode_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "mailingAddressCountry", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerMailingAddressCountry(String value) {
        return new JAXBElement<String>(_SubPartnerMailingAddressCountry_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "logoUserName", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerLogoUserName(String value) {
        return new JAXBElement<String>(_SubPartnerLogoUserName_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "physicalAddressCountry", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerPhysicalAddressCountry(String value) {
        return new JAXBElement<String>(_SubPartnerPhysicalAddressCountry_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "primaryContactTitle", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerPrimaryContactTitle(String value) {
        return new JAXBElement<String>(_SubPartnerPrimaryContactTitle_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "webSite", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerWebSite(String value) {
        return new JAXBElement<String>(_SubPartnerWebSite_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "mailingAddressStateOrProvince", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerMailingAddressStateOrProvince(String value) {
        return new JAXBElement<String>(_SubPartnerMailingAddressStateOrProvince_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "needsPartnerReview", scope = SubPartner.class)
    public JAXBElement<Boolean> createSubPartnerNeedsPartnerReview(Boolean value) {
        return new JAXBElement<Boolean>(_SubPartnerNeedsPartnerReview_QNAME, Boolean.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "mailingAddressCity", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerMailingAddressCity(String value) {
        return new JAXBElement<String>(_SubPartnerMailingAddressCity_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "mailingAddress2", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerMailingAddress2(String value) {
        return new JAXBElement<String>(_SubPartnerMailingAddress2_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "mailingAddress1", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerMailingAddress1(String value) {
        return new JAXBElement<String>(_SubPartnerMailingAddress1_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "physicalAddressStateOrProvince", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerPhysicalAddressStateOrProvince(String value) {
        return new JAXBElement<String>(_SubPartnerPhysicalAddressStateOrProvince_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "physicalAddressZipCode", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerPhysicalAddressZipCode(String value) {
        return new JAXBElement<String>(_SubPartnerPhysicalAddressZipCode_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "primaryContactFirstFax", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerPrimaryContactFirstFax(String value) {
        return new JAXBElement<String>(_SubPartnerPrimaryContactFirstFax_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "typeOfPartnerUser", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerTypeOfPartnerUser(String value) {
        return new JAXBElement<String>(_SubPartnerTypeOfPartnerUser_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "primaryContactFirstName", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerPrimaryContactFirstName(String value) {
        return new JAXBElement<String>(_SubPartnerPrimaryContactFirstName_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "primaryContactLastName", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerPrimaryContactLastName(String value) {
        return new JAXBElement<String>(_SubPartnerPrimaryContactLastName_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "newPassword", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerNewPassword(String value) {
        return new JAXBElement<String>(_SubPartnerNewPassword_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "primaryContactPhone", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerPrimaryContactPhone(String value) {
        return new JAXBElement<String>(_SubPartnerPrimaryContactPhone_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "physicalAddressCity", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerPhysicalAddressCity(String value) {
        return new JAXBElement<String>(_SubPartnerPhysicalAddressCity_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "logoImageURL", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerLogoImageURL(String value) {
        return new JAXBElement<String>(_SubPartnerLogoImageURL_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "agencyName", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerAgencyName(String value) {
        return new JAXBElement<String>(_SubPartnerAgencyName_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "physicalAddress2", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerPhysicalAddress2(String value) {
        return new JAXBElement<String>(_SubPartnerPhysicalAddress2_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "deliverDS2019Forms", scope = SubPartner.class)
    public JAXBElement<Boolean> createSubPartnerDeliverDS2019Forms(Boolean value) {
        return new JAXBElement<Boolean>(_SubPartnerDeliverDS2019Forms_QNAME, Boolean.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "physicalAddress1", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerPhysicalAddress1(String value) {
        return new JAXBElement<String>(_SubPartnerPhysicalAddress1_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "skypeId", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerSkypeId(String value) {
        return new JAXBElement<String>(_SubPartnerSkypeId_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "subPartnerStatus", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerSubPartnerStatus(String value) {
        return new JAXBElement<String>(_SubPartnerSubPartnerStatus_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ReTypedPassword", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerReTypedPassword(String value) {
        return new JAXBElement<String>(_SubPartnerReTypedPassword_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "primaryContactSalutation", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerPrimaryContactSalutation(String value) {
        return new JAXBElement<String>(_SubPartnerPrimaryContactSalutation_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "primaryContactEmergencyPhone", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerPrimaryContactEmergencyPhone(String value) {
        return new JAXBElement<String>(_SubPartnerPrimaryContactEmergencyPhone_QNAME, String.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "payGreenHeartDirectly", scope = SubPartner.class)
    public JAXBElement<Boolean> createSubPartnerPayGreenHeartDirectly(Boolean value) {
        return new JAXBElement<Boolean>(_SubPartnerPayGreenHeartDirectly_QNAME, Boolean.class, SubPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "username", scope = SubPartner.class)
    public JAXBElement<String> createSubPartnerUsername(String value) {
        return new JAXBElement<String>(_SubPartnerUsername_QNAME, String.class, SubPartner.class, value);
    }

}
