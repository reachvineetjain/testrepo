package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the HostFamilyCommunity database table.
 * 
 */
@Entity
@Table(name="HostFamilyCommunity")
@NamedQuery(name="HostFamilyCommunity.findAll", query="SELECT h FROM HostFamilyCommunity h")
public class HostFamilyCommunity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyCommunityId;

	private Byte active;

	@Lob
	private String areasToAvoid;

	@Lob
	private String childrenActivities;

	@Lob
	private String childrenEnrolled;

	@Column(length=50)
	private String cityPopulation;

	@Column(length=50)
	private String cityWebsite;

	@Lob
	private String communityDetails;

	private Byte contactedByCoach;

	private Integer createdBy;

	private Timestamp createdOn;

	@Column(length=50)
	private String distanceFromCity;

	@Column(length=50)
	private String distanceToSchool;

	private Byte involvedInVolunteerService;

	@Lob
	private String involvedInVolunteerServiceDetails;

	private Integer modifiedBy;

	private Timestamp modifiedOn;

	@Column(length=50)
	private String nearestCity;

	private Byte parentIsTeacher;

	@Lob
	private String placesOfInterest;

	@Column(length=50)
	private String population;

	@Column(length=100)
	private String schoolTravelMethod;

	private Byte transportationToActivities;

	@Lob
	private String transportationToActivitiesDetails;

	@Column(length=50)
	private String usRegion;

	@Lob
	private String volunteeringOpportunitiesCommunity;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	public HostFamilyCommunity() {
	}

	public Integer getHostFamilyCommunityId() {
		return this.hostFamilyCommunityId;
	}

	public void setHostFamilyCommunityId(Integer hostFamilyCommunityId) {
		this.hostFamilyCommunityId = hostFamilyCommunityId;
	}

	public Byte getActive() {
		return this.active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public String getAreasToAvoid() {
		return this.areasToAvoid;
	}

	public void setAreasToAvoid(String areasToAvoid) {
		this.areasToAvoid = areasToAvoid;
	}

	public String getChildrenActivities() {
		return this.childrenActivities;
	}

	public void setChildrenActivities(String childrenActivities) {
		this.childrenActivities = childrenActivities;
	}

	public String getChildrenEnrolled() {
		return this.childrenEnrolled;
	}

	public void setChildrenEnrolled(String childrenEnrolled) {
		this.childrenEnrolled = childrenEnrolled;
	}

	public String getCityPopulation() {
		return this.cityPopulation;
	}

	public void setCityPopulation(String cityPopulation) {
		this.cityPopulation = cityPopulation;
	}

	public String getCityWebsite() {
		return this.cityWebsite;
	}

	public void setCityWebsite(String cityWebsite) {
		this.cityWebsite = cityWebsite;
	}

	public String getCommunityDetails() {
		return this.communityDetails;
	}

	public void setCommunityDetails(String communityDetails) {
		this.communityDetails = communityDetails;
	}

	public Byte getContactedByCoach() {
		return this.contactedByCoach;
	}

	public void setContactedByCoach(Byte contactedByCoach) {
		this.contactedByCoach = contactedByCoach;
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

	public String getDistanceFromCity() {
		return this.distanceFromCity;
	}

	public void setDistanceFromCity(String distanceFromCity) {
		this.distanceFromCity = distanceFromCity;
	}

	public String getDistanceToSchool() {
		return this.distanceToSchool;
	}

	public void setDistanceToSchool(String distanceToSchool) {
		this.distanceToSchool = distanceToSchool;
	}

	public Byte getInvolvedInVolunteerService() {
		return this.involvedInVolunteerService;
	}

	public void setInvolvedInVolunteerService(Byte involvedInVolunteerService) {
		this.involvedInVolunteerService = involvedInVolunteerService;
	}

	public String getInvolvedInVolunteerServiceDetails() {
		return this.involvedInVolunteerServiceDetails;
	}

	public void setInvolvedInVolunteerServiceDetails(String involvedInVolunteerServiceDetails) {
		this.involvedInVolunteerServiceDetails = involvedInVolunteerServiceDetails;
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

	public String getNearestCity() {
		return this.nearestCity;
	}

	public void setNearestCity(String nearestCity) {
		this.nearestCity = nearestCity;
	}

	public Byte getParentIsTeacher() {
		return this.parentIsTeacher;
	}

	public void setParentIsTeacher(Byte parentIsTeacher) {
		this.parentIsTeacher = parentIsTeacher;
	}

	public String getPlacesOfInterest() {
		return this.placesOfInterest;
	}

	public void setPlacesOfInterest(String placesOfInterest) {
		this.placesOfInterest = placesOfInterest;
	}

	public String getPopulation() {
		return this.population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getSchoolTravelMethod() {
		return this.schoolTravelMethod;
	}

	public void setSchoolTravelMethod(String schoolTravelMethod) {
		this.schoolTravelMethod = schoolTravelMethod;
	}

	public Byte getTransportationToActivities() {
		return this.transportationToActivities;
	}

	public void setTransportationToActivities(Byte transportationToActivities) {
		this.transportationToActivities = transportationToActivities;
	}

	public String getTransportationToActivitiesDetails() {
		return this.transportationToActivitiesDetails;
	}

	public void setTransportationToActivitiesDetails(String transportationToActivitiesDetails) {
		this.transportationToActivitiesDetails = transportationToActivitiesDetails;
	}

	public String getUsRegion() {
		return this.usRegion;
	}

	public void setUsRegion(String usRegion) {
		this.usRegion = usRegion;
	}

	public String getVolunteeringOpportunitiesCommunity() {
		return this.volunteeringOpportunitiesCommunity;
	}

	public void setVolunteeringOpportunitiesCommunity(String volunteeringOpportunitiesCommunity) {
		this.volunteeringOpportunitiesCommunity = volunteeringOpportunitiesCommunity;
	}

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

}