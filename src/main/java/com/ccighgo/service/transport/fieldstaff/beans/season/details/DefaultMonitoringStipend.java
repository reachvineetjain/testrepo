//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.19 at 03:46:53 PM CST 
//


package com.ccighgo.service.transport.fieldstaff.beans.season.details;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DefaultMonitoringStipend complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DefaultMonitoringStipend">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="defaultMonitoringStipendId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="defaultMonitoringStipend" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DefaultMonitoringStipend", propOrder = {
    "defaultMonitoringStipendId",
    "defaultMonitoringStipend"
})
public class DefaultMonitoringStipend {

    protected int defaultMonitoringStipendId;
    @XmlElement(required = true)
    protected String defaultMonitoringStipend;

    /**
     * Gets the value of the defaultMonitoringStipendId property.
     * 
     */
    public int getDefaultMonitoringStipendId() {
        return defaultMonitoringStipendId;
    }

    /**
     * Sets the value of the defaultMonitoringStipendId property.
     * 
     */
    public void setDefaultMonitoringStipendId(int value) {
        this.defaultMonitoringStipendId = value;
    }

    /**
     * Gets the value of the defaultMonitoringStipend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultMonitoringStipend() {
        return defaultMonitoringStipend;
    }

    /**
     * Sets the value of the defaultMonitoringStipend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultMonitoringStipend(String value) {
        this.defaultMonitoringStipend = value;
    }

}
