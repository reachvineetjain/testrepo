package com.ccighgo.db.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the OauthHistory database table.
 * 
 */
@Embeddable
public class OauthHistoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String nonce;

	private long timeValue;

	public OauthHistoryPK() {
	}
	public String getNonce() {
		return this.nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public long getTimeValue() {
		return this.timeValue;
	}
	public void setTimeValue(long timeValue) {
		this.timeValue = timeValue;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OauthHistoryPK)) {
			return false;
		}
		OauthHistoryPK castOther = (OauthHistoryPK)other;
		return 
			this.nonce.equals(castOther.nonce)
			&& (this.timeValue == castOther.timeValue);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nonce.hashCode();
		hash = hash * prime + ((int) (this.timeValue ^ (this.timeValue >>> 32)));
		
		return hash;
	}
}