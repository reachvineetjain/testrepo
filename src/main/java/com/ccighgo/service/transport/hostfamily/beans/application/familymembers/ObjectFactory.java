//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.09 at 04:45:03 PM IST 
//


package com.ccighgo.service.transport.hostfamily.beans.application.familymembers;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.hostfamily.beans.application.familymembers package. 
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

    private final static QName _HostFamilyMembers_QNAME = new QName("http://www.ccighgo.com/hostfamilymembers", "HostFamilyMembers");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.hostfamily.beans.application.familymembers
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HostFamilyMembers }
     * 
     */
    public HostFamilyMembers createHostFamilyMembers() {
        return new HostFamilyMembers();
    }

    /**
     * Create an instance of {@link HostFamilyMemberDetails }
     * 
     */
    public HostFamilyMemberDetails createHostFamilyMemberDetails() {
        return new HostFamilyMemberDetails();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HostFamilyMembers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/hostfamilymembers", name = "HostFamilyMembers")
    public JAXBElement<HostFamilyMembers> createHostFamilyMembers(HostFamilyMembers value) {
        return new JAXBElement<HostFamilyMembers>(_HostFamilyMembers_QNAME, HostFamilyMembers.class, null, value);
    }

}
