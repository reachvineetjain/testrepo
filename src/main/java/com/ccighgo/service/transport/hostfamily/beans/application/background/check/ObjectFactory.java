//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.07 at 12:04:22 PM CDT 
//


package com.ccighgo.service.transport.hostfamily.beans.application.background.check;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.hostfamily.beans.application.background.check package. 
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

    private final static QName _HFBackgroundCheck_QNAME = new QName("http://www.ccighgo.com/hfbackground", "HFBackgroundCheck");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.hostfamily.beans.application.background.check
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HFBackgroundCheck }
     * 
     */
    public HFBackgroundCheck createHFBackgroundCheck() {
        return new HFBackgroundCheck();
    }

    /**
     * Create an instance of {@link Member }
     * 
     */
    public Member createMember() {
        return new Member();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HFBackgroundCheck }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/hfbackground", name = "HFBackgroundCheck")
    public JAXBElement<HFBackgroundCheck> createHFBackgroundCheck(HFBackgroundCheck value) {
        return new JAXBElement<HFBackgroundCheck>(_HFBackgroundCheck_QNAME, HFBackgroundCheck.class, null, value);
    }

}
