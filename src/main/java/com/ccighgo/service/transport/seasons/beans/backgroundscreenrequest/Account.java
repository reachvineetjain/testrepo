//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.16 at 04:08:39 PM CDT 
//


package com.ccighgo.service.transport.seasons.beans.backgroundscreenrequest;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Account complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Account">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AcctNbr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Applicant" type="{http://www.ccighgo.com/backgroundscreenrequest}Applicant" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Account", propOrder = {
    "acctNbr",
    "applicant"
})
public class Account {

    @XmlElement(name = "AcctNbr", required = true)
    protected String acctNbr;
    @XmlElement(name = "Applicant", required = true)
    protected List<Applicant> applicant;

    /**
     * Gets the value of the acctNbr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctNbr() {
        return acctNbr;
    }

    /**
     * Sets the value of the acctNbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctNbr(String value) {
        this.acctNbr = value;
    }

    /**
     * Gets the value of the applicant property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the applicant property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApplicant().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Applicant }
     * 
     * 
     */
    public List<Applicant> getApplicant() {
        if (applicant == null) {
            applicant = new ArrayList<Applicant>();
        }
        return this.applicant;
    }

}
