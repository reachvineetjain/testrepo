package com.ccighgo.service.transport.partner.beans.subpartner;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.ccighgo.service.transport.utility.beans.gender.Salutation;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubPartnerPrimaryContact", propOrder = {
      "salutation",
      "title",
      "firstName",
      "lastName",
      "email",
      "phone",
      "emergencyPhone",
      "fax",
      "receiveNotificationEmailFromCCI",
      "skypeId",
      "website",
      "typeOfPartnerUser"
})
public class SubPartnerPrimaryContact {
   
   protected Salutation salutation;
   protected String title;
   protected String firstName;
   protected String lastName;
   protected String email;
   protected String phone;
   protected String emergencyPhone;
   protected String fax;
   protected Byte receiveNotificationEmailFromCCI;
   protected String skypeId;
   protected String website;
   protected Integer typeOfPartnerUser;
   
   
   public Salutation getSalutation() {
      return salutation;
   }
   public void setSalutation(Salutation salutation) {
      this.salutation = salutation;
   }
   public String getTitle() {
      return title;
   }
   public void setTitle(String title) {
      this.title = title;
   }
   public String getFirstName() {
      return firstName;
   }
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }
   public String getLastName() {
      return lastName;
   }
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   public String getEmergencyPhone() {
      return emergencyPhone;
   }
   public void setEmergencyPhone(String emergencyPhone) {
      this.emergencyPhone = emergencyPhone;
   }
   public String getFax() {
      return fax;
   }
   public void setFax(String fax) {
      this.fax = fax;
   }
   public Byte getReceiveNotificationEmailFromCCI() {
      return receiveNotificationEmailFromCCI;
   }
   public void setReceiveNotificationEmailFromCCI(Byte receiveNotificationEmailFromCCI) {
      this.receiveNotificationEmailFromCCI = receiveNotificationEmailFromCCI;
   }
   public String getSkypeId() {
      return skypeId;
   }
   public void setSkypeId(String skypeId) {
      this.skypeId = skypeId;
   }
   public String getWebsite() {
      return website;
   }
   public void setWebsite(String website) {
      this.website = website;
   }
   public Integer getTypeOfPartnerUser() {
      return typeOfPartnerUser;
   }
   public void setTypeOfPartnerUser(Integer typeOfPartnerUser) {
      this.typeOfPartnerUser = typeOfPartnerUser;
   }
   
}
