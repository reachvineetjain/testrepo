//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.29 at 04:08:18 PM CDT 
//


package com.ccighgo.service.transport.usermanagement.beans.cciuser;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.usermanagement.beans.cciuser package. 
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

    private final static QName _CCIUsers_QNAME = new QName("http://www.ccighgo.com/cciuser", "CCIUsers");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.usermanagement.beans.cciuser
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CCIUsers }
     * 
     */
    public CCIUsers createCCIUsers() {
        return new CCIUsers();
    }

    /**
     * Create an instance of {@link CCIUserDepartmentProgram }
     * 
     */
    public CCIUserDepartmentProgram createCCIUserDepartmentProgram() {
        return new CCIUserDepartmentProgram();
    }

    /**
     * Create an instance of {@link CCIUser }
     * 
     */
    public CCIUser createCCIUser() {
        return new CCIUser();
    }

    /**
     * Create an instance of {@link CCIUserDepartment }
     * 
     */
    public CCIUserDepartment createCCIUserDepartment() {
        return new CCIUserDepartment();
    }

    /**
     * Create an instance of {@link CCIUserStaffRole }
     * 
     */
    public CCIUserStaffRole createCCIUserStaffRole() {
        return new CCIUserStaffRole();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CCIUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/cciuser", name = "CCIUsers")
    public JAXBElement<CCIUsers> createCCIUsers(CCIUsers value) {
        return new JAXBElement<CCIUsers>(_CCIUsers_QNAME, CCIUsers.class, null, value);
    }

}
