//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.11 at 02:06:07 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerf1details;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerProgramStats complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerProgramStats">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="submitted" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerReview" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="greenheartReview" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="approved" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="notApproved" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerProgramStats", propOrder = {
    "submitted",
    "partnerReview",
    "greenheartReview",
    "approved",
    "notApproved"
})
public class PartnerProgramStats {

    protected int submitted;
    protected int partnerReview;
    protected int greenheartReview;
    protected int approved;
    protected int notApproved;

    /**
     * Gets the value of the submitted property.
     * 
     */
    public int getSubmitted() {
        return submitted;
    }

    /**
     * Sets the value of the submitted property.
     * 
     */
    public void setSubmitted(int value) {
        this.submitted = value;
    }

    /**
     * Gets the value of the partnerReview property.
     * 
     */
    public int getPartnerReview() {
        return partnerReview;
    }

    /**
     * Sets the value of the partnerReview property.
     * 
     */
    public void setPartnerReview(int value) {
        this.partnerReview = value;
    }

    /**
     * Gets the value of the greenheartReview property.
     * 
     */
    public int getGreenheartReview() {
        return greenheartReview;
    }

    /**
     * Sets the value of the greenheartReview property.
     * 
     */
    public void setGreenheartReview(int value) {
        this.greenheartReview = value;
    }

    /**
     * Gets the value of the approved property.
     * 
     */
    public int getApproved() {
        return approved;
    }

    /**
     * Sets the value of the approved property.
     * 
     */
    public void setApproved(int value) {
        this.approved = value;
    }

    /**
     * Gets the value of the notApproved property.
     * 
     */
    public int getNotApproved() {
        return notApproved;
    }

    /**
     * Sets the value of the notApproved property.
     * 
     */
    public void setNotApproved(int value) {
        this.notApproved = value;
    }

}
