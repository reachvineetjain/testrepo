//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.12 at 04:42:22 PM CST 
//


package com.ccighgo.service.transport.fieldstaff.beans.fieldstaffparticipant;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.fieldstaff.beans.fieldstaffparticipant package. 
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

    private final static QName _FieldStaffParticipants_QNAME = new QName("http://www.ccighgo.com/fslclist", "FieldStaffParticipants");
    private final static QName _FieldStaffParticipantApprovedDate_QNAME = new QName("", "approvedDate");
    private final static QName _FieldStaffParticipantFirstName_QNAME = new QName("", "firstName");
    private final static QName _FieldStaffParticipantLastName_QNAME = new QName("", "lastName");
    private final static QName _FieldStaffParticipantCountry_QNAME = new QName("", "country");
    private final static QName _FieldStaffParticipantRD_QNAME = new QName("", "RD");
    private final static QName _FieldStaffParticipantGoId_QNAME = new QName("", "goId");
    private final static QName _FieldStaffParticipantGender_QNAME = new QName("", "gender");
    private final static QName _FieldStaffParticipantLC_QNAME = new QName("", "LC");
    private final static QName _FieldStaffParticipantRoll_QNAME = new QName("", "roll");
    private final static QName _FieldStaffParticipantProgram_QNAME = new QName("", "program");
    private final static QName _FieldStaffParticipantHS_QNAME = new QName("", "HS");
    private final static QName _FieldStaffParticipantState_QNAME = new QName("", "state");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.fieldstaff.beans.fieldstaffparticipant
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FieldStaffParticipants }
     * 
     */
    public FieldStaffParticipants createFieldStaffParticipants() {
        return new FieldStaffParticipants();
    }

    /**
     * Create an instance of {@link FieldStaffParticipant }
     * 
     */
    public FieldStaffParticipant createFieldStaffParticipant() {
        return new FieldStaffParticipant();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FieldStaffParticipants }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/fslclist", name = "FieldStaffParticipants")
    public JAXBElement<FieldStaffParticipants> createFieldStaffParticipants(FieldStaffParticipants value) {
        return new JAXBElement<FieldStaffParticipants>(_FieldStaffParticipants_QNAME, FieldStaffParticipants.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "approvedDate", scope = FieldStaffParticipant.class)
    public JAXBElement<String> createFieldStaffParticipantApprovedDate(String value) {
        return new JAXBElement<String>(_FieldStaffParticipantApprovedDate_QNAME, String.class, FieldStaffParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "firstName", scope = FieldStaffParticipant.class)
    public JAXBElement<String> createFieldStaffParticipantFirstName(String value) {
        return new JAXBElement<String>(_FieldStaffParticipantFirstName_QNAME, String.class, FieldStaffParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "lastName", scope = FieldStaffParticipant.class)
    public JAXBElement<String> createFieldStaffParticipantLastName(String value) {
        return new JAXBElement<String>(_FieldStaffParticipantLastName_QNAME, String.class, FieldStaffParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "country", scope = FieldStaffParticipant.class)
    public JAXBElement<String> createFieldStaffParticipantCountry(String value) {
        return new JAXBElement<String>(_FieldStaffParticipantCountry_QNAME, String.class, FieldStaffParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "RD", scope = FieldStaffParticipant.class)
    public JAXBElement<String> createFieldStaffParticipantRD(String value) {
        return new JAXBElement<String>(_FieldStaffParticipantRD_QNAME, String.class, FieldStaffParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "goId", scope = FieldStaffParticipant.class)
    public JAXBElement<String> createFieldStaffParticipantGoId(String value) {
        return new JAXBElement<String>(_FieldStaffParticipantGoId_QNAME, String.class, FieldStaffParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "gender", scope = FieldStaffParticipant.class)
    public JAXBElement<String> createFieldStaffParticipantGender(String value) {
        return new JAXBElement<String>(_FieldStaffParticipantGender_QNAME, String.class, FieldStaffParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "LC", scope = FieldStaffParticipant.class)
    public JAXBElement<String> createFieldStaffParticipantLC(String value) {
        return new JAXBElement<String>(_FieldStaffParticipantLC_QNAME, String.class, FieldStaffParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "roll", scope = FieldStaffParticipant.class)
    public JAXBElement<String> createFieldStaffParticipantRoll(String value) {
        return new JAXBElement<String>(_FieldStaffParticipantRoll_QNAME, String.class, FieldStaffParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "program", scope = FieldStaffParticipant.class)
    public JAXBElement<String> createFieldStaffParticipantProgram(String value) {
        return new JAXBElement<String>(_FieldStaffParticipantProgram_QNAME, String.class, FieldStaffParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "HS", scope = FieldStaffParticipant.class)
    public JAXBElement<String> createFieldStaffParticipantHS(String value) {
        return new JAXBElement<String>(_FieldStaffParticipantHS_QNAME, String.class, FieldStaffParticipant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "state", scope = FieldStaffParticipant.class)
    public JAXBElement<String> createFieldStaffParticipantState(String value) {
        return new JAXBElement<String>(_FieldStaffParticipantState_QNAME, String.class, FieldStaffParticipant.class, value);
    }

}
