package com.ccighgo.db.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the SeasonACList database table.
 * 
 */
@Entity
@NamedQuery(name="SeasonACList.findAll", query="SELECT s FROM SeasonACList s")
public class SeasonACList implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer departmentProgramId;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fieldstaffGoId;

	private String fieldStaffTypeName;

	private String firstName;

	private String lastName;

	private String photo;

	private Integer regionId;

	private String season;

	private Integer seasonGeographyConfigurationId;

	private Integer seasonId;

	private Integer superRegionId;

	private Integer usStatesId;

	public SeasonACList() {
	}

	public Integer getDepartmentProgramId() {
		return this.departmentProgramId;
	}

	public void setDepartmentProgramId(Integer departmentProgramId) {
		this.departmentProgramId = departmentProgramId;
	}

	public Integer getFieldstaffGoId() {
		return this.fieldstaffGoId;
	}

	public void setFieldstaffGoId(Integer fieldstaffGoId) {
		this.fieldstaffGoId = fieldstaffGoId;
	}

	public String getFieldStaffTypeName() {
		return this.fieldStaffTypeName;
	}

	public void setFieldStaffTypeName(String fieldStaffTypeName) {
		this.fieldStaffTypeName = fieldStaffTypeName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getSeason() {
		return this.season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public Integer getSeasonGeographyConfigurationId() {
		return this.seasonGeographyConfigurationId;
	}

	public void setSeasonGeographyConfigurationId(Integer seasonGeographyConfigurationId) {
		this.seasonGeographyConfigurationId = seasonGeographyConfigurationId;
	}

	public Integer getSeasonId() {
		return this.seasonId;
	}

	public void setSeasonId(Integer seasonId) {
		this.seasonId = seasonId;
	}

	public Integer getSuperRegionId() {
		return this.superRegionId;
	}

	public void setSuperRegionId(Integer superRegionId) {
		this.superRegionId = superRegionId;
	}

	public Integer getUsStatesId() {
		return this.usStatesId;
	}

	public void setUsStatesId(Integer usStatesId) {
		this.usStatesId = usStatesId;
	}

}