package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FieldStaffType database table.
 * 
 */
@Entity
@Table(name="FieldStaffType")
@NamedQuery(name="FieldStaffType.findAll", query="SELECT f FROM FieldStaffType f")
public class FieldStaffType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer fieldStaffTypeId;

	@Column(length=50)
	private String fieldStaffType;

	@Column(length=10)
	private String fieldStaffTypeCode;

	//bi-directional many-to-one association to FieldStaffLCSeason
	@OneToMany(mappedBy="fieldStaffType")
	private List<FieldStaffLCSeason> fieldStaffLcseasons;

	//bi-directional many-to-one association to FieldStaffLeadershipSeason
	@OneToMany(mappedBy="fieldStaffType")
	private List<FieldStaffLeadershipSeason> fieldStaffLeadershipSeasons;

	public FieldStaffType() {
	}

	public Integer getFieldStaffTypeId() {
		return this.fieldStaffTypeId;
	}

	public void setFieldStaffTypeId(Integer fieldStaffTypeId) {
		this.fieldStaffTypeId = fieldStaffTypeId;
	}

	public String getFieldStaffType() {
		return this.fieldStaffType;
	}

	public void setFieldStaffType(String fieldStaffType) {
		this.fieldStaffType = fieldStaffType;
	}

	public String getFieldStaffTypeCode() {
		return this.fieldStaffTypeCode;
	}

	public void setFieldStaffTypeCode(String fieldStaffTypeCode) {
		this.fieldStaffTypeCode = fieldStaffTypeCode;
	}

	public List<FieldStaffLCSeason> getFieldStaffLcseasons() {
		return this.fieldStaffLcseasons;
	}

	public void setFieldStaffLcseasons(List<FieldStaffLCSeason> fieldStaffLcseasons) {
		this.fieldStaffLcseasons = fieldStaffLcseasons;
	}

	public FieldStaffLCSeason addFieldStaffLcseason(FieldStaffLCSeason fieldStaffLcseason) {
		getFieldStaffLcseasons().add(fieldStaffLcseason);
		fieldStaffLcseason.setFieldStaffType(this);

		return fieldStaffLcseason;
	}

	public FieldStaffLCSeason removeFieldStaffLcseason(FieldStaffLCSeason fieldStaffLcseason) {
		getFieldStaffLcseasons().remove(fieldStaffLcseason);
		fieldStaffLcseason.setFieldStaffType(null);

		return fieldStaffLcseason;
	}

	public List<FieldStaffLeadershipSeason> getFieldStaffLeadershipSeasons() {
		return this.fieldStaffLeadershipSeasons;
	}

	public void setFieldStaffLeadershipSeasons(List<FieldStaffLeadershipSeason> fieldStaffLeadershipSeasons) {
		this.fieldStaffLeadershipSeasons = fieldStaffLeadershipSeasons;
	}

	public FieldStaffLeadershipSeason addFieldStaffLeadershipSeason(FieldStaffLeadershipSeason fieldStaffLeadershipSeason) {
		getFieldStaffLeadershipSeasons().add(fieldStaffLeadershipSeason);
		fieldStaffLeadershipSeason.setFieldStaffType(this);

		return fieldStaffLeadershipSeason;
	}

	public FieldStaffLeadershipSeason removeFieldStaffLeadershipSeason(FieldStaffLeadershipSeason fieldStaffLeadershipSeason) {
		getFieldStaffLeadershipSeasons().remove(fieldStaffLeadershipSeason);
		fieldStaffLeadershipSeason.setFieldStaffType(null);

		return fieldStaffLeadershipSeason;
	}

}