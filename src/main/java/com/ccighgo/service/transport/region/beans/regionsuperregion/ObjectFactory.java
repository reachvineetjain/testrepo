//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.31 at 03:58:58 PM CDT 
//


package com.ccighgo.service.transport.region.beans.regionsuperregion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ccighgo.service.transport.region.beans.regionsuperregion package. 
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

    private final static QName _RegionSuperRegions_QNAME = new QName("http://www.ccighgo.com/regionsuperregion", "RegionSuperRegions");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ccighgo.service.transport.region.beans.regionsuperregion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegionSuperRegionsMap }
     * 
     */
    public RegionSuperRegionsMap createRegionSuperRegionsMap() {
        return new RegionSuperRegionsMap();
    }

    /**
     * Create an instance of {@link RegionSuperRegion }
     * 
     */
    public RegionSuperRegion createRegionSuperRegion() {
        return new RegionSuperRegion();
    }

    /**
     * Create an instance of {@link Region }
     * 
     */
    public Region createRegion() {
        return new Region();
    }

    /**
     * Create an instance of {@link SuperRegion }
     * 
     */
    public SuperRegion createSuperRegion() {
        return new SuperRegion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegionSuperRegionsMap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ccighgo.com/regionsuperregion", name = "RegionSuperRegions")
    public JAXBElement<RegionSuperRegionsMap> createRegionSuperRegions(RegionSuperRegionsMap value) {
        return new JAXBElement<RegionSuperRegionsMap>(_RegionSuperRegions_QNAME, RegionSuperRegionsMap.class, null, value);
    }

}
