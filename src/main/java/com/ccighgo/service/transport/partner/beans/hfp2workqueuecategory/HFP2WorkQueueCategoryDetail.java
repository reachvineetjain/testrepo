//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.23 at 03:21:48 PM CDT 
//


package com.ccighgo.service.transport.partner.beans.hfp2workqueuecategory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HFP2WorkQueueCategoryDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HFP2WorkQueueCategoryDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoryId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="workQueueTypeId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="categoryAggregate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departmentProgramId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="categoryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HFP2WorkQueueCategoryDetail", propOrder = {
    "categoryId",
    "workQueueTypeId",
    "categoryAggregate",
    "departmentProgramId",
    "categoryName",
    "serviceUrl"
})
public class HFP2WorkQueueCategoryDetail {

    protected int categoryId;
    protected int workQueueTypeId;
    protected int categoryAggregate;
    protected int departmentProgramId;
    @XmlElement(required = true)
    protected String categoryName;
    @XmlElement(required = true)
    protected String serviceUrl;

    /**
     * Gets the value of the categoryId property.
     * 
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the value of the categoryId property.
     * 
     */
    public void setCategoryId(int value) {
        this.categoryId = value;
    }

    /**
     * Gets the value of the workQueueTypeId property.
     * 
     */
    public int getWorkQueueTypeId() {
        return workQueueTypeId;
    }

    /**
     * Sets the value of the workQueueTypeId property.
     * 
     */
    public void setWorkQueueTypeId(int value) {
        this.workQueueTypeId = value;
    }

    /**
     * Gets the value of the categoryAggregate property.
     * 
     */
    public int getCategoryAggregate() {
        return categoryAggregate;
    }

    /**
     * Sets the value of the categoryAggregate property.
     * 
     */
    public void setCategoryAggregate(int value) {
        this.categoryAggregate = value;
    }

    /**
     * Gets the value of the departmentProgramId property.
     * 
     */
    public int getDepartmentProgramId() {
        return departmentProgramId;
    }

    /**
     * Sets the value of the departmentProgramId property.
     * 
     */
    public void setDepartmentProgramId(int value) {
        this.departmentProgramId = value;
    }

    /**
     * Gets the value of the categoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the value of the categoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    /**
     * Gets the value of the serviceUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceUrl() {
        return serviceUrl;
    }

    /**
     * Sets the value of the serviceUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceUrl(String value) {
        this.serviceUrl = value;
    }

}