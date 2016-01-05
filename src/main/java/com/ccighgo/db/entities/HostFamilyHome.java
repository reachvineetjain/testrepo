package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HostFamilyHome database table.
 * 
 */
@Entity
@Table(name="HostFamilyHome")
@NamedQuery(name="HostFamilyHome.findAll", query="SELECT h FROM HostFamilyHome h")
public class HostFamilyHome implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyHomeId;

	private Byte active;

	@Lob
	private String amenities;

	private Integer birdCount;

	private Integer catCount;

	@Column(length=50)
	private String confirmStudentAdditionalAmenities;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private Integer dogCount;

	@Lob
	private String expectedResponsibilities;

	@Lob
	private String extraActivities;

	private Byte hasComputer;

	private Byte hasPiano;

	@Lob
	private String homeDescription;

	@Column(length=30)
	private String homeLocation;

	@Column(length=30)
	private String homeType;

	@Lob
	private String hopeToLearn;

	private Integer hostedOther;

	@Lob
	private String hostedOtherDetails;

	@Lob
	private String hostingReason;

	@Column(length=25)
	private String houseHoldActivity;

	private Byte isStudentsRoomBasement;

	@Column(length=100)
	private String languageSpoken;

	private Integer localCoordinatorCCI;

	@Lob
	private String localCoordinatorDetails;

	private Integer localCoordinatorOther;

	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	private Integer otherAnimalCount;

	@Column(length=100)
	private String otherAnimalText;

	@Column(length=30)
	private String petLocation;

	private Integer rabbitCount;

	private Integer reptileCount;

	private Byte sharesBedroom;

	@Column(length=100)
	private String sharesBedroomWith;

	@Column(length=50)
	private String specialFeaturesInHome;

	@Lob
	private String studentHomeNeighbourhood;

	@Lob
	private String utilities;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
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

	public Integer getBirdCount() {
		return this.birdCount;
	}

	public void setBirdCount(Integer birdCount) {
		this.birdCount = birdCount;
	}

	public Integer getCatCount() {
		return this.catCount;
	}

	public void setCatCount(Integer catCount) {
		this.catCount = catCount;
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

	public Integer getDogCount() {
		return this.dogCount;
	}

	public void setDogCount(Integer dogCount) {
		this.dogCount = dogCount;
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

	public Byte getHasComputer() {
		return this.hasComputer;
	}

	public void setHasComputer(Byte hasComputer) {
		this.hasComputer = hasComputer;
	}

	public Byte getHasPiano() {
		return this.hasPiano;
	}

	public void setHasPiano(Byte hasPiano) {
		this.hasPiano = hasPiano;
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

	public Integer getOtherAnimalCount() {
		return this.otherAnimalCount;
	}

	public void setOtherAnimalCount(Integer otherAnimalCount) {
		this.otherAnimalCount = otherAnimalCount;
	}

	public String getOtherAnimalText() {
		return this.otherAnimalText;
	}

	public void setOtherAnimalText(String otherAnimalText) {
		this.otherAnimalText = otherAnimalText;
	}

	public String getPetLocation() {
		return this.petLocation;
	}

	public void setPetLocation(String petLocation) {
		this.petLocation = petLocation;
	}

	public Integer getRabbitCount() {
		return this.rabbitCount;
	}

	public void setRabbitCount(Integer rabbitCount) {
		this.rabbitCount = rabbitCount;
	}

	public Integer getReptileCount() {
		return this.reptileCount;
	}

	public void setReptileCount(Integer reptileCount) {
		this.reptileCount = reptileCount;
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

	public String getSpecialFeaturesInHome() {
		return this.specialFeaturesInHome;
	}

	public void setSpecialFeaturesInHome(String specialFeaturesInHome) {
		this.specialFeaturesInHome = specialFeaturesInHome;
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