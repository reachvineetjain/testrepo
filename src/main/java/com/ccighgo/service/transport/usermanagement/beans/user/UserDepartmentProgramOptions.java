//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.03 at 08:26:51 PM CDT 
//


package com.ccighgo.service.transport.usermanagement.beans.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserDepartmentProgramOptions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserDepartmentProgramOptions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="programOptionId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="programOptionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programOptionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserDepartmentProgramOptions", propOrder = {
    "programOptionId",
    "programOptionCode",
    "programOptionName"
})
public class UserDepartmentProgramOptions {

    protected int programOptionId;
    @XmlElement(required = true)
    protected String programOptionCode;
    @XmlElement(required = true)
    protected String programOptionName;

    /**
     * Gets the value of the programOptionId property.
     * 
     */
    public int getProgramOptionId() {
        return programOptionId;
    }

    /**
     * Sets the value of the programOptionId property.
     * 
     */
    public void setProgramOptionId(int value) {
        this.programOptionId = value;
    }

    /**
     * Gets the value of the programOptionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramOptionCode() {
        return programOptionCode;
    }

    /**
     * Sets the value of the programOptionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramOptionCode(String value) {
        this.programOptionCode = value;
    }

    /**
     * Gets the value of the programOptionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramOptionName() {
        return programOptionName;
    }

    /**
     * Sets the value of the programOptionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramOptionName(String value) {
        this.programOptionName = value;
    }

}
