package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HostFamilyPetType database table.
 * 
 */
@Entity
@Table(name="HostFamilyPetType")
@NamedQuery(name="HostFamilyPetType.findAll", query="SELECT h FROM HostFamilyPetType h")
public class HostFamilyPetType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyPetTypeId;

	@Column(length=25)
	private String hostFamilyPetTypeName;

	//bi-directional many-to-one association to HostFamilyPet
	@OneToMany(mappedBy="hostFamilyPetType")
	private List<HostFamilyPet> hostFamilyPets;

	public HostFamilyPetType() {
	}

	public Integer getHostFamilyPetTypeId() {
		return this.hostFamilyPetTypeId;
	}

	public void setHostFamilyPetTypeId(Integer hostFamilyPetTypeId) {
		this.hostFamilyPetTypeId = hostFamilyPetTypeId;
	}

	public String getHostFamilyPetTypeName() {
		return this.hostFamilyPetTypeName;
	}

	public void setHostFamilyPetTypeName(String hostFamilyPetTypeName) {
		this.hostFamilyPetTypeName = hostFamilyPetTypeName;
	}

	public List<HostFamilyPet> getHostFamilyPets() {
		return this.hostFamilyPets;
	}

	public void setHostFamilyPets(List<HostFamilyPet> hostFamilyPets) {
		this.hostFamilyPets = hostFamilyPets;
	}

	public HostFamilyPet addHostFamilyPet(HostFamilyPet hostFamilyPet) {
		getHostFamilyPets().add(hostFamilyPet);
		hostFamilyPet.setHostFamilyPetType(this);

		return hostFamilyPet;
	}

	public HostFamilyPet removeHostFamilyPet(HostFamilyPet hostFamilyPet) {
		getHostFamilyPets().remove(hostFamilyPet);
		hostFamilyPet.setHostFamilyPetType(null);

		return hostFamilyPet;
	}

}