package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Relationship database table.
 * 
 */
@Entity
@NamedQuery(name="Relationship.findAll", query="SELECT r FROM Relationship r")
public class Relationship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer relationshipId;

	private String relationshipType;

	//bi-directional many-to-one association to HostFamilyMember
	@OneToMany(mappedBy="relationship")
	private List<HostFamilyMember> hostFamilyMembers;

	public Relationship() {
	}

	public Integer getRelationshipId() {
		return this.relationshipId;
	}

	public void setRelationshipId(Integer relationshipId) {
		this.relationshipId = relationshipId;
	}

	public String getRelationshipType() {
		return this.relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public List<HostFamilyMember> getHostFamilyMembers() {
		return this.hostFamilyMembers;
	}

	public void setHostFamilyMembers(List<HostFamilyMember> hostFamilyMembers) {
		this.hostFamilyMembers = hostFamilyMembers;
	}

	public HostFamilyMember addHostFamilyMember(HostFamilyMember hostFamilyMember) {
		getHostFamilyMembers().add(hostFamilyMember);
		hostFamilyMember.setRelationship(this);

		return hostFamilyMember;
	}

	public HostFamilyMember removeHostFamilyMember(HostFamilyMember hostFamilyMember) {
		getHostFamilyMembers().remove(hostFamilyMember);
		hostFamilyMember.setRelationship(null);

		return hostFamilyMember;
	}

}