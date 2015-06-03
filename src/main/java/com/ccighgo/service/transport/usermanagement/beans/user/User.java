//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.03 at 04:55:24 PM CDT 
//


package com.ccighgo.service.transport.usermanagement.beans.user;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for User complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="User">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cciUserId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressLine1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressLine2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="primaryPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emergencyPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sevisId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supervisorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="photoPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="userCountry" type="{http://www.ccighgo.com/user}UserCountry" minOccurs="0"/>
 *         &lt;element name="userState" type="{http://www.ccighgo.com/user}UserState" minOccurs="0"/>
 *         &lt;element name="loginInfo" type="{http://www.ccighgo.com/user}LoginInfo"/>
 *         &lt;element name="departments" type="{http://www.ccighgo.com/user}UserDepartment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="roles" type="{http://www.ccighgo.com/user}UserRole" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="permissions" type="{http://www.ccighgo.com/user}UserPermissions" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userNotes" type="{http://www.ccighgo.com/user}UserNotes" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", propOrder = {
    "cciUserId",
    "firstName",
    "lastName",
    "city",
    "addressLine1",
    "addressLine2",
    "zip",
    "email",
    "primaryPhone",
    "emergencyPhone",
    "sevisId",
    "supervisorId",
    "photoPath",
    "active",
    "userCountry",
    "userState",
    "loginInfo",
    "departments",
    "roles",
    "permissions",
    "userNotes"
})
public class User {

    protected int cciUserId;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    protected String city;
    protected String addressLine1;
    protected String addressLine2;
    protected String zip;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String primaryPhone;
    protected String emergencyPhone;
    protected String sevisId;
    protected String supervisorId;
    protected String photoPath;
    protected boolean active;
    protected UserCountry userCountry;
    protected UserState userState;
    @XmlElement(required = true)
    protected LoginInfo loginInfo;
    protected List<UserDepartment> departments;
    protected List<UserRole> roles;
    protected List<UserPermissions> permissions;
    protected List<UserNotes> userNotes;

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
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the addressLine1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the value of the addressLine1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine1(String value) {
        this.addressLine1 = value;
    }

    /**
     * Gets the value of the addressLine2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the value of the addressLine2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine2(String value) {
        this.addressLine2 = value;
    }

    /**
     * Gets the value of the zip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZip(String value) {
        this.zip = value;
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

    /**
     * Gets the value of the emergencyPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    /**
     * Sets the value of the emergencyPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmergencyPhone(String value) {
        this.emergencyPhone = value;
    }

    /**
     * Gets the value of the sevisId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSevisId() {
        return sevisId;
    }

    /**
     * Sets the value of the sevisId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSevisId(String value) {
        this.sevisId = value;
    }

    /**
     * Gets the value of the supervisorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupervisorId() {
        return supervisorId;
    }

    /**
     * Sets the value of the supervisorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupervisorId(String value) {
        this.supervisorId = value;
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
     * Gets the value of the active property.
     * 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the userCountry property.
     * 
     * @return
     *     possible object is
     *     {@link UserCountry }
     *     
     */
    public UserCountry getUserCountry() {
        return userCountry;
    }

    /**
     * Sets the value of the userCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserCountry }
     *     
     */
    public void setUserCountry(UserCountry value) {
        this.userCountry = value;
    }

    /**
     * Gets the value of the userState property.
     * 
     * @return
     *     possible object is
     *     {@link UserState }
     *     
     */
    public UserState getUserState() {
        return userState;
    }

    /**
     * Sets the value of the userState property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserState }
     *     
     */
    public void setUserState(UserState value) {
        this.userState = value;
    }

    /**
     * Gets the value of the loginInfo property.
     * 
     * @return
     *     possible object is
     *     {@link LoginInfo }
     *     
     */
    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    /**
     * Sets the value of the loginInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link LoginInfo }
     *     
     */
    public void setLoginInfo(LoginInfo value) {
        this.loginInfo = value;
    }

    /**
     * Gets the value of the departments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the departments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDepartments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserDepartment }
     * 
     * 
     */
    public List<UserDepartment> getDepartments() {
        if (departments == null) {
            departments = new ArrayList<UserDepartment>();
        }
        return this.departments;
    }

    /**
     * Gets the value of the roles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserRole }
     * 
     * 
     */
    public List<UserRole> getRoles() {
        if (roles == null) {
            roles = new ArrayList<UserRole>();
        }
        return this.roles;
    }

    /**
     * Gets the value of the permissions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the permissions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPermissions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserPermissions }
     * 
     * 
     */
    public List<UserPermissions> getPermissions() {
        if (permissions == null) {
            permissions = new ArrayList<UserPermissions>();
        }
        return this.permissions;
    }

    /**
     * Gets the value of the userNotes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userNotes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserNotes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserNotes }
     * 
     * 
     */
    public List<UserNotes> getUserNotes() {
        if (userNotes == null) {
            userNotes = new ArrayList<UserNotes>();
        }
        return this.userNotes;
    }

}
