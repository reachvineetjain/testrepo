package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HostFamilyPet database table.
 * 
 */
@Entity
@NamedQuery(name="HostFamilyPet.findAll", query="SELECT h FROM HostFamilyPet h")
public class HostFamilyPet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer hostFamilyPetId;

	private String additionalInformation;

	private String animalType;

	private Byte isIndoor;

	private Integer number;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	public HostFamilyPet() {
	}

	public Integer getHostFamilyPetId() {
		return this.hostFamilyPetId;
	}

	public void setHostFamilyPetId(Integer hostFamilyPetId) {
		this.hostFamilyPetId = hostFamilyPetId;
	}

	public String getAdditionalInformation() {
		return this.additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getAnimalType() {
		return this.animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public Byte getIsIndoor() {
		return this.isIndoor;
	}

	public void setIsIndoor(Byte isIndoor) {
		this.isIndoor = isIndoor;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

}