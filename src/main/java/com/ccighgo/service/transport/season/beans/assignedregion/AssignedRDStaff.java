//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.05 at 12:34:22 PM CDT 
//

package com.ccighgo.service.transport.season.beans.assignedregion;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for AssignedRDStaff complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssignedRDStaff">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="staffId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="assignedArea" type="{http://www.ccighgo.com/assignedregion}RegionAssignedArea" maxOccurs="unbounded"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="photo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seasonGeographyConfigurationId" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssignedRDStaff", propOrder = { "staffId", "assignedArea", "firstName", "lastName", "photo", "seasonGeographyConfigurationId" })
public class AssignedRDStaff {

   protected int staffId;
   @XmlElement(required = true)
   protected List<RegionAssignedArea> assignedArea;
   @XmlElement(required = true)
   protected String firstName;
   @XmlElement(required = true)
   protected String lastName;
   @XmlElement(required = true)
   protected String photo;
   @XmlElement(required = true)
   protected int seasonGeographyConfigurationId;

   /**
    * Gets the value of the staffId property.
    * 
    */
   public int getStaffId() {
      return staffId;
   }

   /**
    * Sets the value of the staffId property.
    * 
    */
   public void setStaffId(int value) {
      this.staffId = value;
   }

   /**
    * Gets the value of the assignedArea property.
    * 
    * <p>
    * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
    * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
    * the assignedArea property.
    * 
    * <p>
    * For example, to add a new item, do as follows:
    * 
    * <pre>
    * getAssignedArea().add(newItem);
    * </pre>
    * 
    * 
    * <p>
    * Objects of the following type(s) are allowed in the list {@link RegionAssignedArea }
    * 
    * 
    */
   public List<RegionAssignedArea> getAssignedArea() {
      if (assignedArea == null) {
         assignedArea = new ArrayList<RegionAssignedArea>();
      }
      return this.assignedArea;
   }

   /**
    * Gets the value of the firstName property.
    * 
    * @return possible object is {@link String }
    * 
    */
   public String getFirstName() {
      return firstName;
   }

   /**
    * Sets the value of the firstName property.
    * 
    * @param value
    *           allowed object is {@link String }
    * 
    */
   public void setFirstName(String value) {
      this.firstName = value;
   }

   /**
    * Gets the value of the lastName property.
    * 
    * @return possible object is {@link String }
    * 
    */
   public String getLastName() {
      return lastName;
   }

   /**
    * Sets the value of the lastName property.
    * 
    * @param value
    *           allowed object is {@link String }
    * 
    */
   public void setLastName(String value) {
      this.lastName = value;
   }

   /**
    * Gets the value of the photo property.
    * 
    * @return possible object is {@link String }
    * 
    */
   public String getPhoto() {
      return photo;
   }

   /**
    * Sets the value of the photo property.
    * 
    * @param value
    *           allowed object is {@link String }
    * 
    */
   public void setPhoto(String value) {
      this.photo = value;
   }

   /**
    * Gets the value of the seasonGeographyConfigurationId property.
    * 
    * @return possible object is {@link BigInteger }
    * 
    */
   public int getSeasonGeographyConfigurationId() {
      return seasonGeographyConfigurationId;
   }

   /**
    * Sets the value of the seasonGeographyConfigurationId property.
    * 
    * @param value
    *           allowed object is {@link BigInteger }
    * 
    */
   public void setSeasonGeographyConfigurationId(int value) {
      this.seasonGeographyConfigurationId = value;
   }

}
