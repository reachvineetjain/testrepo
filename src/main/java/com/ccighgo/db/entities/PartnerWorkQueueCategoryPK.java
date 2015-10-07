package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PartnerWorkQueueCategories database table.
 * 
 */
@Embeddable
public class PartnerWorkQueueCategoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(unique=true, nullable=false)
	private int partnerWQCategoryId;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int partnerWQTypeId;

	public PartnerWorkQueueCategoryPK() {
	}
	public int getPartnerWQCategoryId() {
		return this.partnerWQCategoryId;
	}
	public void setPartnerWQCategoryId(int partnerWQCategoryId) {
		this.partnerWQCategoryId = partnerWQCategoryId;
	}
	public int getPartnerWQTypeId() {
		return this.partnerWQTypeId;
	}
	public void setPartnerWQTypeId(int partnerWQTypeId) {
		this.partnerWQTypeId = partnerWQTypeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PartnerWorkQueueCategoryPK)) {
			return false;
		}
		PartnerWorkQueueCategoryPK castOther = (PartnerWorkQueueCategoryPK)other;
		return 
			(this.partnerWQCategoryId == castOther.partnerWQCategoryId)
			&& (this.partnerWQTypeId == castOther.partnerWQTypeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.partnerWQCategoryId;
		hash = hash * prime + this.partnerWQTypeId;
		
		return hash;
	}
}