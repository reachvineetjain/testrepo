//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.11 at 03:39:47 PM CST 
//


package com.ccighgo.service.transport.updatelog.beans.genericupdatelog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GenericUpdateLog complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GenericUpdateLog">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="goId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="updateLogObject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modifiedOn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loginId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenericUpdateLog", propOrder = {
    "goId",
    "updateLogObject",
    "modifiedBy",
    "modifiedOn",
    "loginId"
})
public class GenericUpdateLog {

    protected int goId;
    protected String updateLogObject;
    protected String modifiedBy;
    protected String modifiedOn;
    protected Integer loginId;

    /**
     * Gets the value of the goId property.
     * 
     */
    public int getGoId() {
        return goId;
    }

    /**
     * Sets the value of the goId property.
     * 
     */
    public void setGoId(int value) {
        this.goId = value;
    }

    /**
     * Gets the value of the updateLogObject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateLogObject() {
        return updateLogObject;
    }

    /**
     * Sets the value of the updateLogObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateLogObject(String value) {
        this.updateLogObject = value;
    }

    /**
     * Gets the value of the modifiedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets the value of the modifiedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifiedBy(String value) {
        this.modifiedBy = value;
    }

    /**
     * Gets the value of the modifiedOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModifiedOn() {
        return modifiedOn;
    }

    /**
     * Sets the value of the modifiedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModifiedOn(String value) {
        this.modifiedOn = value;
    }

    /**
     * Gets the value of the loginId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLoginId() {
        return loginId;
    }

    /**
     * Sets the value of the loginId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLoginId(Integer value) {
        this.loginId = value;
    }

}