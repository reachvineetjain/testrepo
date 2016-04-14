package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ResourceType database table.
 * 
 */
@Entity
@Table(name="ResourceType")
@NamedQuery(name="ResourceType.findAll", query="SELECT r FROM ResourceType r")
public class ResourceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer resourceTypeId;

	@Column(length=50)
	private String displayName;

	@Column(length=50)
	private String resourceTypeName;

	//bi-directional many-to-one association to EmployerResource
	@OneToMany(mappedBy="resourceType")
	private List<EmployerResource> employerResources;

	//bi-directional many-to-one association to FieldStaffResource
	@OneToMany(mappedBy="resourceType")
	private List<FieldStaffResource> fieldStaffResources;

	//bi-directional many-to-one association to HostFamilyResource
	@OneToMany(mappedBy="resourceType")
	private List<HostFamilyResource> hostFamilyResources;

	//bi-directional many-to-one association to ParticipantResource
	@OneToMany(mappedBy="resourceType")
	private List<ParticipantResource> participantResources;

	//bi-directional many-to-one association to PartnerResource
	@OneToMany(mappedBy="resourceType")
	private List<PartnerResource> partnerResources;

	public ResourceType() {
	}

	public Integer getResourceTypeId() {
		return this.resourceTypeId;
	}

	public void setResourceTypeId(Integer resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getResourceTypeName() {
		return this.resourceTypeName;
	}

	public void setResourceTypeName(String resourceTypeName) {
		this.resourceTypeName = resourceTypeName;
	}

	public List<EmployerResource> getEmployerResources() {
		return this.employerResources;
	}

	public void setEmployerResources(List<EmployerResource> employerResources) {
		this.employerResources = employerResources;
	}

	public EmployerResource addEmployerResource(EmployerResource employerResource) {
		getEmployerResources().add(employerResource);
		employerResource.setResourceType(this);

		return employerResource;
	}

	public EmployerResource removeEmployerResource(EmployerResource employerResource) {
		getEmployerResources().remove(employerResource);
		employerResource.setResourceType(null);

		return employerResource;
	}

	public List<FieldStaffResource> getFieldStaffResources() {
		return this.fieldStaffResources;
	}

	public void setFieldStaffResources(List<FieldStaffResource> fieldStaffResources) {
		this.fieldStaffResources = fieldStaffResources;
	}

	public FieldStaffResource addFieldStaffResource(FieldStaffResource fieldStaffResource) {
		getFieldStaffResources().add(fieldStaffResource);
		fieldStaffResource.setResourceType(this);

		return fieldStaffResource;
	}

	public FieldStaffResource removeFieldStaffResource(FieldStaffResource fieldStaffResource) {
		getFieldStaffResources().remove(fieldStaffResource);
		fieldStaffResource.setResourceType(null);

		return fieldStaffResource;
	}

	public List<HostFamilyResource> getHostFamilyResources() {
		return this.hostFamilyResources;
	}

	public void setHostFamilyResources(List<HostFamilyResource> hostFamilyResources) {
		this.hostFamilyResources = hostFamilyResources;
	}

	public HostFamilyResource addHostFamilyResource(HostFamilyResource hostFamilyResource) {
		getHostFamilyResources().add(hostFamilyResource);
		hostFamilyResource.setResourceType(this);

		return hostFamilyResource;
	}

	public HostFamilyResource removeHostFamilyResource(HostFamilyResource hostFamilyResource) {
		getHostFamilyResources().remove(hostFamilyResource);
		hostFamilyResource.setResourceType(null);

		return hostFamilyResource;
	}

	public List<ParticipantResource> getParticipantResources() {
		return this.participantResources;
	}

	public void setParticipantResources(List<ParticipantResource> participantResources) {
		this.participantResources = participantResources;
	}

	public ParticipantResource addParticipantResource(ParticipantResource participantResource) {
		getParticipantResources().add(participantResource);
		participantResource.setResourceType(this);

		return participantResource;
	}

	public ParticipantResource removeParticipantResource(ParticipantResource participantResource) {
		getParticipantResources().remove(participantResource);
		participantResource.setResourceType(null);

		return participantResource;
	}

	public List<PartnerResource> getPartnerResources() {
		return this.partnerResources;
	}

	public void setPartnerResources(List<PartnerResource> partnerResources) {
		this.partnerResources = partnerResources;
	}

	public PartnerResource addPartnerResource(PartnerResource partnerResource) {
		getPartnerResources().add(partnerResource);
		partnerResource.setResourceType(this);

		return partnerResource;
	}

	public PartnerResource removePartnerResource(PartnerResource partnerResource) {
		getPartnerResources().remove(partnerResource);
		partnerResource.setResourceType(null);

		return partnerResource;
	}

}