package com.ccighgo.service.transport.seasons.beans.soapservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AreaRepresentativeData")
public class AreaRepresentativeData {

   @XmlElement(name = "FirstName")
   private String FirstName;
   @XmlElement(name = "LastName")
   private String LastName;
   @XmlElement(name = "HomeAddress")
   private String HomeAddress;
   @XmlElement(name = "City")
   private String City;
   @XmlElement(name = "State")
   private String State;
   @XmlElement(name = "PostalCode")
   private String PostalCode;
   @XmlElement(name = "HomePhone")
   private String HomePhone;
   @XmlElement(name = "WorkPhone")
   private String WorkPhone;
   @XmlElement(name = "Email")
   private String Email;
   @XmlElement(name = "Comments")
   private String Comments;
   @XmlElement(name = "InterestedInLocalCoordinatorForYear")
   private Integer InterestedInLocalCoordinatorForYear;
   @XmlElement(name = "InterestedInLocalCoordinatorForSummer")
   private Integer InterestedInLocalCoordinatorForSummer;
   @XmlElement(name = "CurrentCommunityVolunteer")
   private Boolean CurrentCommunityVolunteer;
   @XmlElement(name = "CurrentCommunityVolunteerDetails")
   private String CurrentCommunityVolunteerDetails;
   @XmlElement(name = "FieldStaffWillingToHost")
   private String FieldStaffWillingToHost;

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

   public String getHomeAddress() {
      return HomeAddress;
   }

   public void setHomeAddress(String homeAddress) {
      HomeAddress = homeAddress;
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

   public String getHomePhone() {
      return HomePhone;
   }

   public void setHomePhone(String homePhone) {
      HomePhone = homePhone;
   }

   public String getWorkPhone() {
      return WorkPhone;
   }

   public void setWorkPhone(String workPhone) {
      WorkPhone = workPhone;
   }

   public String getEmail() {
      return Email;
   }

   public void setEmail(String email) {
      Email = email;
   }

   public String getComments() {
      return Comments;
   }

   public void setComments(String comments) {
      Comments = comments;
   }

   public Integer getInterestedInLocalCoordinatorForYear() {
      return InterestedInLocalCoordinatorForYear;
   }

   public void setInterestedInLocalCoordinatorForYear(Integer interestedInLocalCoordinatorForYear) {
      InterestedInLocalCoordinatorForYear = interestedInLocalCoordinatorForYear;
   }

   public Integer getInterestedInLocalCoordinatorForSummer() {
      return InterestedInLocalCoordinatorForSummer;
   }

   public void setInterestedInLocalCoordinatorForSummer(Integer interestedInLocalCoordinatorForSummer) {
      InterestedInLocalCoordinatorForSummer = interestedInLocalCoordinatorForSummer;
   }

   public Boolean isCurrentCommunityVolunteer() {
      return CurrentCommunityVolunteer;
   }

   public void setCurrentCommunityVolunteer(Boolean currentCommunityVolunteer) {
      CurrentCommunityVolunteer = currentCommunityVolunteer;
   }

   public String getCurrentCommunityVolunteerDetails() {
      return CurrentCommunityVolunteerDetails;
   }

   public void setCurrentCommunityVolunteerDetails(String currentCommunityVolunteerDetails) {
      CurrentCommunityVolunteerDetails = currentCommunityVolunteerDetails;
   }

   public String getFieldStaffWillingToHost() {
      return FieldStaffWillingToHost;
   }

   public void setFieldStaffWillingToHost(String fieldStaffWillingToHost) {
      FieldStaffWillingToHost = fieldStaffWillingToHost;
   }

}
