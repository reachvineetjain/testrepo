//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.08 at 03:32:36 PM IST 
//


package com.ccighgo.service.transport.region.beans.stateregion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.region.beans.stateregion package. 
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

    private final static QName _StateRegions_QNAME = new QName("http://www.ccighgo.com/stateregion", "StateRegions");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.region.beans.stateregion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StateRegions }
     * 
     */
    public StateRegions createStateRegions() {
        return new StateRegions();
    }

    /**
     * Create an instance of {@link StateRegion }
     * 
     */
    public StateRegion createStateRegion() {
        return new StateRegion();
    }

    /**
     * Create an instance of {@link State }
     * 
     */
    public State createState() {
        return new State();
    }

    /**
     * Create an instance of {@link Region }
     * 
     */
    public Region createRegion() {
        return new Region();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StateRegions }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/stateregion", name = "StateRegions")
    public JAXBElement<StateRegions> createStateRegions(StateRegions value) {
        return new JAXBElement<StateRegions>(_StateRegions_QNAME, StateRegions.class, null, value);
    }

}
