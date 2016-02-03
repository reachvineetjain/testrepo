package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EmployerPermissions database table.
 * 
 */
@Entity
@Table(name="EmployerPermissions")
@NamedQuery(name="EmployerPermission.findAll", query="SELECT e FROM EmployerPermission e")
public class EmployerPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer empolyerPermissionsId;

	//bi-directional many-to-one association to Employer
	@ManyToOne
	@JoinColumn(name="employerGoId",insertable=false,updatable=false)
	private Employer employer;

	public EmployerPermission() {
	}

	public Integer getEmpolyerPermissionsId() {
		return this.empolyerPermissionsId;
	}

	public void setEmpolyerPermissionsId(Integer empolyerPermissionsId) {
		this.empolyerPermissionsId = empolyerPermissionsId;
	}

	public Employer getEmployer() {
		return this.employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

}