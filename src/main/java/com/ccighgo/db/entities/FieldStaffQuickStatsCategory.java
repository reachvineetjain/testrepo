package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the FieldStaffQuickStatsCategories database table.
 * 
 */
@Entity
@Table(name="FieldStaffQuickStatsCategories")
@NamedQuery(name="FieldStaffQuickStatsCategory.findAll", query="SELECT f FROM FieldStaffQuickStatsCategory f")
public class FieldStaffQuickStatsCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fieldStaffQSCategoryId;

	private String fieldStaffQSCategoryName;

	//bi-directional many-to-one association to FieldStaffQuickStatsType
	@ManyToOne
	@JoinColumn(name="fieldStaffQSTypeId",insertable=false,updatable=false)
	private FieldStaffQuickStatsType fieldStaffQuickStatsType;

	//bi-directional many-to-one association to FieldStaffQuickStatsCategoryAggregate
	@OneToMany(mappedBy="fieldStaffQuickStatsCategory")
	private List<FieldStaffQuickStatsCategoryAggregate> fieldStaffQuickStatsCategoryAggregates;

	public FieldStaffQuickStatsCategory() {
	}

	public Integer getFieldStaffQSCategoryId() {
		return this.fieldStaffQSCategoryId;
	}

	public void setFieldStaffQSCategoryId(Integer fieldStaffQSCategoryId) {
		this.fieldStaffQSCategoryId = fieldStaffQSCategoryId;
	}

	public String getFieldStaffQSCategoryName() {
		return this.fieldStaffQSCategoryName;
	}

	public void setFieldStaffQSCategoryName(String fieldStaffQSCategoryName) {
		this.fieldStaffQSCategoryName = fieldStaffQSCategoryName;
	}

	public FieldStaffQuickStatsType getFieldStaffQuickStatsType() {
		return this.fieldStaffQuickStatsType;
	}

	public void setFieldStaffQuickStatsType(FieldStaffQuickStatsType fieldStaffQuickStatsType) {
		this.fieldStaffQuickStatsType = fieldStaffQuickStatsType;
	}

	public List<FieldStaffQuickStatsCategoryAggregate> getFieldStaffQuickStatsCategoryAggregates() {
		return this.fieldStaffQuickStatsCategoryAggregates;
	}

	public void setFieldStaffQuickStatsCategoryAggregates(List<FieldStaffQuickStatsCategoryAggregate> fieldStaffQuickStatsCategoryAggregates) {
		this.fieldStaffQuickStatsCategoryAggregates = fieldStaffQuickStatsCategoryAggregates;
	}

	public FieldStaffQuickStatsCategoryAggregate addFieldStaffQuickStatsCategoryAggregate(FieldStaffQuickStatsCategoryAggregate fieldStaffQuickStatsCategoryAggregate) {
		getFieldStaffQuickStatsCategoryAggregates().add(fieldStaffQuickStatsCategoryAggregate);
		fieldStaffQuickStatsCategoryAggregate.setFieldStaffQuickStatsCategory(this);

		return fieldStaffQuickStatsCategoryAggregate;
	}

	public FieldStaffQuickStatsCategoryAggregate removeFieldStaffQuickStatsCategoryAggregate(FieldStaffQuickStatsCategoryAggregate fieldStaffQuickStatsCategoryAggregate) {
		getFieldStaffQuickStatsCategoryAggregates().remove(fieldStaffQuickStatsCategoryAggregate);
		fieldStaffQuickStatsCategoryAggregate.setFieldStaffQuickStatsCategory(null);

		return fieldStaffQuickStatsCategoryAggregate;
	}

}