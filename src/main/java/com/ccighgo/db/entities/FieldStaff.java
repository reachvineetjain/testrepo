package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


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
	private int fieldStaffId;

	@Column(length=45)
	private String firstName;

	@Column(length=45)
	private String lastName;

	@Column(length=100)
	private String photo;

	public FieldStaff() {
	}

	public int getFieldStaffId() {
		return this.fieldStaffId;
	}

	public void setFieldStaffId(int fieldStaffId) {
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

}