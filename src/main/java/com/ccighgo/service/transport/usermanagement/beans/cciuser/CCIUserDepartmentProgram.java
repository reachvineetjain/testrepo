//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.27 at 05:47:41 PM CDT 
//


package com.ccighgo.service.transport.usermanagement.beans.cciuser;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CCIUserDepartmentProgram complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CCIUserDepartmentProgram">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="departmentId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="departmentName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="departmentAcronym" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="programId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="programName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cciUserDepartmentProgramOptions" type="{http://www.ccighgo.com/cciuser}CCIUserDepartmentProgramOptions" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CCIUserDepartmentProgram", propOrder = {
    "departmentId",
    "departmentName",
    "departmentAcronym",
    "programId",
    "programName",
    "cciUserDepartmentProgramOptions"
})
public class CCIUserDepartmentProgram {

    protected Integer departmentId;
    protected String departmentName;
    protected String departmentAcronym;
    protected Integer programId;
    protected String programName;
    protected List<CCIUserDepartmentProgramOptions> cciUserDepartmentProgramOptions;

    /**
     * Gets the value of the departmentId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Sets the value of the departmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDepartmentId(Integer value) {
        this.departmentId = value;
    }

    /**
     * Gets the value of the departmentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Sets the value of the departmentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentName(String value) {
        this.departmentName = value;
    }

    /**
     * Gets the value of the departmentAcronym property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartmentAcronym() {
        return departmentAcronym;
    }

    /**
     * Sets the value of the departmentAcronym property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartmentAcronym(String value) {
        this.departmentAcronym = value;
    }

    /**
     * Gets the value of the programId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProgramId() {
        return programId;
    }

    /**
     * Sets the value of the programId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProgramId(Integer value) {
        this.programId = value;
    }

    /**
     * Gets the value of the programName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * Sets the value of the programName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramName(String value) {
        this.programName = value;
    }

    /**
     * Gets the value of the cciUserDepartmentProgramOptions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cciUserDepartmentProgramOptions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCciUserDepartmentProgramOptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CCIUserDepartmentProgramOptions }
     * 
     * 
     */
    public List<CCIUserDepartmentProgramOptions> getCciUserDepartmentProgramOptions() {
        if (cciUserDepartmentProgramOptions == null) {
            cciUserDepartmentProgramOptions = new ArrayList<CCIUserDepartmentProgramOptions>();
        }
        return this.cciUserDepartmentProgramOptions;
    }

}
