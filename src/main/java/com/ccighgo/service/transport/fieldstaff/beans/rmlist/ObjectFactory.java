//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.23 at 03:09:55 PM CST 
//


package com.ccighgo.service.transport.fieldstaff.beans.rmlist;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.fieldstaff.beans.rmlist package. 
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

    private final static QName _FieldStaffRMList_QNAME = new QName("http://www.ccighgo.com/fsrmlist", "FieldStaffRMList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.fieldstaff.beans.rmlist
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FieldStaffRMList }
     * 
     */
    public FieldStaffRMList createFieldStaffRMList() {
        return new FieldStaffRMList();
    }

    /**
     * Create an instance of {@link RMSeasonContact }
     * 
     */
    public RMSeasonContact createRMSeasonContact() {
        return new RMSeasonContact();
    }

    /**
     * Create an instance of {@link FieldStaffRM }
     * 
     */
    public FieldStaffRM createFieldStaffRM() {
        return new FieldStaffRM();
    }

    /**
     * Create an instance of {@link Contact }
     * 
     */
    public Contact createContact() {
        return new Contact();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FieldStaffRMList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/fsrmlist", name = "FieldStaffRMList")
    public JAXBElement<FieldStaffRMList> createFieldStaffRMList(FieldStaffRMList value) {
        return new JAXBElement<FieldStaffRMList>(_FieldStaffRMList_QNAME, FieldStaffRMList.class, null, value);
    }

}
