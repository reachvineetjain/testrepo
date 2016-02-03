package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyHome database table.
 * 
 */
@Entity
@NamedQuery(name="HostFamilyHome.findAll", query="SELECT h FROM HostFamilyHome h")
public class HostFamilyHome implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hostFamilyHomeId;

	private Byte active;

	@Lob
	private String amenities;

	private String confirmStudentAdditionalAmenities;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private Byte exitBasement;

	private Byte exitOutOfBasement;

	@Lob
	private String expectedResponsibilities;

	@Lob
	private String extraActivities;

	@Lob
	private String extraFacilities;

	@Lob
	private String homeDescription;

	private String homeLocation;

	private String homeType;

	@Lob
	private String hopeToLearn;

	private Integer hostedOther;

	@Lob
	private String hostedOtherDetails;

	@Lob
	private String hostingReason;

	private String houseHoldActivity;

	private Byte isLocalCordinator;

	private Byte isStudentsRoomBasement;

	private String languageSpoken;

	private Integer localCoordinatorCCI;

	@Lob
	private String localCoordinatorDetails;

	private Integer localCoordinatorOther;

	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	private String otherTypeOfBusiness;

	private Byte residenceSiteFunctioningBusiness;

	private Byte sharesBedroom;

	private String sharesBedroomWith;

	private Integer sharingAge;

	private Integer sharingBedroomGenderId;

	private String sharingOther;

	private String specialFeaturesInHome;

	private String specifyTypeOfBusiness;

	@Lob
	private String studentHomeNeighbourhood;

	@Lob
	private String utilities;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId",insertable=false,updatable=false)
	private HostFamilySeason hostFamilySeason;

	public HostFamilyHome() {
	}

	public Integer getHostFamilyHomeId() {
		return this.hostFamilyHomeId;
	}

	public void setHostFamilyHomeId(Integer hostFamilyHomeId) {
		this.hostFamilyHomeId = hostFamilyHomeId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getAmenities() {
		return this.amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public String getConfirmStudentAdditionalAmenities() {
		return this.confirmStudentAdditionalAmenities;
	}

	public void setConfirmStudentAdditionalAmenities(String confirmStudentAdditionalAmenities) {
		this.confirmStudentAdditionalAmenities = confirmStudentAdditionalAmenities;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Byte getExitBasement() {
		return this.exitBasement;
	}

	public void setExitBasement(Byte exitBasement) {
		this.exitBasement = exitBasement;
	}

	public Byte getExitOutOfBasement() {
		return this.exitOutOfBasement;
	}

	public void setExitOutOfBasement(Byte exitOutOfBasement) {
		this.exitOutOfBasement = exitOutOfBasement;
	}

	public String getExpectedResponsibilities() {
		return this.expectedResponsibilities;
	}

	public void setExpectedResponsibilities(String expectedResponsibilities) {
		this.expectedResponsibilities = expectedResponsibilities;
	}

	public String getExtraActivities() {
		return this.extraActivities;
	}

	public void setExtraActivities(String extraActivities) {
		this.extraActivities = extraActivities;
	}

	public String getExtraFacilities() {
		return this.extraFacilities;
	}

	public void setExtraFacilities(String extraFacilities) {
		this.extraFacilities = extraFacilities;
	}

	public String getHomeDescription() {
		return this.homeDescription;
	}

	public void setHomeDescription(String homeDescription) {
		this.homeDescription = homeDescription;
	}

	public String getHomeLocation() {
		return this.homeLocation;
	}

	public void setHomeLocation(String homeLocation) {
		this.homeLocation = homeLocation;
	}

	public String getHomeType() {
		return this.homeType;
	}

	public void setHomeType(String homeType) {
		this.homeType = homeType;
	}

	public String getHopeToLearn() {
		return this.hopeToLearn;
	}

	public void setHopeToLearn(String hopeToLearn) {
		this.hopeToLearn = hopeToLearn;
	}

	public Integer getHostedOther() {
		return this.hostedOther;
	}

	public void setHostedOther(Integer hostedOther) {
		this.hostedOther = hostedOther;
	}

	public String getHostedOtherDetails() {
		return this.hostedOtherDetails;
	}

	public void setHostedOtherDetails(String hostedOtherDetails) {
		this.hostedOtherDetails = hostedOtherDetails;
	}

	public String getHostingReason() {
		return this.hostingReason;
	}

	public void setHostingReason(String hostingReason) {
		this.hostingReason = hostingReason;
	}

	public String getHouseHoldActivity() {
		return this.houseHoldActivity;
	}

	public void setHouseHoldActivity(String houseHoldActivity) {
		this.houseHoldActivity = houseHoldActivity;
	}

	public Byte getIsLocalCordinator() {
		return this.isLocalCordinator;
	}

	public void setIsLocalCordinator(Byte isLocalCordinator) {
		this.isLocalCordinator = isLocalCordinator;
	}

	public Byte getIsStudentsRoomBasement() {
		return this.isStudentsRoomBasement;
	}

	public void setIsStudentsRoomBasement(Byte isStudentsRoomBasement) {
		this.isStudentsRoomBasement = isStudentsRoomBasement;
	}

	public String getLanguageSpoken() {
		return this.languageSpoken;
	}

	public void setLanguageSpoken(String languageSpoken) {
		this.languageSpoken = languageSpoken;
	}

	public Integer getLocalCoordinatorCCI() {
		return this.localCoordinatorCCI;
	}

	public void setLocalCoordinatorCCI(Integer localCoordinatorCCI) {
		this.localCoordinatorCCI = localCoordinatorCCI;
	}

	public String getLocalCoordinatorDetails() {
		return this.localCoordinatorDetails;
	}

	public void setLocalCoordinatorDetails(String localCoordinatorDetails) {
		this.localCoordinatorDetails = localCoordinatorDetails;
	}

	public Integer getLocalCoordinatorOther() {
		return this.localCoordinatorOther;
	}

	public void setLocalCoordinatorOther(Integer localCoordinatorOther) {
		this.localCoordinatorOther = localCoordinatorOther;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getOtherTypeOfBusiness() {
		return this.otherTypeOfBusiness;
	}

	public void setOtherTypeOfBusiness(String otherTypeOfBusiness) {
		this.otherTypeOfBusiness = otherTypeOfBusiness;
	}

	public Byte getResidenceSiteFunctioningBusiness() {
		return this.residenceSiteFunctioningBusiness;
	}

	public void setResidenceSiteFunctioningBusiness(Byte residenceSiteFunctioningBusiness) {
		this.residenceSiteFunctioningBusiness = residenceSiteFunctioningBusiness;
	}

	public Byte getSharesBedroom() {
		return this.sharesBedroom;
	}

	public void setSharesBedroom(Byte sharesBedroom) {
		this.sharesBedroom = sharesBedroom;
	}

	public String getSharesBedroomWith() {
		return this.sharesBedroomWith;
	}

	public void setSharesBedroomWith(String sharesBedroomWith) {
		this.sharesBedroomWith = sharesBedroomWith;
	}

	public Integer getSharingAge() {
		return this.sharingAge;
	}

	public void setSharingAge(Integer sharingAge) {
		this.sharingAge = sharingAge;
	}

	public Integer getSharingBedroomGenderId() {
		return this.sharingBedroomGenderId;
	}

	public void setSharingBedroomGenderId(Integer sharingBedroomGenderId) {
		this.sharingBedroomGenderId = sharingBedroomGenderId;
	}

	public String getSharingOther() {
		return this.sharingOther;
	}

	public void setSharingOther(String sharingOther) {
		this.sharingOther = sharingOther;
	}

	public String getSpecialFeaturesInHome() {
		return this.specialFeaturesInHome;
	}

	public void setSpecialFeaturesInHome(String specialFeaturesInHome) {
		this.specialFeaturesInHome = specialFeaturesInHome;
	}

	public String getSpecifyTypeOfBusiness() {
		return this.specifyTypeOfBusiness;
	}

	public void setSpecifyTypeOfBusiness(String specifyTypeOfBusiness) {
		this.specifyTypeOfBusiness = specifyTypeOfBusiness;
	}

	public String getStudentHomeNeighbourhood() {
		return this.studentHomeNeighbourhood;
	}

	public void setStudentHomeNeighbourhood(String studentHomeNeighbourhood) {
		this.studentHomeNeighbourhood = studentHomeNeighbourhood;
	}

	public String getUtilities() {
		return this.utilities;
	}

	public void setUtilities(String utilities) {
		this.utilities = utilities;
	}

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

}