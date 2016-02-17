package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PartnerPermissions database table.
 * 
 */
@Entity
@Table(name="PartnerPermissions")
@NamedQuery(name="PartnerPermission.findAll", query="SELECT p FROM PartnerPermission p")
public class PartnerPermission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer partnerPermissionsId;

	private Byte capAccountingInsurance;

	private Byte capAdmin;

	private Byte capApplications;

	private Byte capContracting;

	private Byte capFlights;

	private Byte capInsurance;

	private Byte capMonitoring;

	private Byte capPlacementInfo;

	private Byte capStudentsPreProgram;

	private Byte f1AccountingInsurance;

	private Byte f1Admin;

	private Byte f1Applications;

	private Byte f1Contracting;

	private Byte f1Flights;

	private Byte f1Insurance;

	private Byte f1Monitoring;

	private Byte f1PlacementInfo;

	private Byte f1StudentsPreProgram;

	private Byte ihpAccountingInsurance;

	private Byte ihpAdmin;

	private Byte ihpApplications;

	private Byte ihpContracting;

	private Byte ihpFlights;

	private Byte ihpInsurance;

	private Byte ihpMonitoring;

	private Byte ihpPlacementInfo;

	private Byte ihpStudentsPreProgram;

	private Byte j1AccountingInsurance;

	private Byte j1Admin;

	private Byte j1Applications;

	private Byte j1Contracting;

	private Byte j1Flights;

	private Byte j1Insurance;

	private Byte j1Monitoring;

	private Byte j1PlacementInfo;

	private Byte j1StudentsPreProgram;

	private Byte wtAccountingInsurance;

	private Byte wtAdmin;

	private Byte wtApplications;

	private Byte wtContracting;

	private Byte wtFlights;

	private Byte wtInsurance;

	private Byte wtMonitoring;

	private Byte wtPlacementInfo;

	private Byte wtStudentsPreProgram;

	//bi-directional many-to-one association to PartnerUser
	@ManyToOne
	@JoinColumn(name="partnerUserId")
	private PartnerUser partnerUser;

	public PartnerPermission() {
	}

	public Integer getPartnerPermissionsId() {
		return this.partnerPermissionsId;
	}

	public void setPartnerPermissionsId(Integer partnerPermissionsId) {
		this.partnerPermissionsId = partnerPermissionsId;
	}

	public Byte getCapAccountingInsurance() {
		return this.capAccountingInsurance;
	}

	public void setCapAccountingInsurance(Byte capAccountingInsurance) {
		this.capAccountingInsurance = capAccountingInsurance;
	}

	public Byte getCapAdmin() {
		return this.capAdmin;
	}

	public void setCapAdmin(Byte capAdmin) {
		this.capAdmin = capAdmin;
	}

	public Byte getCapApplications() {
		return this.capApplications;
	}

	public void setCapApplications(Byte capApplications) {
		this.capApplications = capApplications;
	}

	public Byte getCapContracting() {
		return this.capContracting;
	}

	public void setCapContracting(Byte capContracting) {
		this.capContracting = capContracting;
	}

	public Byte getCapFlights() {
		return this.capFlights;
	}

	public void setCapFlights(Byte capFlights) {
		this.capFlights = capFlights;
	}

	public Byte getCapInsurance() {
		return this.capInsurance;
	}

	public void setCapInsurance(Byte capInsurance) {
		this.capInsurance = capInsurance;
	}

	public Byte getCapMonitoring() {
		return this.capMonitoring;
	}

	public void setCapMonitoring(Byte capMonitoring) {
		this.capMonitoring = capMonitoring;
	}

	public Byte getCapPlacementInfo() {
		return this.capPlacementInfo;
	}

	public void setCapPlacementInfo(Byte capPlacementInfo) {
		this.capPlacementInfo = capPlacementInfo;
	}

	public Byte getCapStudentsPreProgram() {
		return this.capStudentsPreProgram;
	}

	public void setCapStudentsPreProgram(Byte capStudentsPreProgram) {
		this.capStudentsPreProgram = capStudentsPreProgram;
	}

	public Byte getF1AccountingInsurance() {
		return this.f1AccountingInsurance;
	}

	public void setF1AccountingInsurance(Byte f1AccountingInsurance) {
		this.f1AccountingInsurance = f1AccountingInsurance;
	}

	public Byte getF1Admin() {
		return this.f1Admin;
	}

	public void setF1Admin(Byte f1Admin) {
		this.f1Admin = f1Admin;
	}

	public Byte getF1Applications() {
		return this.f1Applications;
	}

	public void setF1Applications(Byte f1Applications) {
		this.f1Applications = f1Applications;
	}

	public Byte getF1Contracting() {
		return this.f1Contracting;
	}

	public void setF1Contracting(Byte f1Contracting) {
		this.f1Contracting = f1Contracting;
	}

	public Byte getF1Flights() {
		return this.f1Flights;
	}

	public void setF1Flights(Byte f1Flights) {
		this.f1Flights = f1Flights;
	}

	public Byte getF1Insurance() {
		return this.f1Insurance;
	}

	public void setF1Insurance(Byte f1Insurance) {
		this.f1Insurance = f1Insurance;
	}

	public Byte getF1Monitoring() {
		return this.f1Monitoring;
	}

	public void setF1Monitoring(Byte f1Monitoring) {
		this.f1Monitoring = f1Monitoring;
	}

	public Byte getF1PlacementInfo() {
		return this.f1PlacementInfo;
	}

	public void setF1PlacementInfo(Byte f1PlacementInfo) {
		this.f1PlacementInfo = f1PlacementInfo;
	}

	public Byte getF1StudentsPreProgram() {
		return this.f1StudentsPreProgram;
	}

	public void setF1StudentsPreProgram(Byte f1StudentsPreProgram) {
		this.f1StudentsPreProgram = f1StudentsPreProgram;
	}

	public Byte getIhpAccountingInsurance() {
		return this.ihpAccountingInsurance;
	}

	public void setIhpAccountingInsurance(Byte ihpAccountingInsurance) {
		this.ihpAccountingInsurance = ihpAccountingInsurance;
	}

	public Byte getIhpAdmin() {
		return this.ihpAdmin;
	}

	public void setIhpAdmin(Byte ihpAdmin) {
		this.ihpAdmin = ihpAdmin;
	}

	public Byte getIhpApplications() {
		return this.ihpApplications;
	}

	public void setIhpApplications(Byte ihpApplications) {
		this.ihpApplications = ihpApplications;
	}

	public Byte getIhpContracting() {
		return this.ihpContracting;
	}

	public void setIhpContracting(Byte ihpContracting) {
		this.ihpContracting = ihpContracting;
	}

	public Byte getIhpFlights() {
		return this.ihpFlights;
	}

	public void setIhpFlights(Byte ihpFlights) {
		this.ihpFlights = ihpFlights;
	}

	public Byte getIhpInsurance() {
		return this.ihpInsurance;
	}

	public void setIhpInsurance(Byte ihpInsurance) {
		this.ihpInsurance = ihpInsurance;
	}

	public Byte getIhpMonitoring() {
		return this.ihpMonitoring;
	}

	public void setIhpMonitoring(Byte ihpMonitoring) {
		this.ihpMonitoring = ihpMonitoring;
	}

	public Byte getIhpPlacementInfo() {
		return this.ihpPlacementInfo;
	}

	public void setIhpPlacementInfo(Byte ihpPlacementInfo) {
		this.ihpPlacementInfo = ihpPlacementInfo;
	}

	public Byte getIhpStudentsPreProgram() {
		return this.ihpStudentsPreProgram;
	}

	public void setIhpStudentsPreProgram(Byte ihpStudentsPreProgram) {
		this.ihpStudentsPreProgram = ihpStudentsPreProgram;
	}

	public Byte getJ1AccountingInsurance() {
		return this.j1AccountingInsurance;
	}

	public void setJ1AccountingInsurance(Byte j1AccountingInsurance) {
		this.j1AccountingInsurance = j1AccountingInsurance;
	}

	public Byte getJ1Admin() {
		return this.j1Admin;
	}

	public void setJ1Admin(Byte j1Admin) {
		this.j1Admin = j1Admin;
	}

	public Byte getJ1Applications() {
		return this.j1Applications;
	}

	public void setJ1Applications(Byte j1Applications) {
		this.j1Applications = j1Applications;
	}

	public Byte getJ1Contracting() {
		return this.j1Contracting;
	}

	public void setJ1Contracting(Byte j1Contracting) {
		this.j1Contracting = j1Contracting;
	}

	public Byte getJ1Flights() {
		return this.j1Flights;
	}

	public void setJ1Flights(Byte j1Flights) {
		this.j1Flights = j1Flights;
	}

	public Byte getJ1Insurance() {
		return this.j1Insurance;
	}

	public void setJ1Insurance(Byte j1Insurance) {
		this.j1Insurance = j1Insurance;
	}

	public Byte getJ1Monitoring() {
		return this.j1Monitoring;
	}

	public void setJ1Monitoring(Byte j1Monitoring) {
		this.j1Monitoring = j1Monitoring;
	}

	public Byte getJ1PlacementInfo() {
		return this.j1PlacementInfo;
	}

	public void setJ1PlacementInfo(Byte j1PlacementInfo) {
		this.j1PlacementInfo = j1PlacementInfo;
	}

	public Byte getJ1StudentsPreProgram() {
		return this.j1StudentsPreProgram;
	}

	public void setJ1StudentsPreProgram(Byte j1StudentsPreProgram) {
		this.j1StudentsPreProgram = j1StudentsPreProgram;
	}

	public Byte getWtAccountingInsurance() {
		return this.wtAccountingInsurance;
	}

	public void setWtAccountingInsurance(Byte wtAccountingInsurance) {
		this.wtAccountingInsurance = wtAccountingInsurance;
	}

	public Byte getWtAdmin() {
		return this.wtAdmin;
	}

	public void setWtAdmin(Byte wtAdmin) {
		this.wtAdmin = wtAdmin;
	}

	public Byte getWtApplications() {
		return this.wtApplications;
	}

	public void setWtApplications(Byte wtApplications) {
		this.wtApplications = wtApplications;
	}

	public Byte getWtContracting() {
		return this.wtContracting;
	}

	public void setWtContracting(Byte wtContracting) {
		this.wtContracting = wtContracting;
	}

	public Byte getWtFlights() {
		return this.wtFlights;
	}

	public void setWtFlights(Byte wtFlights) {
		this.wtFlights = wtFlights;
	}

	public Byte getWtInsurance() {
		return this.wtInsurance;
	}

	public void setWtInsurance(Byte wtInsurance) {
		this.wtInsurance = wtInsurance;
	}

	public Byte getWtMonitoring() {
		return this.wtMonitoring;
	}

	public void setWtMonitoring(Byte wtMonitoring) {
		this.wtMonitoring = wtMonitoring;
	}

	public Byte getWtPlacementInfo() {
		return this.wtPlacementInfo;
	}

	public void setWtPlacementInfo(Byte wtPlacementInfo) {
		this.wtPlacementInfo = wtPlacementInfo;
	}

	public Byte getWtStudentsPreProgram() {
		return this.wtStudentsPreProgram;
	}

	public void setWtStudentsPreProgram(Byte wtStudentsPreProgram) {
		this.wtStudentsPreProgram = wtStudentsPreProgram;
	}

	public PartnerUser getPartnerUser() {
		return this.partnerUser;
	}

	public void setPartnerUser(PartnerUser partnerUser) {
		this.partnerUser = partnerUser;
	}

}