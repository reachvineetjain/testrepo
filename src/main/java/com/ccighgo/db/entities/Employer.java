package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Employer database table.
 * 
 */
@Entity
@NamedQuery(name="Employer.findAll", query="SELECT e FROM Employer e")
public class Employer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer employerGoId;

	private Integer employerStatusId;

	//bi-directional one-to-one association to GoIdSequence
	@OneToOne
	@JoinColumn(name="employerGoId")
	private GoIdSequence goIdSequence;

	//bi-directional many-to-one association to EmployerPermission
	@OneToMany(mappedBy="employer")
	private List<EmployerPermission> employerPermissions;

	public Employer() {
	}

	public Integer getEmployerGoId() {
		return this.employerGoId;
	}

	public void setEmployerGoId(Integer employerGoId) {
		this.employerGoId = employerGoId;
	}

	public Integer getEmployerStatusId() {
		return this.employerStatusId;
	}

	public void setEmployerStatusId(Integer employerStatusId) {
		this.employerStatusId = employerStatusId;
	}

	public GoIdSequence getGoIdSequence() {
		return this.goIdSequence;
	}

	public void setGoIdSequence(GoIdSequence goIdSequence) {
		this.goIdSequence = goIdSequence;
	}

	public List<EmployerPermission> getEmployerPermissions() {
		return this.employerPermissions;
	}

	public void setEmployerPermissions(List<EmployerPermission> employerPermissions) {
		this.employerPermissions = employerPermissions;
	}

	public EmployerPermission addEmployerPermission(EmployerPermission employerPermission) {
		getEmployerPermissions().add(employerPermission);
		employerPermission.setEmployer(this);

		return employerPermission;
	}

	public EmployerPermission removeEmployerPermission(EmployerPermission employerPermission) {
		getEmployerPermissions().remove(employerPermission);
		employerPermission.setEmployer(null);

		return employerPermission;
	}

}