package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FieldStaffAgreement database table.
 * 
 */
@Entity
@NamedQuery(name="FieldStaffAgreement.findAll", query="SELECT f FROM FieldStaffAgreement f")
public class FieldStaffAgreement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fieldStaffAgreementId;

	private String agreementName;

	//bi-directional many-to-one association to SeasonF1Detail
	@OneToMany(mappedBy="fieldStaffAgreement")
	private List<SeasonF1Detail> seasonF1details;

	//bi-directional many-to-one association to SeasonJ1Detail
	@OneToMany(mappedBy="fieldStaffAgreement")
	private List<SeasonJ1Detail> seasonJ1details;

	public FieldStaffAgreement() {
	}

	public Integer getFieldStaffAgreementId() {
		return this.fieldStaffAgreementId;
	}

	public void setFieldStaffAgreementId(Integer fieldStaffAgreementId) {
		this.fieldStaffAgreementId = fieldStaffAgreementId;
	}

	public String getAgreementName() {
		return this.agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public List<SeasonF1Detail> getSeasonF1details() {
		return this.seasonF1details;
	}

	public void setSeasonF1details(List<SeasonF1Detail> seasonF1details) {
		this.seasonF1details = seasonF1details;
	}

	public SeasonF1Detail addSeasonF1detail(SeasonF1Detail seasonF1detail) {
		getSeasonF1details().add(seasonF1detail);
		seasonF1detail.setFieldStaffAgreement(this);

		return seasonF1detail;
	}

	public SeasonF1Detail removeSeasonF1detail(SeasonF1Detail seasonF1detail) {
		getSeasonF1details().remove(seasonF1detail);
		seasonF1detail.setFieldStaffAgreement(null);

		return seasonF1detail;
	}

	public List<SeasonJ1Detail> getSeasonJ1details() {
		return this.seasonJ1details;
	}

	public void setSeasonJ1details(List<SeasonJ1Detail> seasonJ1details) {
		this.seasonJ1details = seasonJ1details;
	}

	public SeasonJ1Detail addSeasonJ1detail(SeasonJ1Detail seasonJ1detail) {
		getSeasonJ1details().add(seasonJ1detail);
		seasonJ1detail.setFieldStaffAgreement(this);

		return seasonJ1detail;
	}

	public SeasonJ1Detail removeSeasonJ1detail(SeasonJ1Detail seasonJ1detail) {
		getSeasonJ1details().remove(seasonJ1detail);
		seasonJ1detail.setFieldStaffAgreement(null);

		return seasonJ1detail;
	}

}