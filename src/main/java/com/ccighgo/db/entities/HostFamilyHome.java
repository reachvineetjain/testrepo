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

	@Column(length=1000)
	private String amenities;

	private Integer bathroomNumber;

	private Integer bedroomNumber;

	private Integer createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private Byte exitBasement;

	@Column(length=1000)
	private String extraActivities;

	@Column(length=1000)
	private String extraFacilities;

	@Column(length=1000)
	private String homeDescription;

	@Column(length=30)
	private String homeLocation;

	@Column(length=30)
	private String homeType;

	@Column(length=1000)
	private String hopeToLearn;

	@Column(nullable=false)
	private Byte hostedOther;

	@Column(length=100)
	private String hostedOtherDetails;

	@Column(length=1000)
	private String hostingReason;

	private Byte interestedForTwoStudents;

	private Byte isStudentsRoomBasement;

	@Column(length=100)
	private String localCoordinatorDetails;

	@Column(nullable=false)
	private Byte localCoordinatorOther;

	private Integer modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	@Column(length=50)
	private String otherTypeOfBusiness;

	private Byte residenceSiteFunctioningBusiness;

	private Byte sharesBedroom;

	@Column(length=50)
	private String sharesBedroomWith;

	private Integer sharingAge;

	@Column(length=100)
	private String specialFeaturesInHome;

	@Column(length=25)
	private String specifyTypeOfBusiness;

	@Lob
	private String studentHomeNeighbourhood;

	@Column(length=1000)
	private String studentsResponsibilities;

	@Column(length=1000)
	private String utilities;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	//bi-directional many-to-one association to LookupGender
	@ManyToOne
	@JoinColumn(name="sharingBedroomGenderId")
	private LookupGender lookupGender;

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

	public Integer getBathroomNumber() {
		return this.bathroomNumber;
	}

	public void setBathroomNumber(Integer bathroomNumber) {
		this.bathroomNumber = bathroomNumber;
	}

	public Integer getBedroomNumber() {
		return this.bedroomNumber;
	}

	public void setBedroomNumber(Integer bedroomNumber) {
		this.bedroomNumber = bedroomNumber;
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

	public Byte getHostedOther() {
		return this.hostedOther;
	}

	public void setHostedOther(Byte hostedOther) {
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

	public Byte getInterestedForTwoStudents() {
		return this.interestedForTwoStudents;
	}

	public void setInterestedForTwoStudents(Byte interestedForTwoStudents) {
		this.interestedForTwoStudents = interestedForTwoStudents;
	}

	public Byte getIsStudentsRoomBasement() {
		return this.isStudentsRoomBasement;
	}

	public void setIsStudentsRoomBasement(Byte isStudentsRoomBasement) {
		this.isStudentsRoomBasement = isStudentsRoomBasement;
	}

	public String getLocalCoordinatorDetails() {
		return this.localCoordinatorDetails;
	}

	public void setLocalCoordinatorDetails(String localCoordinatorDetails) {
		this.localCoordinatorDetails = localCoordinatorDetails;
	}

	public Byte getLocalCoordinatorOther() {
		return this.localCoordinatorOther;
	}

	public void setLocalCoordinatorOther(Byte localCoordinatorOther) {
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

	public String getStudentsResponsibilities() {
		return this.studentsResponsibilities;
	}

	public void setStudentsResponsibilities(String studentsResponsibilities) {
		this.studentsResponsibilities = studentsResponsibilities;
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

	public LookupGender getLookupGender() {
		return this.lookupGender;
	}

	public void setLookupGender(LookupGender lookupGender) {
		this.lookupGender = lookupGender;
	}

}