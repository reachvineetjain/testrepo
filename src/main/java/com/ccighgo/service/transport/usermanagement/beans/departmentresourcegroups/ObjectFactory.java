//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.04 at 01:45:15 PM IST 
//


package com.ccighgo.service.transport.usermanagement.beans.departmentresourcegroups;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.usermanagement.beans.departmentresourcegroups package. 
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

    private final static QName _DepartmentResourceGroups_QNAME = new QName("http://www.ccighgo.com/departmentresourcegroup", "DepartmentResourceGroups");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.usermanagement.beans.departmentresourcegroups
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DepartmentResourceGroups }
     * 
     */
    public DepartmentResourceGroups createDepartmentResourceGroups() {
        return new DepartmentResourceGroups();
    }

    /**
     * Create an instance of {@link DepartmentResourceGroupTO }
     * 
     */
    public DepartmentResourceGroupTO createDepartmentResourceGroupTO() {
        return new DepartmentResourceGroupTO();
    }

    /**
     * Create an instance of {@link ResourcePermissionTO }
     * 
     */
    public ResourcePermissionTO createResourcePermissionTO() {
        return new ResourcePermissionTO();
    }

    /**
     * Create an instance of {@link ResourcePermissions }
     * 
     */
    public ResourcePermissions createResourcePermissions() {
        return new ResourcePermissions();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepartmentResourceGroups }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/departmentresourcegroup", name = "DepartmentResourceGroups")
    public JAXBElement<DepartmentResourceGroups> createDepartmentResourceGroups(DepartmentResourceGroups value) {
        return new JAXBElement<DepartmentResourceGroups>(_DepartmentResourceGroups_QNAME, DepartmentResourceGroups.class, null, value);
    }

}
