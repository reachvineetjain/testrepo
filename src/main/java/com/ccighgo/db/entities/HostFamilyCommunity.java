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
	private int hostFamilyCommunityId;

	private byte active;

	@Column(length=1000)
	private String areasToAvoid;

	@Column(length=1000)
	private String childrenActivities;

	@Column(length=1000)
	private String childrenEnrolled;

	@Column(length=50)
	private String cityWebsite;

	private byte contactACoach;

	@Column(length=1000)
	private String contactByCoachDetails;

	private int createdBy;

	private Timestamp createdOn;

	@Column(length=50)
	private String distanceFromCity;

	@Column(length=50)
	private String distanceToSchool;

	private int modifiedBy;

	private Timestamp modifiedOn;

	@Column(length=50)
	private String nearestCity;

	@Column(length=50)
	private String nearestCityPopulation;

	private byte parentIsTeacher;

	@Column(length=1000)
	private String placesOfInterest;

	@Column(length=50)
	private String population;

	@Column(length=100)
	private String schoolTravelMethod;

	private byte transportationToActivities;

	@Column(length=1000)
	private String transportationToActivitiesDetails;

	@Column(length=100)
	private String uniquenessAboutCommunity;

	@Column(length=1000)
	private String volunteeringOpportunitiesCommunity;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	public HostFamilyCommunity() {
	}

	public int getHostFamilyCommunityId() {
		return this.hostFamilyCommunityId;
	}

	public void setHostFamilyCommunityId(int hostFamilyCommunityId) {
		this.hostFamilyCommunityId = hostFamilyCommunityId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
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

	public String getCityWebsite() {
		return this.cityWebsite;
	}

	public void setCityWebsite(String cityWebsite) {
		this.cityWebsite = cityWebsite;
	}

	public byte getContactACoach() {
		return this.contactACoach;
	}

	public void setContactACoach(byte contactACoach) {
		this.contactACoach = contactACoach;
	}

	public String getContactByCoachDetails() {
		return this.contactByCoachDetails;
	}

	public void setContactByCoachDetails(String contactByCoachDetails) {
		this.contactByCoachDetails = contactByCoachDetails;
	}

	public int getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(int createdBy) {
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

	public int getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
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

	public String getNearestCityPopulation() {
		return this.nearestCityPopulation;
	}

	public void setNearestCityPopulation(String nearestCityPopulation) {
		this.nearestCityPopulation = nearestCityPopulation;
	}

	public byte getParentIsTeacher() {
		return this.parentIsTeacher;
	}

	public void setParentIsTeacher(byte parentIsTeacher) {
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

	public byte getTransportationToActivities() {
		return this.transportationToActivities;
	}

	public void setTransportationToActivities(byte transportationToActivities) {
		this.transportationToActivities = transportationToActivities;
	}

	public String getTransportationToActivitiesDetails() {
		return this.transportationToActivitiesDetails;
	}

	public void setTransportationToActivitiesDetails(String transportationToActivitiesDetails) {
		this.transportationToActivitiesDetails = transportationToActivitiesDetails;
	}

	public String getUniquenessAboutCommunity() {
		return this.uniquenessAboutCommunity;
	}

	public void setUniquenessAboutCommunity(String uniquenessAboutCommunity) {
		this.uniquenessAboutCommunity = uniquenessAboutCommunity;
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