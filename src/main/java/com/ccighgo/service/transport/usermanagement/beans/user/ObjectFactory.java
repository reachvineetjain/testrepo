//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.14 at 10:58:39 PM IST 
//


package com.ccighgo.service.transport.usermanagement.beans.user;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.usermanagement.beans.user package. 
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

    private final static QName _User_QNAME = new QName("http://www.ccighgo.com/user", "User");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.usermanagement.beans.user
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link PermissionGroupOptions }
     * 
     */
    public PermissionGroupOptions createPermissionGroupOptions() {
        return new PermissionGroupOptions();
    }

    /**
     * Create an instance of {@link UserState }
     * 
     */
    public UserState createUserState() {
        return new UserState();
    }

    /**
     * Create an instance of {@link LoginInfo }
     * 
     */
    public LoginInfo createLoginInfo() {
        return new LoginInfo();
    }

    /**
     * Create an instance of {@link UserRole }
     * 
     */
    public UserRole createUserRole() {
        return new UserRole();
    }

    /**
     * Create an instance of {@link UserCountry }
     * 
     */
    public UserCountry createUserCountry() {
        return new UserCountry();
    }

    /**
     * Create an instance of {@link UserNotes }
     * 
     */
    public UserNotes createUserNotes() {
        return new UserNotes();
    }

    /**
     * Create an instance of {@link UserPermissions }
     * 
     */
    public UserPermissions createUserPermissions() {
        return new UserPermissions();
    }

    /**
     * Create an instance of {@link UserType }
     * 
     */
    public UserType createUserType() {
        return new UserType();
    }

    /**
     * Create an instance of {@link UserDepartmentProgramOptions }
     * 
     */
    public UserDepartmentProgramOptions createUserDepartmentProgramOptions() {
        return new UserDepartmentProgramOptions();
    }

    /**
     * Create an instance of {@link UserDepartmentProgram }
     * 
     */
    public UserDepartmentProgram createUserDepartmentProgram() {
        return new UserDepartmentProgram();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/user", name = "User")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

}
