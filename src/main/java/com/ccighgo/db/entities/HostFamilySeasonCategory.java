package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HostFamilySeasonCategories database table.
 * 
 */
@Entity
@Table(name="HostFamilySeasonCategories")
@NamedQuery(name="HostFamilySeasonCategory.findAll", query="SELECT h FROM HostFamilySeasonCategory h")
public class HostFamilySeasonCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilySeasonCategoriesId;

	private Integer filledMandatoryFields;

	private Byte status;

	private Integer totalMandatoryFields;

	//bi-directional many-to-one association to HostFamilyApplicationCategory
	@ManyToOne
	@JoinColumn(name="hostFamilyApplicationCategoriesId")
	private HostFamilyApplicationCategory hostFamilyApplicationCategory;

	//bi-directional many-to-one association to HostFamilySeason
	@ManyToOne
	@JoinColumn(name="hostFamilySeasonId")
	private HostFamilySeason hostFamilySeason;

	public HostFamilySeasonCategory() {
	}

	public Integer getHostFamilySeasonCategoriesId() {
		return this.hostFamilySeasonCategoriesId;
	}

	public void setHostFamilySeasonCategoriesId(Integer hostFamilySeasonCategoriesId) {
		this.hostFamilySeasonCategoriesId = hostFamilySeasonCategoriesId;
	}

	public Integer getFilledMandatoryFields() {
		return this.filledMandatoryFields;
	}

	public void setFilledMandatoryFields(Integer filledMandatoryFields) {
		this.filledMandatoryFields = filledMandatoryFields;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getTotalMandatoryFields() {
		return this.totalMandatoryFields;
	}

	public void setTotalMandatoryFields(Integer totalMandatoryFields) {
		this.totalMandatoryFields = totalMandatoryFields;
	}

	public HostFamilyApplicationCategory getHostFamilyApplicationCategory() {
		return this.hostFamilyApplicationCategory;
	}

	public void setHostFamilyApplicationCategory(HostFamilyApplicationCategory hostFamilyApplicationCategory) {
		this.hostFamilyApplicationCategory = hostFamilyApplicationCategory;
	}

	public HostFamilySeason getHostFamilySeason() {
		return this.hostFamilySeason;
	}

	public void setHostFamilySeason(HostFamilySeason hostFamilySeason) {
		this.hostFamilySeason = hostFamilySeason;
	}

}