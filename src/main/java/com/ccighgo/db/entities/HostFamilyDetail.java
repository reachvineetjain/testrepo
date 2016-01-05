package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the HostFamilyDetail database table.
 * 
 */
@Entity
@Table(name="HostFamilyDetail")
@NamedQuery(name="HostFamilyDetail.findAll", query="SELECT h FROM HostFamilyDetail h")
public class HostFamilyDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyDetailsId;

	private Byte active;

	@Lob
	private String adaptCircumtances;

	private Byte agreeToServeMeals;

	private Byte childServicesContact;

	@Lob
	private String childServicesContactDetails;

	private Byte comfortableHostingDiet;

	private Integer createdBy;

	private Timestamp createdOn;

	private Byte crimeConviction;

	@Lob
	private String crimeConvictionDetails;

	private Integer dietaryRestrictions;

	private Byte disability;

	@Lob
	private String disabilityDetails;

	@Column(length=50)
	private String drinkAlcohol;

	@Lob
	private String familyMembers;

	@Column(length=50)
	private String familySmoker;

	@Lob
	private String governmentAssistanceMembers;

	@Column(length=50)
	private String hasAutoInsurance;

	private byte illness;

	@Lob
	private String illnessDetails;

	@Column(length=30)
	private String incomeRange;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	private Byte participantFollowDiet;

	@Column(length=50)
	private String preferStudentJoins;

	private Integer problemWithReligiousDifference;

	private Byte receiveGovernmentAssistance;

	@Column(length=30)
	private String religiousAffiliation;

	@Column(length=30)
	private String religiousAttendance;

	@Lob
	private String specialWeekend;

	@Lob
	private String typicalWeekday;

	@Lob
	private String typicalWeekend;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	public HostFamilyDetail() {
	}

	public Integer getHostFamilyDetailsId() {
		return this.hostFamilyDetailsId;
	}

	public void setHostFamilyDetailsId(Integer hostFamilyDetailsId) {
		this.hostFamilyDetailsId = hostFamilyDetailsId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getAdaptCircumtances() {
		return this.adaptCircumtances;
	}

	public void setAdaptCircumtances(String adaptCircumtances) {
		this.adaptCircumtances = adaptCircumtances;
	}

	public Byte getAgreeToServeMeals() {
		return this.agreeToServeMeals;
	}

	public void setAgreeToServeMeals(Byte agreeToServeMeals) {
		this.agreeToServeMeals = agreeToServeMeals;
	}

	public Byte getChildServicesContact() {
		return this.childServicesContact;
	}

	public void setChildServicesContact(Byte childServicesContact) {
		this.childServicesContact = childServicesContact;
	}

	public String getChildServicesContactDetails() {
		return this.childServicesContactDetails;
	}

	public void setChildServicesContactDetails(String childServicesContactDetails) {
		this.childServicesContactDetails = childServicesContactDetails;
	}

	public Byte getComfortableHostingDiet() {
		return this.comfortableHostingDiet;
	}

	public void setComfortableHostingDiet(Byte comfortableHostingDiet) {
		this.comfortableHostingDiet = comfortableHostingDiet;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Byte getCrimeConviction() {
		return this.crimeConviction;
	}

	public void setCrimeConviction(Byte crimeConviction) {
		this.crimeConviction = crimeConviction;
	}

	public String getCrimeConvictionDetails() {
		return this.crimeConvictionDetails;
	}

	public void setCrimeConvictionDetails(String crimeConvictionDetails) {
		this.crimeConvictionDetails = crimeConvictionDetails;
	}

	public Integer getDietaryRestrictions() {
		return this.dietaryRestrictions;
	}

	public void setDietaryRestrictions(Integer dietaryRestrictions) {
		this.dietaryRestrictions = dietaryRestrictions;
	}

	public Byte getDisability() {
		return this.disability;
	}

	public void setDisability(Byte disability) {
		this.disability = disability;
	}

	public String getDisabilityDetails() {
		return this.disabilityDetails;
	}

	public void setDisabilityDetails(String disabilityDetails) {
		this.disabilityDetails = disabilityDetails;
	}

	public String getDrinkAlcohol() {
		return this.drinkAlcohol;
	}

	public void setDrinkAlcohol(String drinkAlcohol) {
		this.drinkAlcohol = drinkAlcohol;
	}

	public String getFamilyMembers() {
		return this.familyMembers;
	}

	public void setFamilyMembers(String familyMembers) {
		this.familyMembers = familyMembers;
	}

	public String getFamilySmoker() {
		return this.familySmoker;
	}

	public void setFamilySmoker(String familySmoker) {
		this.familySmoker = familySmoker;
	}

	public String getGovernmentAssistanceMembers() {
		return this.governmentAssistanceMembers;
	}

	public void setGovernmentAssistanceMembers(String governmentAssistanceMembers) {
		this.governmentAssistanceMembers = governmentAssistanceMembers;
	}

	public String getHasAutoInsurance() {
		return this.hasAutoInsurance;
	}

	public void setHasAutoInsurance(String hasAutoInsurance) {
		this.hasAutoInsurance = hasAutoInsurance;
	}

	public byte getIllness() {
		return this.illness;
	}

	public void setIllness(byte illness) {
		this.illness = illness;
	}

	public String getIllnessDetails() {
		return this.illnessDetails;
	}

	public void setIllnessDetails(String illnessDetails) {
		this.illnessDetails = illnessDetails;
	}

	public String getIncomeRange() {
		return this.incomeRange;
	}

	public void setIncomeRange(String incomeRange) {
		this.incomeRange = incomeRange;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Byte getParticipantFollowDiet() {
		return this.participantFollowDiet;
	}

	public void setParticipantFollowDiet(Byte participantFollowDiet) {
		this.participantFollowDiet = participantFollowDiet;
	}

	public String getPreferStudentJoins() {
		return this.preferStudentJoins;
	}

	public void setPreferStudentJoins(String preferStudentJoins) {
		this.preferStudentJoins = preferStudentJoins;
	}

	public Integer getProblemWithReligiousDifference() {
		return this.problemWithReligiousDifference;
	}

	public void setProblemWithReligiousDifference(Integer problemWithReligiousDifference) {
		this.problemWithReligiousDifference = problemWithReligiousDifference;
	}

	public Byte getReceiveGovernmentAssistance() {
		return this.receiveGovernmentAssistance;
	}

	public void setReceiveGovernmentAssistance(Byte receiveGovernmentAssistance) {
		this.receiveGovernmentAssistance = receiveGovernmentAssistance;
	}

	public String getReligiousAffiliation() {
		return this.religiousAffiliation;
	}

	public void setReligiousAffiliation(String religiousAffiliation) {
		this.religiousAffiliation = religiousAffiliation;
	}

	public String getReligiousAttendance() {
		return this.religiousAttendance;
	}

	public void setReligiousAttendance(String religiousAttendance) {
		this.religiousAttendance = religiousAttendance;
	}

	public String getSpecialWeekend() {
		return this.specialWeekend;
	}

	public void setSpecialWeekend(String specialWeekend) {
		this.specialWeekend = specialWeekend;
	}

	public String getTypicalWeekday() {
		return this.typicalWeekday;
	}

	public void setTypicalWeekday(String typicalWeekday) {
		this.typicalWeekday = typicalWeekday;
	}

	public String getTypicalWeekend() {
		return this.typicalWeekend;
	}

	public void setTypicalWeekend(String typicalWeekend) {
		this.typicalWeekend = typicalWeekend;
	}

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

}