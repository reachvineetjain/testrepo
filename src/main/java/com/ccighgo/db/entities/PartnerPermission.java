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
	@Column(unique=true, nullable=false)
	private Integer partnerPermissionsId;

	private byte capAccountingInsurance;

	private byte capAdmin;

	private byte capApplications;

	private byte capContracting;

	private byte capFlights;

	private byte capInsurance;

	private byte capMonitoring;

	private byte capPlacementInfo;

	private byte capStudentsPreProgram;

	private byte f1AccountingInsurance;

	private byte f1Admin;

	private byte f1Applications;

	private byte f1Contracting;

	private byte f1Flights;

	private byte f1Insurance;

	private byte f1Monitoring;

	private byte f1PlacementInfo;

	private byte f1StudentsPreProgram;

	private byte j1AccountingInsurance;

	private byte j1Admin;

	private byte j1Applications;

	private byte j1Contracting;

	private byte j1Flights;

	private byte j1Insurance;

	private byte j1Monitoring;

	private byte j1PlacementInfo;

	private byte j1StudentsPreProgram;

	private byte wtAccoutingInsurance;

	private byte wtAdmin;

	private byte wtApplications;

	private byte wtContracting;

	private byte wtFlights;

	private byte wtInsurance;

	private byte wtMonitoring;

	private byte wtPlacementInfo;

	private byte wtStudentsPreProgram;

	//bi-directional many-to-one association to PartnerUser
	@ManyToOne
	@JoinColumn(name="partnerUserId", nullable=false)
	private PartnerUser partnerUser;

	public PartnerPermission() {
	}

	public Integer getPartnerPermissionsId() {
		return this.partnerPermissionsId;
	}

	public void setPartnerPermissionsId(Integer partnerPermissionsId) {
		this.partnerPermissionsId = partnerPermissionsId;
	}

	public byte getCapAccountingInsurance() {
		return this.capAccountingInsurance;
	}

	public void setCapAccountingInsurance(byte capAccountingInsurance) {
		this.capAccountingInsurance = capAccountingInsurance;
	}

	public byte getCapAdmin() {
		return this.capAdmin;
	}

	public void setCapAdmin(byte capAdmin) {
		this.capAdmin = capAdmin;
	}

	public byte getCapApplications() {
		return this.capApplications;
	}

	public void setCapApplications(byte capApplications) {
		this.capApplications = capApplications;
	}

	public byte getCapContracting() {
		return this.capContracting;
	}

	public void setCapContracting(byte capContracting) {
		this.capContracting = capContracting;
	}

	public byte getCapFlights() {
		return this.capFlights;
	}

	public void setCapFlights(byte capFlights) {
		this.capFlights = capFlights;
	}

	public byte getCapInsurance() {
		return this.capInsurance;
	}

	public void setCapInsurance(byte capInsurance) {
		this.capInsurance = capInsurance;
	}

	public byte getCapMonitoring() {
		return this.capMonitoring;
	}

	public void setCapMonitoring(byte capMonitoring) {
		this.capMonitoring = capMonitoring;
	}

	public byte getCapPlacementInfo() {
		return this.capPlacementInfo;
	}

	public void setCapPlacementInfo(byte capPlacementInfo) {
		this.capPlacementInfo = capPlacementInfo;
	}

	public byte getCapStudentsPreProgram() {
		return this.capStudentsPreProgram;
	}

	public void setCapStudentsPreProgram(byte capStudentsPreProgram) {
		this.capStudentsPreProgram = capStudentsPreProgram;
	}

	public byte getF1AccountingInsurance() {
		return this.f1AccountingInsurance;
	}

	public void setF1AccountingInsurance(byte f1AccountingInsurance) {
		this.f1AccountingInsurance = f1AccountingInsurance;
	}

	public byte getF1Admin() {
		return this.f1Admin;
	}

	public void setF1Admin(byte f1Admin) {
		this.f1Admin = f1Admin;
	}

	public byte getF1Applications() {
		return this.f1Applications;
	}

	public void setF1Applications(byte f1Applications) {
		this.f1Applications = f1Applications;
	}

	public byte getF1Contracting() {
		return this.f1Contracting;
	}

	public void setF1Contracting(byte f1Contracting) {
		this.f1Contracting = f1Contracting;
	}

	public byte getF1Flights() {
		return this.f1Flights;
	}

	public void setF1Flights(byte f1Flights) {
		this.f1Flights = f1Flights;
	}

	public byte getF1Insurance() {
		return this.f1Insurance;
	}

	public void setF1Insurance(byte f1Insurance) {
		this.f1Insurance = f1Insurance;
	}

	public byte getF1Monitoring() {
		return this.f1Monitoring;
	}

	public void setF1Monitoring(byte f1Monitoring) {
		this.f1Monitoring = f1Monitoring;
	}

	public byte getF1PlacementInfo() {
		return this.f1PlacementInfo;
	}

	public void setF1PlacementInfo(byte f1PlacementInfo) {
		this.f1PlacementInfo = f1PlacementInfo;
	}

	public byte getF1StudentsPreProgram() {
		return this.f1StudentsPreProgram;
	}

	public void setF1StudentsPreProgram(byte f1StudentsPreProgram) {
		this.f1StudentsPreProgram = f1StudentsPreProgram;
	}

	public byte getJ1AccountingInsurance() {
		return this.j1AccountingInsurance;
	}

	public void setJ1AccountingInsurance(byte j1AccountingInsurance) {
		this.j1AccountingInsurance = j1AccountingInsurance;
	}

	public byte getJ1Admin() {
		return this.j1Admin;
	}

	public void setJ1Admin(byte j1Admin) {
		this.j1Admin = j1Admin;
	}

	public byte getJ1Applications() {
		return this.j1Applications;
	}

	public void setJ1Applications(byte j1Applications) {
		this.j1Applications = j1Applications;
	}

	public byte getJ1Contracting() {
		return this.j1Contracting;
	}

	public void setJ1Contracting(byte j1Contracting) {
		this.j1Contracting = j1Contracting;
	}

	public byte getJ1Flights() {
		return this.j1Flights;
	}

	public void setJ1Flights(byte j1Flights) {
		this.j1Flights = j1Flights;
	}

	public byte getJ1Insurance() {
		return this.j1Insurance;
	}

	public void setJ1Insurance(byte j1Insurance) {
		this.j1Insurance = j1Insurance;
	}

	public byte getJ1Monitoring() {
		return this.j1Monitoring;
	}

	public void setJ1Monitoring(byte j1Monitoring) {
		this.j1Monitoring = j1Monitoring;
	}

	public byte getJ1PlacementInfo() {
		return this.j1PlacementInfo;
	}

	public void setJ1PlacementInfo(byte j1PlacementInfo) {
		this.j1PlacementInfo = j1PlacementInfo;
	}

	public byte getJ1StudentsPreProgram() {
		return this.j1StudentsPreProgram;
	}

	public void setJ1StudentsPreProgram(byte j1StudentsPreProgram) {
		this.j1StudentsPreProgram = j1StudentsPreProgram;
	}

	public byte getWtAccoutingInsurance() {
		return this.wtAccoutingInsurance;
	}

	public void setWtAccoutingInsurance(byte wtAccoutingInsurance) {
		this.wtAccoutingInsurance = wtAccoutingInsurance;
	}

	public byte getWtAdmin() {
		return this.wtAdmin;
	}

	public void setWtAdmin(byte wtAdmin) {
		this.wtAdmin = wtAdmin;
	}

	public byte getWtApplications() {
		return this.wtApplications;
	}

	public void setWtApplications(byte wtApplications) {
		this.wtApplications = wtApplications;
	}

	public byte getWtContracting() {
		return this.wtContracting;
	}

	public void setWtContracting(byte wtContracting) {
		this.wtContracting = wtContracting;
	}

	public byte getWtFlights() {
		return this.wtFlights;
	}

	public void setWtFlights(byte wtFlights) {
		this.wtFlights = wtFlights;
	}

	public byte getWtInsurance() {
		return this.wtInsurance;
	}

	public void setWtInsurance(byte wtInsurance) {
		this.wtInsurance = wtInsurance;
	}

	public byte getWtMonitoring() {
		return this.wtMonitoring;
	}

	public void setWtMonitoring(byte wtMonitoring) {
		this.wtMonitoring = wtMonitoring;
	}

	public byte getWtPlacementInfo() {
		return this.wtPlacementInfo;
	}

	public void setWtPlacementInfo(byte wtPlacementInfo) {
		this.wtPlacementInfo = wtPlacementInfo;
	}

	public byte getWtStudentsPreProgram() {
		return this.wtStudentsPreProgram;
	}

	public void setWtStudentsPreProgram(byte wtStudentsPreProgram) {
		this.wtStudentsPreProgram = wtStudentsPreProgram;
	}

	public PartnerUser getPartnerUser() {
		return this.partnerUser;
	}

	public void setPartnerUser(PartnerUser partnerUser) {
		this.partnerUser = partnerUser;
	}

}