//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.30 at 12:02:32 PM CST 
//


package com.ccighgo.service.transport.partner.beans.partnerdashboard;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;


/**
 * <p>Java class for PartnerDashboard complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerDashboard">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="partnerId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partnerCompany" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerCompanyLogo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="photoPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isSubpartner" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="partnerPrograms" type="{http://www.ccighgo.com/partnerdashboard}PartnerProgram" maxOccurs="unbounded"/>
 *         &lt;element name="userProgramsAndPermissions" type="{http://www.ccighgo.com/partnerdashboard}Programs" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerDashboard", propOrder = {
    "partnerId",
    "partnerCompany",
    "partnerCompanyLogo",
    "username",
    "firstName",
    "lastName",
    "photoPath",
    "isSubpartner",
    "canHaveSubpartners",
    "partnerPrograms",
    "userProgramsAndPermissions"
})
public class PartnerDashboard
    extends Response
{

    protected int partnerId;
    @XmlElement(required = true)
    protected String partnerCompany;
    @XmlElement(required = true)
    protected String partnerCompanyLogo;
    @XmlElement(required = true)
    protected String username;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String photoPath;
    protected boolean isSubpartner;
    protected boolean canHaveSubpartners;
    @XmlElement(required = true)
    protected List<PartnerProgram> partnerPrograms;
    @XmlElement(required = true)
    protected List<Programs> userProgramsAndPermissions;

    /**
     * Gets the value of the partnerId property.
     * 
     */
    public int getPartnerId() {
        return partnerId;
    }

    /**
     * Sets the value of the partnerId property.
     * 
     */
    public void setPartnerId(int value) {
        this.partnerId = value;
    }

    /**
     * Gets the value of the partnerCompany property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerCompany() {
        return partnerCompany;
    }

    /**
     * Sets the value of the partnerCompany property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerCompany(String value) {
        this.partnerCompany = value;
    }

    /**
     * Gets the value of the partnerCompanyLogo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerCompanyLogo() {
        return partnerCompanyLogo;
    }

    /**
     * Sets the value of the partnerCompanyLogo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerCompanyLogo(String value) {
        this.partnerCompanyLogo = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the photoPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhotoPath() {
        return photoPath;
    }

    /**
     * Sets the value of the photoPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhotoPath(String value) {
        this.photoPath = value;
    }

    /**
     * Gets the value of the isSubpartner property.
     * 
     */
    public boolean isIsSubpartner() {
        return isSubpartner;
    }

    /**
     * Sets the value of the isSubpartner property.
     * 
     */
    public void setIsSubpartner(boolean value) {
        this.isSubpartner = value;
    }
    
    /**
     * Gets the value of the canHaveSubpartners property.
     * 
     */
    public boolean canHaveSubpartners() {
        return canHaveSubpartners;
    }

    /**
     * Sets the value of the canHaveSubpartners property.
     * 
     */
    public void setCanHaveSubpartners(boolean value) {
        this.canHaveSubpartners = value;
    }

    /**
     * Gets the value of the partnerPrograms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerPrograms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerPrograms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerProgram }
     * 
     * 
     */
    public List<PartnerProgram> getPartnerPrograms() {
        if (partnerPrograms == null) {
            partnerPrograms = new ArrayList<PartnerProgram>();
        }
        return this.partnerPrograms;
    }

    /**
     * Gets the value of the userProgramsAndPermissions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userProgramsAndPermissions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserProgramsAndPermissions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Programs }
     * 
     * 
     */
    public List<Programs> getUserProgramsAndPermissions() {
        if (userProgramsAndPermissions == null) {
            userProgramsAndPermissions = new ArrayList<Programs>();
        }
        return this.userProgramsAndPermissions;
    }

}
