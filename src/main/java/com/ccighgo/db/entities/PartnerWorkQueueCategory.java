package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PartnerWorkQueueCategories database table.
 * 
 */
@Entity
@Table(name="PartnerWorkQueueCategories")
@NamedQuery(name="PartnerWorkQueueCategory.findAll", query="SELECT p FROM PartnerWorkQueueCategory p")
public class PartnerWorkQueueCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PartnerWorkQueueCategoryPK id;

	@Column(length=45)
	private String partnerWQCategoryName;

	//bi-directional many-to-one association to PartnerWorkQueueType
	@ManyToOne
	@JoinColumn(name="partnerWQTypeId", nullable=false, insertable=false, updatable=false)
	private PartnerWorkQueueType partnerWorkQueueType;

	public PartnerWorkQueueCategory() {
	}

	public PartnerWorkQueueCategoryPK getId() {
		return this.id;
	}

	public void setId(PartnerWorkQueueCategoryPK id) {
		this.id = id;
	}

	public String getPartnerWQCategoryName() {
		return this.partnerWQCategoryName;
	}

	public void setPartnerWQCategoryName(String partnerWQCategoryName) {
		this.partnerWQCategoryName = partnerWQCategoryName;
	}

	public PartnerWorkQueueType getPartnerWorkQueueType() {
		return this.partnerWorkQueueType;
	}

	public void setPartnerWorkQueueType(PartnerWorkQueueType partnerWorkQueueType) {
		this.partnerWorkQueueType = partnerWorkQueueType;
	}

}