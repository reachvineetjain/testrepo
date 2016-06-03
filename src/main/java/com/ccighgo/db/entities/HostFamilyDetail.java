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

	@Column(length=1000)
	private String adaptCircumtances;

	private Byte agreeToServeMeals;

	private Byte childServicesContact;

	@Column(length=1000)
	private String childServicesContactDetails;

	private Byte comfortableHostingDiet;

	private Integer createdBy;

	private Timestamp createdOn;

	private Byte crimeConviction;

	@Column(length=1000)
	private String crimeConvictionDetails;

	@Column(length=100)
	private String descPaxDietaryRestrictions;

	@Column(length=100)
	private String describeDietaryRestrictions;

	private Byte dietaryRestrictions;

	private Byte disability;

	@Column(length=1000)
	private String disabilityDetails;

	@Column(length=50)
	private String drinkAlcohol;

	@Column(length=1000)
	private String familyMemberDescription;

	private Byte familySmoker;

	@Column(length=50)
	private String familySmokingPlace;

	@Column(length=1000)
	private String favouriteWeekend;

	private Byte happyToInviteStudent;

	@Column(length=50)
	private String hasAutoInsurance;

	private String homeLanguage;

	@Column(length=25)
	private String houseHoldType;

	private Byte illness;

	@Column(length=1000)
	private String illnessDetails;

	@Column(length=30)
	private String incomeRange;

	private Byte inviteStudentForReligiousExperience;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Column(length=30)
	private String otherLaungage;

	@Column(length=100)
	private String otherReligiousDetails;

	private Byte participantFollowDiet;

	@Column(length=30)
	private String preferStudentJoins;

	private Byte problemWithReligiousDifference;

	@Column(length=1000)
	private String publicAssistanceExplanation;

	private Byte receivePublicAssistance;

	@Column(length=30)
	private String religiousAffiliation;

	@Column(length=30)
	private String religiousAttendance;

	@Column(length=1000)
	private String typicalWeekday;

	@Column(length=1000)
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

	public String getDescPaxDietaryRestrictions() {
		return this.descPaxDietaryRestrictions;
	}

	public void setDescPaxDietaryRestrictions(String descPaxDietaryRestrictions) {
		this.descPaxDietaryRestrictions = descPaxDietaryRestrictions;
	}

	public String getDescribeDietaryRestrictions() {
		return this.describeDietaryRestrictions;
	}

	public void setDescribeDietaryRestrictions(String describeDietaryRestrictions) {
		this.describeDietaryRestrictions = describeDietaryRestrictions;
	}

	public Byte getDietaryRestrictions() {
		return this.dietaryRestrictions;
	}

	public void setDietaryRestrictions(Byte dietaryRestrictions) {
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

	public String getFamilyMemberDescription() {
		return this.familyMemberDescription;
	}

	public void setFamilyMemberDescription(String familyMemberDescription) {
		this.familyMemberDescription = familyMemberDescription;
	}

	public Byte getFamilySmoker() {
		return this.familySmoker;
	}

	public void setFamilySmoker(Byte familySmoker) {
		this.familySmoker = familySmoker;
	}

	public String getFamilySmokingPlace() {
		return this.familySmokingPlace;
	}

	public void setFamilySmokingPlace(String familySmokingPlace) {
		this.familySmokingPlace = familySmokingPlace;
	}

	public String getFavouriteWeekend() {
		return this.favouriteWeekend;
	}

	public void setFavouriteWeekend(String favouriteWeekend) {
		this.favouriteWeekend = favouriteWeekend;
	}

	public Byte getHappyToInviteStudent() {
		return this.happyToInviteStudent;
	}

	public void setHappyToInviteStudent(Byte happyToInviteStudent) {
		this.happyToInviteStudent = happyToInviteStudent;
	}

	public String getHasAutoInsurance() {
		return this.hasAutoInsurance;
	}

	public void setHasAutoInsurance(String hasAutoInsurance) {
		this.hasAutoInsurance = hasAutoInsurance;
	}

	public String getHomeLanguage() {
		return this.homeLanguage;
	}

	public void setHomeLanguage(String homeLanguage) {
		this.homeLanguage = homeLanguage;
	}

	public String getHouseHoldType() {
		return this.houseHoldType;
	}

	public void setHouseHoldType(String houseHoldType) {
		this.houseHoldType = houseHoldType;
	}

	public Byte getIllness() {
		return this.illness;
	}

	public void setIllness(Byte illness) {
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

	public Byte getInviteStudentForReligiousExperience() {
		return this.inviteStudentForReligiousExperience;
	}

	public void setInviteStudentForReligiousExperience(Byte inviteStudentForReligiousExperience) {
		this.inviteStudentForReligiousExperience = inviteStudentForReligiousExperience;
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

	public String getOtherLaungage() {
		return this.otherLaungage;
	}

	public void setOtherLaungage(String otherLaungage) {
		this.otherLaungage = otherLaungage;
	}

	public String getOtherReligiousDetails() {
		return this.otherReligiousDetails;
	}

	public void setOtherReligiousDetails(String otherReligiousDetails) {
		this.otherReligiousDetails = otherReligiousDetails;
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

	public Byte getProblemWithReligiousDifference() {
		return this.problemWithReligiousDifference;
	}

	public void setProblemWithReligiousDifference(Byte problemWithReligiousDifference) {
		this.problemWithReligiousDifference = problemWithReligiousDifference;
	}

	public String getPublicAssistanceExplanation() {
		return this.publicAssistanceExplanation;
	}

	public void setPublicAssistanceExplanation(String publicAssistanceExplanation) {
		this.publicAssistanceExplanation = publicAssistanceExplanation;
	}

	public Byte getReceivePublicAssistance() {
		return this.receivePublicAssistance;
	}

	public void setReceivePublicAssistance(Byte receivePublicAssistance) {
		this.receivePublicAssistance = receivePublicAssistance;
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