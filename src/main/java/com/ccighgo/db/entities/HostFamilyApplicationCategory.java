package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the HostFamilyApplicationCategories database table.
 * 
 */
@Entity
@Table(name="HostFamilyApplicationCategories")
@NamedQuery(name="HostFamilyApplicationCategory.findAll", query="SELECT h FROM HostFamilyApplicationCategory h")
public class HostFamilyApplicationCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer hostFamilyApplicationCategoriesId;

	@Column(length=45)
	private String hostFamilyApplicationCategoryName;

	//bi-directional many-to-one association to HostFamilySeasonCategory
	@OneToMany(mappedBy="hostFamilyApplicationCategory")
	private List<HostFamilySeasonCategory> hostFamilySeasonCategories;

	public HostFamilyApplicationCategory() {
	}

	public Integer getHostFamilyApplicationCategoriesId() {
		return this.hostFamilyApplicationCategoriesId;
	}

	public void setHostFamilyApplicationCategoriesId(Integer hostFamilyApplicationCategoriesId) {
		this.hostFamilyApplicationCategoriesId = hostFamilyApplicationCategoriesId;
	}

	public String getHostFamilyApplicationCategoryName() {
		return this.hostFamilyApplicationCategoryName;
	}

	public void setHostFamilyApplicationCategoryName(String hostFamilyApplicationCategoryName) {
		this.hostFamilyApplicationCategoryName = hostFamilyApplicationCategoryName;
	}

	public List<HostFamilySeasonCategory> getHostFamilySeasonCategories() {
		return this.hostFamilySeasonCategories;
	}

	public void setHostFamilySeasonCategories(List<HostFamilySeasonCategory> hostFamilySeasonCategories) {
		this.hostFamilySeasonCategories = hostFamilySeasonCategories;
	}

	public HostFamilySeasonCategory addHostFamilySeasonCategory(HostFamilySeasonCategory hostFamilySeasonCategory) {
		getHostFamilySeasonCategories().add(hostFamilySeasonCategory);
		hostFamilySeasonCategory.setHostFamilyApplicationCategory(this);

		return hostFamilySeasonCategory;
	}

	public HostFamilySeasonCategory removeHostFamilySeasonCategory(HostFamilySeasonCategory hostFamilySeasonCategory) {
		getHostFamilySeasonCategories().remove(hostFamilySeasonCategory);
		hostFamilySeasonCategory.setHostFamilyApplicationCategory(null);

		return hostFamilySeasonCategory;
	}

}