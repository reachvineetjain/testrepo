//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.19 at 03:46:53 PM CST 
//


package com.ccighgo.service.transport.fieldstaff.beans.season.details;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.fieldstaff.beans.season.details package. 
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

    private final static QName _FieldStaffAdminSeasonDetails_QNAME = new QName("http://www.ccighgo.com/fsladminseason", "FieldStaffAdminSeasonDetails");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.fieldstaff.beans.season.details
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FieldStaffAdminSeasonDetails }
     * 
     */
    public FieldStaffAdminSeasonDetails createFieldStaffAdminSeasonDetails() {
        return new FieldStaffAdminSeasonDetails();
    }

    /**
     * Create an instance of {@link SeasonStatus }
     * 
     */
    public SeasonStatus createSeasonStatus() {
        return new SeasonStatus();
    }

    /**
     * Create an instance of {@link PaymentSchedule }
     * 
     */
    public PaymentSchedule createPaymentSchedule() {
        return new PaymentSchedule();
    }

    /**
     * Create an instance of {@link DefaultMonitoringStipend }
     * 
     */
    public DefaultMonitoringStipend createDefaultMonitoringStipend() {
        return new DefaultMonitoringStipend();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FieldStaffAdminSeasonDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/fsladminseason", name = "FieldStaffAdminSeasonDetails")
    public JAXBElement<FieldStaffAdminSeasonDetails> createFieldStaffAdminSeasonDetails(FieldStaffAdminSeasonDetails value) {
        return new JAXBElement<FieldStaffAdminSeasonDetails>(_FieldStaffAdminSeasonDetails_QNAME, FieldStaffAdminSeasonDetails.class, null, value);
    }

}