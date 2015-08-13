package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FieldStaff database table.
 * 
 */
@Entity
@Table(name="FieldStaff")
@NamedQuery(name="FieldStaff.findAll", query="SELECT f FROM FieldStaff f")
public class FieldStaff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer fieldStaffId;

	@Column(length=45)
	private String firstName;

	@Column(length=45)
	private String lastName;

	@Column(length=100)
	private String photo;

	//bi-directional many-to-one association to FieldStaffType
	@ManyToOne
	@JoinColumn(name="fieldStaffTypeId")
	private FieldStaffType fieldStaffType;

	//bi-directional many-to-one association to FieldStaffLCSeason
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffLCSeason> fieldStaffLcseasons;

	//bi-directional many-to-one association to FieldStaffLeadershipSeason
	@OneToMany(mappedBy="fieldStaff")
	private List<FieldStaffLeadershipSeason> fieldStaffLeadershipSeasons;

	public FieldStaff() {
	}

	public Integer getFieldStaffId() {
		return this.fieldStaffId;
	}

	public void setFieldStaffId(Integer fieldStaffId) {
		this.fieldStaffId = fieldStaffId;
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

	public FieldStaffType getFieldStaffType() {
		return this.fieldStaffType;
	}

	public void setFieldStaffType(FieldStaffType fieldStaffType) {
		this.fieldStaffType = fieldStaffType;
	}

	public List<FieldStaffLCSeason> getFieldStaffLcseasons() {
		return this.fieldStaffLcseasons;
	}

	public void setFieldStaffLcseasons(List<FieldStaffLCSeason> fieldStaffLcseasons) {
		this.fieldStaffLcseasons = fieldStaffLcseasons;
	}

	public FieldStaffLCSeason addFieldStaffLcseason(FieldStaffLCSeason fieldStaffLcseason) {
		getFieldStaffLcseasons().add(fieldStaffLcseason);
		fieldStaffLcseason.setFieldStaff(this);

		return fieldStaffLcseason;
	}

	public FieldStaffLCSeason removeFieldStaffLcseason(FieldStaffLCSeason fieldStaffLcseason) {
		getFieldStaffLcseasons().remove(fieldStaffLcseason);
		fieldStaffLcseason.setFieldStaff(null);

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
		fieldStaffLeadershipSeason.setFieldStaff(this);

		return fieldStaffLeadershipSeason;
	}

	public FieldStaffLeadershipSeason removeFieldStaffLeadershipSeason(FieldStaffLeadershipSeason fieldStaffLeadershipSeason) {
		getFieldStaffLeadershipSeasons().remove(fieldStaffLeadershipSeason);
		fieldStaffLeadershipSeason.setFieldStaff(null);

		return fieldStaffLeadershipSeason;
	}

}