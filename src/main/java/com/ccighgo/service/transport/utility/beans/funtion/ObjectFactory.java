//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.07 at 04:43:29 PM CDT 
//


package com.ccighgo.service.transport.utility.beans.funtion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.utility.beans.funtion package. 
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

    private final static QName _Functions_QNAME = new QName("http://www.ccighgo.com/function", "Functions");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.utility.beans.funtion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Functions }
     * 
     */
    public Functions createFunctions() {
        return new Functions();
    }

    /**
     * Create an instance of {@link Function }
     * 
     */
    public Function createFunction() {
        return new Function();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Functions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/function", name = "Functions")
    public JAXBElement<Functions> createFunctions(Functions value) {
        return new JAXBElement<Functions>(_Functions_QNAME, Functions.class, null, value);
    }

}
