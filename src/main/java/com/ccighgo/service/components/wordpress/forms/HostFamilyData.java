package com.ccighgo.service.components.wordpress.forms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HostFamilyData")
public class HostFamilyData {

   @XmlElement(name = "FirstName")
   private String FirstName;
   @XmlElement(name = "LastName")
   private String LastName;
   @XmlElement(name = "Address1")
   private String Address1;
   @XmlElement(name = "Address2")
   private String Address2;
   @XmlElement(name = "City")
   private String City;
   @XmlElement(name = "State")
   private String State;
   @XmlElement(name = "PostalCode")
   private String PostalCode;
   @XmlElement(name = "NearestLargeCity")
   private String NearestLargeCity;
   @XmlElement(name = "DayPhone")
   private String DayPhone;
   @XmlElement(name = "EveningPhone")
   private String EveningPhone;
   @XmlElement(name = "Email")
   private String Email;
   @XmlElement(name = "IsAdult")
   private boolean IsAdult;
   @XmlElement(name = "SchoolName")
   private String SchoolName;
   @XmlElement(name = "SchoolCity")
   private String SchoolCity;
   @XmlElement(name = "InterestedInStudentFrom")
   private String InterestedInStudentFrom;
   @XmlElement(name = "InterestedInStudentGender")
   private String InterestedInStudentGender;
   @XmlElement(name = "ReferredFrom")
   private String ReferredFrom;
   @XmlElement(name = "Comments")
   private String Comments;
   @XmlElement(name = "AreaRepresentativeName")
   private String AreaRepresentativeName;
   @XmlElement(name = "HostingExperience")
   private String HostingExperience;
   @XmlElement(name = "IsLCApplicant")
   private boolean IsLCApplicant;
   @XmlElement(name = "LCEmailAddress")
   private String LCEmailAddress;

   public String getFirstName() {
      return FirstName;
   }

   public void setFirstName(String firstName) {
      FirstName = firstName;
   }

   public String getLastName() {
      return LastName;
   }

   public void setLastName(String lastName) {
      LastName = lastName;
   }

   public String getAddress1() {
      return Address1;
   }

   public void setAddress1(String address1) {
      Address1 = address1;
   }

   public String getAddress2() {
      return Address2;
   }

   public void setAddress2(String address2) {
      Address2 = address2;
   }

   public String getCity() {
      return City;
   }

   public void setCity(String city) {
      City = city;
   }

   public String getState() {
      return State;
   }

   public void setState(String state) {
      State = state;
   }

   public String getPostalCode() {
      return PostalCode;
   }

   public void setPostalCode(String postalCode) {
      PostalCode = postalCode;
   }

   public String getNearestLargeCity() {
      return NearestLargeCity;
   }

   public void setNearestLargeCity(String nearestLargeCity) {
      NearestLargeCity = nearestLargeCity;
   }

   public String getDayPhone() {
      return DayPhone;
   }

   public void setDayPhone(String dayPhone) {
      DayPhone = dayPhone;
   }

   public String getEveningPhone() {
      return EveningPhone;
   }

   public void setEveningPhone(String eveningPhone) {
      EveningPhone = eveningPhone;
   }

   public String getEmail() {
      return Email;
   }

   public void setEmail(String email) {
      Email = email;
   }

   public boolean isIsAdult() {
      return IsAdult;
   }

   public void setIsAdult(boolean isAdult) {
      IsAdult = isAdult;
   }

   public String getSchoolName() {
      return SchoolName;
   }

   public void setSchoolName(String schoolName) {
      SchoolName = schoolName;
   }

   public String getSchoolCity() {
      return SchoolCity;
   }

   public void setSchoolCity(String schoolCity) {
      SchoolCity = schoolCity;
   }

   public String getInterestedInStudentFrom() {
      return InterestedInStudentFrom;
   }

   public void setInterestedInStudentFrom(String interestedInStudentFrom) {
      InterestedInStudentFrom = interestedInStudentFrom;
   }

   public String getInterestedInStudentGender() {
      return InterestedInStudentGender;
   }

   public void setInterestedInStudentGender(String interestedInStudentGender) {
      InterestedInStudentGender = interestedInStudentGender;
   }

   public String getReferredFrom() {
      return ReferredFrom;
   }

   public void setReferredFrom(String referredFrom) {
      ReferredFrom = referredFrom;
   }

   public String getComments() {
      return Comments;
   }

   public void setComments(String comments) {
      Comments = comments;
   }

   public String getAreaRepresentativeName() {
      return AreaRepresentativeName;
   }

   public void setAreaRepresentativeName(String areaRepresentativeName) {
      AreaRepresentativeName = areaRepresentativeName;
   }

   public String getHostingExperience() {
      return HostingExperience;
   }

   public void setHostingExperience(String hostingExperience) {
      HostingExperience = hostingExperience;
   }

   public boolean isIsLCApplicant() {
      return IsLCApplicant;
   }

   public void setIsLCApplicant(boolean isLCApplicant) {
      IsLCApplicant = isLCApplicant;
   }

   public String getLCEmailAddress() {
      return LCEmailAddress;
   }

   public void setLCEmailAddress(String lCEmailAddress) {
      LCEmailAddress = lCEmailAddress;
   }

}
