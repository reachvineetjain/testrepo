package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FieldStaffStatus database table.
 * 
 */
@Entity
@Table(name="FieldStaffStatus")
@NamedQuery(name="FieldStaffStatus.findAll", query="SELECT f FROM FieldStaffStatus f")
public class FieldStaffStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer fieldStaffStatusId;

	@Column(nullable=false, length=50)
	private String fieldStaffStatusName;

	//bi-directional many-to-one association to FieldStaff
	@OneToMany(mappedBy="fieldStaffStatus")
	private List<FieldStaff> fieldStaffs;

	//bi-directional many-to-one association to FieldStaffSeason
	@OneToMany(mappedBy="fieldStaffStatus")
	private List<FieldStaffSeason> fieldStaffSeasons;

	public FieldStaffStatus() {
	}

	public Integer getFieldStaffStatusId() {
		return this.fieldStaffStatusId;
	}

	public void setFieldStaffStatusId(Integer fieldStaffStatusId) {
		this.fieldStaffStatusId = fieldStaffStatusId;
	}

	public String getFieldStaffStatusName() {
		return this.fieldStaffStatusName;
	}

	public void setFieldStaffStatusName(String fieldStaffStatusName) {
		this.fieldStaffStatusName = fieldStaffStatusName;
	}

	public List<FieldStaff> getFieldStaffs() {
		return this.fieldStaffs;
	}

	public void setFieldStaffs(List<FieldStaff> fieldStaffs) {
		this.fieldStaffs = fieldStaffs;
	}

	public FieldStaff addFieldStaff(FieldStaff fieldStaff) {
		getFieldStaffs().add(fieldStaff);
		fieldStaff.setFieldStaffStatus(this);

		return fieldStaff;
	}

	public FieldStaff removeFieldStaff(FieldStaff fieldStaff) {
		getFieldStaffs().remove(fieldStaff);
		fieldStaff.setFieldStaffStatus(null);

		return fieldStaff;
	}

	public List<FieldStaffSeason> getFieldStaffSeasons() {
		return this.fieldStaffSeasons;
	}

	public void setFieldStaffSeasons(List<FieldStaffSeason> fieldStaffSeasons) {
		this.fieldStaffSeasons = fieldStaffSeasons;
	}

	public FieldStaffSeason addFieldStaffSeason(FieldStaffSeason fieldStaffSeason) {
		getFieldStaffSeasons().add(fieldStaffSeason);
		fieldStaffSeason.setFieldStaffStatus(this);

		return fieldStaffSeason;
	}

	public FieldStaffSeason removeFieldStaffSeason(FieldStaffSeason fieldStaffSeason) {
		getFieldStaffSeasons().remove(fieldStaffSeason);
		fieldStaffSeason.setFieldStaffStatus(null);

		return fieldStaffSeason;
	}

}