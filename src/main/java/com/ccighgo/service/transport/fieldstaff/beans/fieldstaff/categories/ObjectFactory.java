//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.29 at 06:59:01 PM IST 
//


package com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.categories;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.categories package. 
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

    private final static QName _FieldStaffCategoriesList_QNAME = new QName("http://www.ccighgo.com/fslcate", "FieldStaffCategoriesList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.fieldstaff.beans.fieldstaff.categories
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FieldStaffCategoriesList }
     * 
     */
    public FieldStaffCategoriesList createFieldStaffCategoriesList() {
        return new FieldStaffCategoriesList();
    }

    /**
     * Create an instance of {@link FieldStaffCategories }
     * 
     */
    public FieldStaffCategories createFieldStaffCategories() {
        return new FieldStaffCategories();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FieldStaffCategoriesList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/fslcate", name = "FieldStaffCategoriesList")
    public JAXBElement<FieldStaffCategoriesList> createFieldStaffCategoriesList(FieldStaffCategoriesList value) {
        return new JAXBElement<FieldStaffCategoriesList>(_FieldStaffCategoriesList_QNAME, FieldStaffCategoriesList.class, null, value);
    }

}
