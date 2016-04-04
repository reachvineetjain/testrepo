package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the HostFamilyBackground database table.
 * 
 */
@Entity
@Table(name = "HostFamilyBackground")
@NamedQuery(name = "HostFamilyBackground.findAll", query = "SELECT h FROM HostFamilyBackground h")
public class HostFamilyBackground implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(unique = true, nullable = false) private Integer hostFamilyBackgroundId;

   @Temporal(TemporalType.DATE) private Date birthDate;

   @Temporal(TemporalType.TIMESTAMP) private Date createdOn;

   @Temporal(TemporalType.DATE) private Date dateCheckedByCCI;

   @Column(length = 50) private String firstName;

   @Column(length = 50) private String lastName;

   @Column(length = 50) private String relationshipToHostParent;

   private Byte status;
   private String password;
   private String account;
   private Integer userId;
   private String resultStatus;
   private String resultURL;
   @Temporal(TemporalType.DATE) private Date dateOrderReceived;
   @Temporal(TemporalType.DATE) private Date dateOrderClosed;

   // bi-directional many-to-one association to HostFamilySeason
   @ManyToOne @JoinColumn(name = "hostFamilySeasonId") private HostFamilySeason hostFamilySeason;

   public HostFamilyBackground() {
   }

   public Integer getHostFamilyBackgroundId() {
      return this.hostFamilyBackgroundId;
   }

   public void setHostFamilyBackgroundId(Integer hostFamilyBackgroundId) {
      this.hostFamilyBackgroundId = hostFamilyBackgroundId;
   }

   public Date getBirthDate() {
      return this.birthDate;
   }

   public void setBirthDate(Date birthDate) {
      this.birthDate = birthDate;
   }

   public Date getCreatedOn() {
      return this.createdOn;
   }

   public void setCreatedOn(Date createdOn) {
      this.createdOn = createdOn;
   }

   public Date getDateCheckedByCCI() {
      return this.dateCheckedByCCI;
   }

   public void setDateCheckedByCCI(Date dateCheckedByCCI) {
      this.dateCheckedByCCI = dateCheckedByCCI;
   }

   public String getFirstName() {
      return this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return this.lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getRelationshipToHostParent() {
      return this.relationshipToHostParent;
   }

   public void setRelationshipToHostParent(String relationshipToHostParent) {
      this.relationshipToHostParent = relationshipToHostParent;
   }

   public Byte getStatus() {
      return this.status;
   }

   public void setStatus(Byte status) {
      this.status = status;
   }

   public HostFamilySeason getHostFamilySeason() {
      return this.hostFamilySeason;
   }

   public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
      this.hostFamilySeason = hostFamilySeason;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getAccount() {
      return account;
   }

   public void setAccount(String account) {
      this.account = account;
   }

   public Integer getUserId() {
      return userId;
   }

   public void setUserId(Integer userId) {
      this.userId = userId;
   }

   public String getResultStatus() {
      return resultStatus;
   }

   public void setResultStatus(String resultStatus) {
      this.resultStatus = resultStatus;
   }

   public String getResultURL() {
      return resultURL;
   }

   public void setResultURL(String resultURL) {
      this.resultURL = resultURL;
   }

   public Date getDateOrderReceived() {
      return dateOrderReceived;
   }

   public void setDateOrderReceived(Date dateOrderReceived) {
      this.dateOrderReceived = dateOrderReceived;
   }

   public Date getDateOrderClosed() {
      return dateOrderClosed;
   }

   public void setDateOrderClosed(Date dateOrderClosed) {
      this.dateOrderClosed = dateOrderClosed;
   }

}