package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FieldStaffPermissions database table.
 * 
 */
@Entity
@Table(name="FieldStaffPermissions")
@NamedQuery(name="FieldStaffPermission.findAll", query="SELECT f FROM FieldStaffPermission f")
public class FieldStaffPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fieldStaffPermissionsId;

	//bi-directional many-to-one association to FieldStaff
	@ManyToOne
	@JoinColumn(name="fieldStaffGoId",insertable=false,updatable=false)
	private FieldStaff fieldStaff;

	public FieldStaffPermission() {
	}

	public Integer getFieldStaffPermissionsId() {
		return this.fieldStaffPermissionsId;
	}

	public void setFieldStaffPermissionsId(Integer fieldStaffPermissionsId) {
		this.fieldStaffPermissionsId = fieldStaffPermissionsId;
	}

	public FieldStaff getFieldStaff() {
		return this.fieldStaff;
	}

	public void setFieldStaff(FieldStaff fieldStaff) {
		this.fieldStaff = fieldStaff;
	}

}