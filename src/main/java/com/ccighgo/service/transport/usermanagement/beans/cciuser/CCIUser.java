//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.14 at 11:09:43 PM IST 
//


package com.ccighgo.service.transport.usermanagement.beans.cciuser;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ccighgo.service.transport.common.response.beans.Response;
import com.ccighgo.service.transport.utility.beans.country.Country;
import com.ccighgo.service.transport.utility.beans.gender.Gender;


/**
 * <p>Java class for CCIUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CCIUser">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ccighgo.com/common}Response">
 *       &lt;sequence>
 *         &lt;element name="cciUserId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="country" type="{http://www.ccighgo.com/country}Country" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primaryPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loginName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isActive" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="photoPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userRole" type="{http://www.ccighgo.com/cciuser}CCIUserStaffRole" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cciUserDepartmentPrograms" type="{http://www.ccighgo.com/cciuser}CCIUserDepartmentProgram" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="gender" type="{http://www.ccighgo.com/gender}Gender" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CCIUser", propOrder = {
    "cciUserId",
    "firstName",
    "lastName",
    "country",
    "state",
    "email",
    "primaryPhone",
    "phoneExtension",
    "loginName",
    "isActive",
    "photoPath",
    "userRole",
    "cciUserDepartmentPrograms",
    "gender"
})
public class CCIUser
    extends Response
{

    protected int cciUserId;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    protected Country country;
    protected String state;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String primaryPhone;
    protected String phoneExtension;
    @XmlElement(required = true)
    protected String loginName;
    protected Boolean isActive;
    protected String photoPath;
    protected List<CCIUserStaffRole> userRole;
    protected List<CCIUserDepartmentProgram> cciUserDepartmentPrograms;
    protected Gender gender;

    /**
     * Gets the value of the cciUserId property.
     * 
     */
    public int getCciUserId() {
        return cciUserId;
    }

    /**
     * Sets the value of the cciUserId property.
     * 
     */
    public void setCciUserId(int value) {
        this.cciUserId = value;
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
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link Country }
     *     
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link Country }
     *     
     */
    public void setCountry(Country value) {
        this.country = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the primaryPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryPhone() {
        return primaryPhone;
    }

    /**
     * Sets the value of the primaryPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryPhone(String value) {
        this.primaryPhone = value;
    }

    public String getPhoneExtension() {
      return phoneExtension;
   }

   public void setPhoneExtension(String phoneExtension) {
      this.phoneExtension = phoneExtension;
   }

   /**
     * Gets the value of the loginName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Sets the value of the loginName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginName(String value) {
        this.loginName = value;
    }

    /**
     * Gets the value of the isActive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsActive(Boolean value) {
        this.isActive = value;
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
     * Gets the value of the userRole property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userRole property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CCIUserStaffRole }
     * 
     * 
     */
    public List<CCIUserStaffRole> getUserRole() {
        if (userRole == null) {
            userRole = new ArrayList<CCIUserStaffRole>();
        }
        return this.userRole;
    }

    /**
     * Gets the value of the cciUserDepartmentPrograms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cciUserDepartmentPrograms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCciUserDepartmentPrograms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CCIUserDepartmentProgram }
     * 
     * 
     */
    public List<CCIUserDepartmentProgram> getCciUserDepartmentPrograms() {
        if (cciUserDepartmentPrograms == null) {
            cciUserDepartmentPrograms = new ArrayList<CCIUserDepartmentProgram>();
        }
        return this.cciUserDepartmentPrograms;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link Gender }
     *     
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link Gender }
     *     
     */
    public void setGender(Gender value) {
        this.gender = value;
    }

}
