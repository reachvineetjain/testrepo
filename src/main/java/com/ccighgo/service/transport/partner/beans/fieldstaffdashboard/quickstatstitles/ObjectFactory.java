//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.17 at 02:48:45 PM CST 
//


package com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.quickstatstitles;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.quickstatstitles package. 
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

    private final static QName _FieldStaffDashboardQuickStatsTitles_QNAME = new QName("http://www.ccighgo.com/fieldstaffdashboard", "FieldStaffDashboardQuickStatsTitles");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.partner.beans.fieldstaffdashboard.quickstatstitles
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FieldStaffDashboardQuickStatsTitles }
     * 
     */
    public FieldStaffDashboardQuickStatsTitles createFieldStaffDashboardQuickStatsTitles() {
        return new FieldStaffDashboardQuickStatsTitles();
    }

    /**
     * Create an instance of {@link FieldStaffDashboardQuickStatsTitlesDetails }
     * 
     */
    public FieldStaffDashboardQuickStatsTitlesDetails createFieldStaffDashboardQuickStatsTitlesDetails() {
        return new FieldStaffDashboardQuickStatsTitlesDetails();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FieldStaffDashboardQuickStatsTitles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/fieldstaffdashboard", name = "FieldStaffDashboardQuickStatsTitles")
    public JAXBElement<FieldStaffDashboardQuickStatsTitles> createFieldStaffDashboardQuickStatsTitles(FieldStaffDashboardQuickStatsTitles value) {
        return new JAXBElement<FieldStaffDashboardQuickStatsTitles>(_FieldStaffDashboardQuickStatsTitles_QNAME, FieldStaffDashboardQuickStatsTitles.class, null, value);
    }

}
